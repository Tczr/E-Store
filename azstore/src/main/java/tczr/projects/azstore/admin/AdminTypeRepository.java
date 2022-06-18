package tczr.projects.azstore.admin;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import tczr.projects.azstore.admin.model.AdminType;
import tczr.projects.azstore.shared.Repo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class AdminTypeRepository implements Repo<AdminType> {

    private final RowMapper rowMapper = (res, numberRow )->{

        AdminType adminType = new AdminType(
                res.getString("admin_type"),
                res.getString("admin_permission")
        );
        return adminType;
    };

    private final JdbcTemplate jdbcTemplate;
    public AdminTypeRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}


    @Override
    public List<AdminType> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM admin_type",
                rowMapper
        );
    }

   /* @Override
    public List<AdminType> findAllBy(Object type) {
        return jdbcTemplate.query(
            "SELECT * FROM admin_type WHERE admin_type=?",
            rowMapper,
            type
        );
    }*/

    @Override
    public Optional<AdminType> findBy(Object typeName) {
        AdminType type = (AdminType) jdbcTemplate.queryForObject(
          "SELECT * FROM admin_type WHERE admin_type=?",
          rowMapper,
          typeName
        );
        return Optional.ofNullable(type);
    }

    @Override
    public Optional<AdminType> findById(Integer ID) {
        AdminType type = (AdminType) jdbcTemplate.queryForObject(
                "SELECT * FROM admin_type WHERE admin_type_id=?",
                rowMapper,
                ID
        );
        return Optional.ofNullable(type);
    }

    @Override
    public boolean existsById(Integer primaryKey) {
        return findById(primaryKey)!=null;
    }

    @Override
    public boolean insert(AdminType entity) {

        return jdbcTemplate.update(
            "INSERT INTO admin_type(" +
                    "admin_type, admin_permission, createdAt)" +
                    "VALUES(?,?,?)",
                entity.getType(),
                entity.getPermission(),
                LocalDateTime.now()

        )==1;
    }


    // there is no dependencies for admin_type model so, it simply inserts to its table
    @Override
    public boolean insertWith(AdminType entity, Object... option) {return insert(entity);}


    @Override
    public boolean insertAll(List<AdminType> entities) {
        AtomicInteger size = new AtomicInteger(entities.size() - 1);
        entities.forEach(
                entity ->{
                    insert(entity);
                    size.getAndDecrement();
                });
        return size.getAcquire()<=0;
    }

    @Override
    public boolean update(AdminType entity) {
        return jdbcTemplate.update(
            "UPDATE admin_type SET admin_type=?, admin_permission=?, modifiedAt=? WHERE admin_type_id=? ",
                entity.getType(), entity.getPermission(), LocalDateTime.now(),
                entity.getId()
        )==1;
    }

    @Override
    public boolean delete(AdminType entity) {
        return jdbcTemplate.update(
                "UPDATE admin_type SET deletedAt=? WHERE admin_type_id=? ",
                LocalDateTime.now(),
                entity.getId()
                )==1;
    }



}
