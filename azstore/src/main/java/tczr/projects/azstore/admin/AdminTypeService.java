package tczr.projects.azstore.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tczr.projects.azstore.admin.model.Admin;
import tczr.projects.azstore.admin.model.AdminType;
import tczr.projects.azstore.shared.Repo;

import java.util.*;

@Service
public class AdminTypeService {

    private static final  String ID_REG="^\\d+";

    //we could do proxy pattern to limit the amount of new inserted but for now, lets keep it simple. :|
    static AdminType[] newAdminTypeInserted = new AdminType[15];

    static Map<Integer, AdminType> typeMap = new HashMap<>();
    static int lastId, lastIndex;
    @Autowired
    private final Repo adminTypeRepository;
    public AdminTypeService(Repo adminTypeRepository){
        this.adminTypeRepository=adminTypeRepository;
       lastIndex=0;
        setList(adminTypeRepository.getAll());
    }
    private void setList(List<AdminType> adminTypes){
        //
        if(adminTypes.size()>0){
        adminTypes.forEach(
                adminType -> {
                    typeMap.put(adminType.getId(),adminType);
                });
        //to get last id, there are simply way is to set it from the loop above until the loop is finished
        lastId = adminTypes.get(adminTypes.size()-1).getId();
        }
    }

    public List<AdminType> getAll(){return typeMap.values().stream().toList();}

    public Optional<AdminType> getById(Integer id){return  Optional.ofNullable(typeMap.get(id));}
    public Optional<AdminType> getBy(Object obj){
       return getByType((String)obj);
    }

       private Optional<AdminType> getByPermission(String permission) {
            return Optional.empty();   // to be implemented
       }
    private Optional<AdminType> getByType(String type){
       return Optional.ofNullable(searchFor(type, "Type"));
    }
    // Inserted Section
    public boolean insert(AdminType newType)
    {
        if(isListFull()){
            insertAll();
        }
        else if(!isFlushed()){
            lastId++;
            newType.setId(lastId);
            typeMap.put(lastId, newType);
            newAdminTypeInserted[lastIndex]=newType;
            lastIndex++;
            return true;
        }
        else {
            newAdminTypeInserted = new AdminType[20];
        }

        return  insert(newType);

    }


    private void insertAll(){

        adminTypeRepository.insertAll(Arrays.stream(newAdminTypeInserted).toList());

        //flush :)
        newAdminTypeInserted=null;
        lastIndex=0;

    }


    //Updating Section
    public boolean update(AdminType admin)
    {
        typeMap.replace(admin.getId(), admin);
        return adminTypeRepository.update(admin);
    }


    public boolean delete(AdminType admintype){
        adminTypeRepository.delete(admintype);
        return typeMap.remove(admintype.getId(), admintype );
    }
    //HelperSection
    /**
     * Function Document:
     * @param string
     * @param type
     * @return
     */
    private AdminType searchFor(String string, String...type ){
        //.NoSuchElementException
        if (type.equals("Permission"))
        {return typeMap.values().stream()
                .filter( (result) -> result.getPermission().equals(string) )
                .findAny()
                .get();}


        //default
        return typeMap.values().stream()
                .filter( (result) -> result.getType().equals(string) )
                .findAny()
                .get();
    }
/*
    private Optional<AdminType> selectQueryOf(String obj){
        if(obj.matches(ID_REG))
            return getById(Integer.parseInt(obj));
        //default
        return getBy(obj);
    }*/


    private boolean isListFull(){

        return  lastIndex<newAdminTypeInserted.length;
    }

    private boolean isFlushed(){
        return newAdminTypeInserted==null;
    }
}
