package tech.lapsa.insurance.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Local;

import com.lapsa.insurance.domain.crm.User;

@Local
public interface UserDAO extends GeneralDAO<User, Integer> {

    User findByLogin(String login) throws NotFound;

    default Optional<User> optionalByLogin(String login) {
	try {
	    return Optional.of(findByLogin(login));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }

    List<User> findAll();

    List<User> findVisible();

    List<User> findAllWhoCreatedRequest();

    List<User> findAllWithNoGroup();
}
