package tczr.projects.azstore.admin;

import tczr.projects.azstore.admin.model.AdminType;
import tczr.projects.azstore.shared.Repo;

import java.util.List;
import java.util.Optional;

public class AdminTypeRepository implements Repo<AdminType> {
    @Override
    public List<AdminType> getAll() {
        return null;
    }

    @Override
    public List<AdminType> findAllById(Integer ID) {
        return null;
    }

    @Override
    public Optional<AdminType> findBy(Object obj) {
        return Optional.empty();
    }

    @Override
    public Optional<AdminType> findById(Integer ID) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer primaryKey) {
        return false;
    }

    @Override
    public boolean save(AdminType entity) {
        return false;
    }

    @Override
    public boolean saveInDifferentTables(AdminType entity, Object... option) {
        return false;
    }

    @Override
    public boolean saveAll(List<AdminType> entities) {
        return false;
    }

    @Override
    public boolean update(AdminType entity) {
        return false;
    }

    @Override
    public boolean delete(AdminType entity) {
        return false;
    }
}
