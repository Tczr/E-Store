package tczr.projects.azstore.product;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import tczr.projects.azstore.product.model.Product;
import tczr.projects.azstore.shared.Repo;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductRepository implements Repo<Product> {

    private static final String TEXT_REGEX ="\\D";
    private final JdbcTemplate jdbcTemplate;

    private RowMapper rowMapper = (res, numberRow)->{
        Product product=new Product();
        product.setProduct_id(res.getInt("product_id"));
        product.setProduct_name(res.getString("product_name"));
        product.setProduct_details(res.getString("product_description"));
        product.setProduct_stock(res.getFloat("product_stock"));
        product.setProduct_unlimited(res.getBoolean("product_unlimited"));
        product.setProduct_weight(res.getFloat("product_weight"));
        product.setProduct_availability(res.getBoolean("product_availability"));
        product.setInsertedAt(res.getTimestamp("createdAt").toLocalDateTime());
        product.setModifiedAt(res.getTimestamp("modifiedAt").toLocalDateTime());
//        product.setCategory();
//        product.setInventory();
//        product.setImages();
        return product;
    };

    public ProductRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}


    @Override
    public List<Product> getAll() {
        return jdbcTemplate.query("SELECT * FROM product", rowMapper);
    }

    /*@Override
    public List<Product> findAllBy(Object productType) {
       return selectQueryOf();
    }*/

    @Override
    public Optional<Product> findBy(Object obj) {
        return findById((Integer)obj);
    }

    @Override
    public Optional<Product> findById(Integer ID) {
        Product product = (Product) jdbcTemplate.queryForObject(
                "SELECT * FROM product WHERE product_id=?",
                rowMapper,
                ID

        );

        return Optional.ofNullable(product);
    }

    @Override
    public boolean existsById(Integer primaryKey) {
        return ! findById(primaryKey).isEmpty();
    }

    @Override
    public boolean insert(Product entity) {
        return jdbcTemplate.update(
                "INSERT INTO product(" +
                        "product_id, inventory_id, category_id, discount_id" +
                        "product_name, product_description, price," +
                        "product_weight, product_stock, product_availability,  product_unlimited" +
                        "insertedAt ) VALUES" +
                        " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getProduct_id(),
                entity.getInventory().getInventory_id(),
                entity.getCategory().getCategory_id(),
                entity.getDiscount().getDiscount_id(),
                entity.getProduct_name(),
                entity.getProduct_details(),
                entity.getPrice(),
                entity.getProduct_weight(),
                entity.getProduct_stock(),
                entity.getProduct_availability(),
                entity.getProduct_unlimited(),
                entity.getInsertedAt()
        )==1;
    }

    @Override
    public boolean insertWith(Product entity, Object... option) {
        return false;
    }

    @Override
    public boolean insertAll(List<Product> entities) {
        AtomicInteger size = new AtomicInteger(entities.size());
        entities.forEach(
                entity->{
                    insert(entity);
                    size.getAndDecrement();
                });
        return size.equals(0);
    }

    @Override
    public boolean update(Product entity) {
        return  jdbcTemplate.update(
                "UPDATE product SET" +
                        "product_id=?, inventory_id=?, category_id=?, discount_id=?" +
                        "product_name=?, product_description=?, price=?," +
                        "product_weight=?, product_stock=?, product_availability=?,  product_unlimited=?" +
                        "insertedAt=?, modifiedAt=? " +
                        "WHERE product_id=?",
                entity.getProduct_id(),
                entity.getInventory().getInventory_id(),
                entity.getCategory().getCategory_id(),
                entity.getDiscount().getDiscount_id(),
                entity.getProduct_name(),
                entity.getProduct_details(),
                entity.getPrice(),
                entity.getProduct_weight(),
                entity.getProduct_stock(),
                entity.getProduct_availability(),
                entity.getProduct_unlimited(),
                entity.getInsertedAt(),
                entity.getModifiedAt(),

                // Where id statement
                entity.getProduct_id()

                )==1;
    }

    @Override
    public boolean delete(Product entity) {
        return jdbcTemplate.update(
                "DELETE FROM product WHERE product_id=?",
                entity.getProduct_id()
        )==1;
    }
}
