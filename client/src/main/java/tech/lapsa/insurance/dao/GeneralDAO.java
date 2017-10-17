package tech.lapsa.insurance.dao;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import tech.lapsa.java.commons.function.MyObjects;

public interface GeneralDAO<T, I> {

    default T findById(I id) throws EntityNotFound {
	return optionalById(id) //
		.orElseThrow(() -> new EntityNotFound(
			String.format("Entity %1$s with id = '%2$s' is not found", id.getClass().getName(), id)));
    }

    Optional<T> optionalById(I id);

    default T findByIdByPassCache(I id) throws EntityNotFound {
	return optionalByIdByPassCache(id) //
		.orElseThrow(() -> new EntityNotFound(
			String.format("Entity %1$s with id = '%2$s' is not found", id.getClass().getName(), id)));
    }

    Optional<T> optionalByIdByPassCache(I id);

    <ET extends T> ET save(ET entity) throws PeristenceOperationFailed;

    <ET extends T> ET restore(ET entity) throws PeristenceOperationFailed, NotPersistedException;

    default <ET extends T> Collection<ET> saveAll(Collection<ET> entities) throws PeristenceOperationFailed {
	return MyObjects.requireNonNull(entities, "entities").stream() //
		.map(this::save) //
		.collect(Collectors.toList());
    }
}
