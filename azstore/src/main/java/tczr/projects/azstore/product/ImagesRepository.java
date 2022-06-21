package tczr.projects.azstore.product;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tczr.projects.azstore.product.model.Image;
import tczr.projects.azstore.shared.Repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
@Repository
public class ImagesRepository implements Repo<Image> {

    private final RowMapper rowMapper = (res, rowNumber)->{
        Image image = new Image(
                res.getInt("image_id"),
                res.getString("url")
        );
        return image;
    };

    private final JdbcTemplate jdbcTemplate;
    public ImagesRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    @Override
    public List<Image> getAll() {
        return jdbcTemplate.query(
                "SELECT image_id, url FROM product_image",
                rowMapper);
    }

    @Override
    public Optional<Image> findBy(Object obj) {
        return Optional.empty();
    }

    @Override
    public Optional<Image> findById(Integer ID) {
        Image image = (Image) jdbcTemplate.queryForObject(
                "SELECT image_id, url FROM product_image WHERE image_id=?",
                rowMapper,
                ID
        );
        return Optional.ofNullable(image);
    }

    @Override
    public boolean existsById(Integer primaryKey) {
        return !findById(primaryKey).isEmpty();
    }

    @Override
    public boolean insert(Image entity) {
        return false;
    }

    @Override
    public boolean insertWith(Image entity, Object... option) {
        return false;
    }

    @Override
    public boolean insertAll(List<Image> entities) {
        AtomicInteger size =new AtomicInteger(entities.size());
        entities.forEach(
                entity->{
                    insert(entity);
                    size.getAndDecrement();
                });

        return size.equals(0);
    }

    @Override
    public boolean update(Image entity) {
        return jdbcTemplate.update(
                "UPDATE product_image  SET url, modifiedAt",
                entity.getImage_url(),
                LocalDateTime.now()
                )==1;
    }

    @Override
    public boolean delete(Image entity) {
        return jdbcTemplate.update(
                "DELETE FROM product_image WHERE image_id=?",
                entity.getImage_id()
        )==1;
    }
}
