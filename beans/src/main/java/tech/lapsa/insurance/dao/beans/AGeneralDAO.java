package tech.lapsa.insurance.dao.beans;

import static com.lapsa.insurance.jpaUnit.InsuranceConstants.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tech.lapsa.insurance.dao.GeneralDAO;
import tech.lapsa.insurance.dao.NotPersistedException;
import tech.lapsa.insurance.dao.PeristenceOperationFailed;
import tech.lapsa.java.commons.function.MyMaps;
import tech.lapsa.java.commons.function.MyObjects;

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
    public Optional<T> optionalById(I id) {
	return optionalByIdAndHint(id, null);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Optional<T> optionalByIdByPassCache(I id) {
	return optionalByIdAndHint(id, MyMaps.of( //
		HINT_JAVAX_PERSISTENCE_CACHE_RETREIVE_MODE, CacheRetrieveMode.BYPASS, //
		HINT_JAVAX_PERSISTENCE_CACHE_STORE_MODE, CacheStoreMode.REFRESH //
	));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <ET extends T> ET save(final ET entity) throws PeristenceOperationFailed {
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
    public <ET extends T> Collection<ET> saveAll(final Collection<ET> entities) throws PeristenceOperationFailed {
	MyObjects.requireNonNull(entities, "entities");
	em.flush();
	Collection<ET> ret = GeneralDAO.super.saveAll(entities);
	em.flush();
	return ret;
    }

    // PROTECTED

    protected <X> TypedQuery<X> putNoCacheHints(final TypedQuery<X> query) {
	return query
		.setHint(HINT_JAVAX_PERSISTENCE_CACHE_RETREIVE_MODE, CacheRetrieveMode.BYPASS)
		.setHint(HINT_JAVAX_PERSISTENCE_CACHE_STORE_MODE, CacheStoreMode.REFRESH);
    }

    protected <X> List<X> resultListNoCached(final TypedQuery<X> query) {
	return putNoCacheHints(query)
		.getResultList();
    }

    // PRIVATE

    private Optional<T> optionalByIdAndHint(final I id, Map<String, Object> hints) {
	Objects.requireNonNull(id, "id");
	return Optional.ofNullable(MyObjects.nonNull(hints) //
		? em.find(entityClass, id, hints) //
		: em.find(entityClass, id));
    }
}
