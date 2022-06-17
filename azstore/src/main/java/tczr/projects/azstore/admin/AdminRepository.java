package tczr.projects.azstore.admin;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tczr.projects.azstore.admin.model.Admin;
import tczr.projects.azstore.admin.model.AdminType;
import tczr.projects.azstore.shared.Repo;
import tczr.projects.azstore.shared.Status;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepository implements Repo<Admin>{

    private static final String USER_EMAIL_REG = "^([^\\d]\\w+).*(\\@\\w+)(\\.\\w+)";
    private static final  String ID_REG="^\\d+";
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper rowMapper = (result, rowNumber) ->{
        Admin account = new Admin(
                result.getString("admin_email"),
                result.getString("admin_accountName"),
                result.getString("admin_password"));

        account.setId(result.getInt("admin_id"));
        account.setPhone(result.getString("admin_phoneNumber"));
        account.setCreatedAt(result.getDate("createdAt").toLocalDate());
        account.setModifiedAt(result.getDate("modifiedAt").toLocalDate());
//        must test it
//        account.setLastLogin();
        String fname=result.getString("admin_first_name");
        String lname = result.getString("admin_last_name");
        account.setFullname(fname+" "+lname);
        account.setStatus(Status.toStatus(result.getString("status")));
        account.setAdminType(getTypeOfAdmin(result.getInt("admin_type_id")));

      //  admin_type is 1:n. so, we will create a  query for it

        return account;
    };

    AdminRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


    @Override
    public List<Admin> getAll() {

        return jdbcTemplate.query(" SELECT * FROM admin",
                rowMapper) ;
    }

    @Deprecated
    @Override
    public List<Admin> findAllById(Integer ID) {
        return List.of(findById(ID).get());
    }

    @Override
    public Optional<Admin> findBy(Object obj) {
       return selectQueryOf((String)obj);
    }

    @Override
    public Optional<Admin> findById(Integer ID) {
        Admin admin=(Admin) jdbcTemplate.queryForObject("SELECT * FROM admins WHERE user_id=?",
                rowMapper,
                ID);

        return Optional.ofNullable(admin);
    }


    // to be implemented
    @Override
    public boolean existsById(Integer Id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM admins WHERE user_id=?",
                rowMapper,
                Id
        ) != null;
    }

    @Override
    public boolean save(Admin entity) {
        return false;
    }

    @Override
    public boolean saveAll(List<Admin> entities) {
        return false;
    }


    @Override
    public boolean saveInDifferentTables(Admin entity,Object...option) {
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
                entity.getSecondName(),
                entity.getPhone(),
                entity.getLastLogin(),
                entity.getCreatedAt()
        ) == 1;
    }

    @Override
    public boolean update(Admin entity) {
        return jdbcTemplate.update(
                "UPDATE users SET user_email=?, user_name=?, user_password=? WHERE user_id=?",
                entity.getEmail(), entity.getUserName(),entity.getPassword(),

                entity.getId()
        )==1;
    }

    //just the one who has all permissions
    @Override
    public boolean delete(Admin entity) {
        return jdbcTemplate.update(
                "DELETE FROM users WHERE user_id=?",
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


    private AdminType getTypeOfAdmin(int typeId){
       return  jdbcTemplate.queryForObject(
                "SELECT * FROM admin_type WHERE admin_type_id=?",
                (res, rowNumber)->{
                    String type = res.getString("admin_type");
                     return AdminType.toAdminType(type);
                },
                typeId
        );
    }
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
