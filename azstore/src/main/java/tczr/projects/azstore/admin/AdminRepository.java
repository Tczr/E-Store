package tczr.projects.azstore.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tczr.projects.azstore.admin.model.Admin;
import tczr.projects.azstore.admin.model.AdminType;
import tczr.projects.azstore.shared.Repo;
import tczr.projects.azstore.shared.Status;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AdminRepository implements Repo<Admin>{

    private static final String USER_EMAIL_REG = "^([^\\d]\\w+).*(\\@\\w+)(\\.\\w+)";
    private static final  String ID_REG="^\\d+";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("adminTypeRepository")
    private Repo adminType;
    private RowMapper rowMapper = (result, rowNumber) ->{
        Admin account = new Admin(
                result.getString("admin_email"),
                result.getString("admin_accountName"),
                result.getString("admin_password"));

        account.setId(result.getInt("admin_id"));
        account.setPhone(result.getString("admin_phoneNumber"));
        account.setCreatedAt(result.getTimestamp("createdAt").toLocalDateTime());
        account.setModifiedAt(result.getTimestamp("modifiedAt").toLocalDateTime());
        account.setLastLogin(result.getTimestamp("last_login").toLocalDateTime());
        String fname=result.getString("admin_first_name");
        String lname = result.getString("admin_last_name");
        account.setFullname(fname+" "+lname);
        account.setStatus(Status.toStatus(result.getString("status")));
        account.setAdminType((AdminType)
                adminType.findById(result.getInt("admin_type_id")).get());

      //  admin_type is 1:n. so, we will create a  query for it

        return account;
    };

    AdminRepository(JdbcTemplate jdbcTemplate, Repo adminType){
        this.jdbcTemplate=jdbcTemplate;
        this.adminType=adminType;
    }


    @Override
    public List<Admin> getAll() {

        return jdbcTemplate.query(" SELECT * FROM admin",
                rowMapper) ;
    }

  /*  @Override
    public List<Admin> findAllBy(Object Id) {
        return List.of(findById(ID).get());
    }*/

    @Override
    public Optional<Admin> findBy(Object obj) {
       return selectQueryOf((String)obj);
    }

    @Override
    public Optional<Admin> findById(Integer ID) {
        Admin admin=(Admin) jdbcTemplate.queryForObject(
                "SELECT * FROM admin WHERE admin_id=?",
                rowMapper,
                ID);

        return Optional.ofNullable(admin);
    }


    // to be implemented
    @Override
    public boolean existsById(Integer Id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM admin WHERE admin_id=?",
                rowMapper,
                Id
        ) != null;
    }

    @Override
    public boolean insert(Admin entity) {

        return jdbcTemplate.update(
                "INSERT INTO admin(" +
                        "admin_id, admin_password, admin_accountName, admin_email" +
                        " admin_poneNumber, admin_first_name, admin_last_name," +
                        "admin_type_id," +
                        "status, last_login, createdAt",
                entity.getId(), entity.getPassword(), entity.getUserName() ,  entity.getEmail(),
                entity.getPhone(), entity.getFirstName(), entity.getLastName(),
                entity.getAdminType().getId(),
                entity.getStatus().toString(), entity.getLastLogin(), entity.getCreatedAt()
        )==1;
    }

    @Override
    public boolean insertAll(List<Admin> entities) {
        AtomicInteger size = new AtomicInteger(entities.size());
        entities.forEach(
                entity ->{
                    insert(entity);
                    size.getAndDecrement();
                }
        );

        return size.equals(0);
    }


    @Override
    public boolean insertWith(Admin entity, Object...option) {
        return jdbcTemplate.update(
                "INSERT INTO admin" +
                    "(admin_accountName, " +
                    "admin_email," +
                    "admin_password," +
                    "status," +
                    "admin_type_id," +
                    "admin_first_name," +
                    "admin_last_name," +
                    "admin_phoneNumber," +
                    "last_login,) " +
                    "createdAt)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",

                entity.getUserName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getStatus().toString(),
                option,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhone(),
                entity.getLastLogin(),
                entity.getCreatedAt()
        ) == 1;
    }

    @Override
    public boolean update(Admin entity) {
        return jdbcTemplate.update(
                "UPDATE admin SET admin_email=?, admin_accountName=?, admin_password=? WHERE admin_id=?",
                entity.getEmail(), entity.getUserName(),entity.getPassword(),

                entity.getId()
        )==1;
    }

    //just the one who has all permissions
    @Override
    public boolean delete(Admin entity) {
        return jdbcTemplate.update(
                "DELETE FROM admin WHERE admin_id=?",
                entity.getId()
        )==1;
    }

    private Optional<Admin> findByEmail(String email){
        Admin user = (Admin) jdbcTemplate.queryForObject(
                "SELECT * FROM admins WHERE user_email=?",
                rowMapper,
                email
        );
        return Optional.ofNullable(user);
    }

    private Optional<Admin> findByUserName(String userName){
        Admin admin =(Admin) jdbcTemplate.queryForObject(
                "SELECT * FROM admins WHERE user_name=?",
                rowMapper,
                userName
        );
        return  Optional.ofNullable(admin);
    }


   /* private AdminType getTypeOfAdmin(int typeId){
       return  jdbcTemplate.queryForObject(
                "SELECT * FROM admin_type WHERE admin_type_id=?",
                (res, rowNumber)->{
                    String type = res.getString("admin_type");
                     return AdminType.toAdminType(type);
                },
                typeId
        );
    }*/
    /***
     helper function:
     -SelectQueryOF : dteremins wat query should search for
     */

    private Optional<Admin> selectQueryOf(String obj){
        if(obj.matches(ID_REG))
            return findById(Integer.parseInt(obj));

        else if(obj.matches(USER_EMAIL_REG))
            return  findByEmail(obj);
        //default
        return findByUserName(obj);
    }
}
