package tech.lapsa.insurance.dao;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public interface GeneralDAO<T, I> {

    T findById(I id) throws NotFound;

    default Optional<T> optionalById(I id) {
	try {
	    return Optional.of(findById(id));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }

    T findByIdByPassCache(I id) throws NotFound;

    default Optional<T> optionalByIdByPassCache(I id) throws NotFound {
	try {
	    return Optional.of(findByIdByPassCache(id));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }

    <ET extends T> ET save(ET entity);

    <ET extends T> ET restore(ET entity) throws NotFound;

    default <ET extends T> Collection<ET> saveAll(Collection<ET> entities) {
	return entities.stream() //
		.map(this::save) //
		.collect(Collectors.toList());
    }
}
