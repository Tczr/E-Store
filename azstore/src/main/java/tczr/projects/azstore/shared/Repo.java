package tczr.projects.azstore.shared;

import java.util.List;
import java.util.Optional;

public interface Repo <T> {

    List<T> getAll();
    List<T> findAllById(Integer ID);

    Optional<T> findBy(Object obj);

    Optional<T> findById(Integer ID);

    boolean existsById(Integer primaryKey);

    boolean save(T entity);
    boolean saveInDifferentTables(T entity, Object...option);
    boolean saveAll(List<T> entities);

    boolean update(T entity);

    boolean delete(T entity);

}
