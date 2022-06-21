package tczr.projects.azstore.inventory;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tczr.projects.azstore.shared.Helper;
import tczr.projects.azstore.shared.Repo;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InventoryRepository implements Repo<Inventory> {

    private final RowMapper rowMapper = (res, rowNumber)->{
        Inventory inventory = new Inventory(
                res.getString("inventory_name"),
                res.getInt("inventory_quantity"),
                Helper.toLocalDateTime(res.getTimestamp("inventory_createdAt"))
        );
        inventory.setInventory_id(res.getInt("inventory_id"));
        inventory.setModifiedAt(Helper.toLocalDateTime(res.getTimestamp("inventory_modifiedAt")));
        inventory.setDeletedAt(Helper.toLocalDateTime(res.getTimestamp("inventory_deletedAt")));
        return inventory;
    };

    private final JdbcTemplate jdbcTemplate ;

    public InventoryRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    @Override
    public List<Inventory> getAll() {
        return jdbcTemplate.query("SELECT * FROM product_inventory",rowMapper);
    }

    @Override
    public Optional<Inventory> findBy(Object obj) {
        return  Optional.empty();
    }

    @Override
    public Optional<Inventory> findById(Integer ID) {
        Inventory inventory = (Inventory) jdbcTemplate.queryForObject(
                "SELECT * FROM product_inventory WHERE inventory_id = ? ",
                rowMapper,
                ID
        );

        return Optional.ofNullable(inventory);
    }

    @Override
    public boolean existsById(Integer primaryKey) {
        return ! findById(primaryKey).isEmpty();
    }

    @Override
    public boolean insert(Inventory entity) {
        return jdbcTemplate.update(
                "INSERT INTO product_inventory (inventory_name, inventory_quantity, inventory_createdAt)" +
                        " VALUES (?, ?, ?)",
                entity.getInventory_name(),
                entity.getQuantity(),
                entity.getCreatedAt()
        )==1;
    }

    @Override
    public boolean insertWith(Inventory entity, Object... option) {
        return insert(entity);
    }

    @Override
    public boolean insertAll(List<Inventory> entities) {
        AtomicInteger size = new AtomicInteger(entities.size());
        entities.forEach(
                entity -> {
                    insert(entity);
                    size.getAndDecrement();
                });
        return size.equals(0);
    }

    @Override
    public boolean update(Inventory entity) {
        return jdbcTemplate.update(
                "UPDATE product_inventory SET " +
                        "inventory_name=?," +
                        " inventory_quantity=?," +
                        " inventory_modifiedAt=? " +
                        "WHERE inventory_id=? " +
                entity.getInventory_name(),
                entity.getQuantity(),
                entity.getModifiedAt(),

                entity.getInventory_id()
        )==1;
    }

    @Override
    public boolean delete(Inventory entity) {
        return jdbcTemplate.update(
                "DELETE FROM product_inventory WHEHRE inventory_id=?",
                entity.getInventory_id()
        )==1;
    }
}
