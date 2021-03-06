package tczr.projects.azstore.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tczr.projects.azstore.admin.model.Admin;
import tczr.projects.azstore.shared.Repo;
import tczr.projects.azstore.shared.security.Encryption;

import java.util.*;

@Service
public class AdminService {

    private static final String USER_EMAIL_REG = "^([^\\d]\\w+).*(\\@\\w+)(\\.\\w+)";
    private static final  String ID_REG="^\\d+";
    private static int lastIndex = -1;
    private static int lastId;
    private static Admin[] inserted_admin =  new Admin[15];
    private static Map<Integer, Admin> admins =new HashMap<>();

    @Autowired
    private final Repo adminRepository;

    public AdminService(Repo adminRepository) {
        this.adminRepository=adminRepository;
        setList(adminRepository.getAll());
    }

    private void setList(List<Admin> adminList){
        //
        adminList.forEach(
                admin -> {
                    admins.put(admin.getId(),admin);
                });
        //to get last id, there are simply way is to set it from the loop above until the loop is finished
       lastId = adminList.get(adminList.size()-1).getId();
    }
// Get Section
    public List<Admin> getAll(){
        return admins.values().stream().toList();
    }

    public Optional<Admin> getById(Integer id){return  Optional.ofNullable(admins.get(id));}
    public Optional getBy(Object obj){
       return selectQueryOf((String) obj);
    }

    public Optional<Admin> getByEmail(String email){
//       return Optional.ofNullable(searchFor(email, "email"));
        return adminRepository.findBy(email);
    }

    public Optional<Admin> getByUserName(String userName){
        return Optional.ofNullable(searchFor(userName, "username"));
    }

    // Inserted Section
    public boolean insert(Admin newAdmin)
    {
        if(isFlushed()) {

            inserted_admin = new Admin[20];

        }else if(isListFull()){
            insertAll();
        }else{
            String encryptedPass = Encryption.encryptPassword(newAdmin.getPassword());
            newAdmin.setPassword(encryptedPass);


            lastIndex++;
            lastId++;
            newAdmin.setId(lastId);

            admins.put(lastId, newAdmin);
            inserted_admin[lastIndex]=newAdmin;

            return true;
        }

        return  insert(newAdmin);

    }


    private void insertAll(){
        for (int i=0; i<inserted_admin.length; i++) {System.out.println(inserted_admin[i]);}
        adminRepository.insertAll(Arrays.stream(inserted_admin).toList());

        //flush :)
        inserted_admin=null;
        lastIndex=0;

    }


    //Updating Section
    public boolean update(Admin admin)
    {
        admins.replace(admin.getId(), admin);
         return adminRepository.update(admin);
    }


    public boolean delete(Admin admin){
       return admins.remove(admin.getId(), admin );
    }
    //HelperSection
    /**
     * Function Document:
     * @param string
     * @param type
     * @return
     */
    private Admin searchFor(String string, String...type ){
        //.NoSuchElementException
        if (type[0].equalsIgnoreCase("username"))
        {
            return admins.values().stream()
                .filter( (result) -> result.getUserName().equalsIgnoreCase(string) )
                .findAny()
                .get();

        }


        //default
        return admins.values().stream()
                .filter( (result) -> result.getEmail().equals(string) )
                .findAny()
                .get();
    }

    private Optional<Admin> selectQueryOf(String obj){
        if(obj.matches(ID_REG))
            return getById(Integer.parseInt(obj));

        else if(obj.matches(USER_EMAIL_REG))
            return  getByEmail(obj);
        //default
        return getByUserName(obj);
    }


    private boolean isListFull(){
        if(isFlushed()) return false;
        return  lastIndex+1>=inserted_admin.length;
    }

    private boolean isFlushed(){
        return inserted_admin==null;
    }
}
