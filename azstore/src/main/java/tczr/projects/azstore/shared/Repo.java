package tczr.projects.azstore.shared;

import java.util.List;
import java.util.Optional;

public interface Repo <T> {

    List<T> getAll();
//    List<T> findAllBy(Object object);

    Optional<T> findBy(Object obj);

    Optional<T> findById(Integer ID);

    boolean existsById(Integer primaryKey);

    boolean insert(T entity);
    @Deprecated
    boolean insertWith(T entity, Object...option);
    boolean insertAll(List<T> entities);

    boolean update(T entity);

    boolean delete(T entity);


}
