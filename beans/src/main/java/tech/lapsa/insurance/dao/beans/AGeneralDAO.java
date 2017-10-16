package tech.lapsa.insurance.dao.beans;

import static com.lapsa.insurance.jpaUnit.InsuranceConstants.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tech.lapsa.insurance.dao.EntityNotFound;
import tech.lapsa.insurance.dao.GeneralDAO;
import tech.lapsa.insurance.dao.NotPersistedException;
import tech.lapsa.insurance.dao.PeristenceOperationFailed;

public abstract class AGeneralDAO<T, I> implements GeneralDAO<T, I> {

    protected static final String HINT_JAVAX_PERSISTENCE_CACHE_STORE_MODE = "javax.persistence.cache.storeMode";
    protected static final String HINT_JAVAX_PERSISTENCE_CACHE_RETREIVE_MODE = "javax.persistence.cache.retreiveMode";

    protected final Class<T> entityClass;
    protected final Logger logger = Logger.getLogger(this.getClass().getPackage().getName());

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    protected EntityManager em;

    protected AGeneralDAO(final Class<T> entityClazz) {
	this.entityClass = entityClazz;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public T findById(final I id) throws EntityNotFound, PeristenceOperationFailed {
	return findByIdHint(id, null);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public T findByIdByPassCache(final I id) throws EntityNotFound, PeristenceOperationFailed {
	Map<String, Object> hints = new HashMap<String, Object>();
	hints.put(HINT_JAVAX_PERSISTENCE_CACHE_RETREIVE_MODE, CacheRetrieveMode.BYPASS);
	hints.put(HINT_JAVAX_PERSISTENCE_CACHE_STORE_MODE, CacheStoreMode.REFRESH);
	return findByIdHint(id, hints);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <ET extends T> ET save(ET entity) throws PeristenceOperationFailed {
	try {
	    // em.flush();
	    ET merged = em.merge(entity);
	    em.flush();
	    return merged;
	} catch (Throwable e) {
	    throw new PeristenceOperationFailed(
		    String.format("Entity %1$s save failed", entityClass.getCanonicalName()), e);
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <ET extends T> ET restore(final ET entity) throws PeristenceOperationFailed, NotPersistedException {
	try {
	    ET merged = em.merge(entity);
	    em.refresh(merged);
	    return merged;
	} catch (EntityNotFoundException e) {
	    throw new NotPersistedException(
		    String.format("Entity is not persisted %1$s", entityClass.getCanonicalName()), e);
	} catch (Throwable e) {
	    throw new PeristenceOperationFailed(
		    String.format("Entity %1$s restore failed", entityClass.getCanonicalName()), e);
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveAll(List<T> entities) throws PeristenceOperationFailed {
	try {
	    em.flush();
	    for (int i = 0; i < entities.size(); i++) {
		T entity = entities.get(i);
		entities.set(i, em.merge(entity));
	    }
	    em.flush();
	} catch (Throwable e) {
	    throw new PeristenceOperationFailed(
		    String.format("Entities %1$s save failed", entityClass.getCanonicalName()), e);
	}
    }

    // PROTECTED

    protected <X> TypedQuery<X> putNoCacheHints(TypedQuery<X> query) {
	return query
		.setHint(HINT_JAVAX_PERSISTENCE_CACHE_RETREIVE_MODE, CacheRetrieveMode.BYPASS)
		.setHint(HINT_JAVAX_PERSISTENCE_CACHE_STORE_MODE, CacheStoreMode.REFRESH);
    }

    protected <X> List<X> resultListNoCached(TypedQuery<X> query) {
	return putNoCacheHints(query)
		.getResultList();
    }

    // PRIVATE

    private T findByIdHint(final I id, Map<String, Object> hints)
	    throws EntityNotFound, PeristenceOperationFailed {
	try {
	    T entity;
	    if (hints == null)
		entity = em.find(entityClass, id);
	    else
		entity = em.find(entityClass, id, hints);
	    if (entity != null)
		return entity;
	} catch (Throwable e) {
	    throw new PeristenceOperationFailed(
		    String.format("Entity %1$s findById failed", entityClass.getCanonicalName()), e);
	}
	throw new EntityNotFound(String.format("Not found %1$s with id = '%2$s'", entityClass.getSimpleName(), id));
    }

}
