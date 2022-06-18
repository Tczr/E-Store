package tczr.projects.azstore.costumer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tczr.projects.azstore.shared.Repo;
import tczr.projects.azstore.costumer.model.Costumer;

import java.util.List;
import java.util.Optional;

@Repository
public class CostumerRepository implements Repo<Costumer> {

    private static final String USER_EMAIL_REG = "^([^\\d]\\w+).*(\\@\\w+)(\\.\\w+)";
    private static final  String ID_REG="^\\d+";

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper rowMapper = (result, rowNumber) ->{
        Costumer account = new Costumer(
                result.getString("user_email"),
                result.getString("user_name"),
                result.getString("user_password"));
        account.setId(result.getInt("user_id"));
        account.setPhone(result.getString("user_phone"));
//        account.setType(result.getString("user_type"));
        account.setCreatedAt(
                                result.getTimestamp("createdAt").toLocalDateTime()
        );


        return account;
    };

    CostumerRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


    public List<Costumer> findAll() {

        return jdbcTemplate.query(" SELECT * FROM users",
                rowMapper) ;
    }

    @Override
    public List<Costumer> getAll() {
        return null;
    }

   /* @Override
    public List<Costumer> findAllBy(Object ID) {
        return List.of(findById(Integer.parseInt(ID.toString())).get());
    }*/

    @Override
    public Optional<Costumer> findBy(Object obj) {

        return selectQueryOf((String)obj);
    }

    @Override
    public Optional<Costumer> findById(Integer ID) {
        Costumer user=(Costumer) jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id=?",
                rowMapper,
                ID);

        return Optional.ofNullable(user);
    }


    @Override
    public boolean existsById(Integer Id) {
       return jdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE user_id=?",
                rowMapper,
                Id
        ) != null;
    }

    @Override
    public boolean insert(Costumer entity) {
        return jdbcTemplate.update(
                "INSERT INTO users(user_email,user_password,user_name,createdAt) " +
                        "VALUES (?, ?, ?, ?)",
                entity.getEmail(), entity.getPassword(), entity.getUserName(), entity.getCreatedAt()
        ) == 1;
    }

    @Override
    public boolean insertWith(Costumer entity, Object... option) {
        return false;
    }

    @Override
    public boolean insertAll(List<Costumer> entities) {
        return false;
    }

    @Override
    public boolean update(Costumer entity) {
        return jdbcTemplate.update(
                "UPDATE users SET user_email=?, user_name=?, user_password=? WHERE user_id=?",
                entity.getEmail(), entity.getUserName(),entity.getPassword(),

                entity.getId()
        )==1;
    }

    @Override
    public boolean delete(Costumer entity) {
        return jdbcTemplate.update(
                "DELETE FROM users WHERE user_id=?",
                entity.getId()
        )==1;
    }

    private Optional<Costumer> findByEmail(String email){
        Costumer costumer = (Costumer) jdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE user_email=?",
                rowMapper,
                email
        );
        return Optional.ofNullable(costumer);
    }

    private Optional<Costumer> findByUserName(String userName){
        Costumer costumer =(Costumer) jdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE user_name=?",
                rowMapper,
                userName
        );
        return  Optional.ofNullable(costumer);
    }
    /***
        helper function:
            -SelectQueryOF : dteremins wat query should search for
     */

    private Optional<Costumer> selectQueryOf(String obj){
        if(obj.matches(ID_REG))
            return findById(Integer.parseInt(obj));

        else if(obj.matches(USER_EMAIL_REG))
            return  findByEmail(obj);
        //default
        return findByUserName(obj);
    }
}
