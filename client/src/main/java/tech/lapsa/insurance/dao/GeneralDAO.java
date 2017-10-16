package tech.lapsa.insurance.dao;

import java.util.List;

public interface GeneralDAO<T, I> {
    T findById(I id) throws EntityNotFound, PeristenceOperationFailed;

    T findByIdByPassCache(I id) throws EntityNotFound, PeristenceOperationFailed;

    <ET extends T> ET save(ET entity) throws PeristenceOperationFailed;

    <ET extends T> ET restore(ET entity) throws PeristenceOperationFailed, NotPersistedException;

    void saveAll(List<T> entities) throws PeristenceOperationFailed;

}
