package tech.lapsa.insurance.dao;

import java.util.Optional;

import javax.ejb.Local;

import com.lapsa.insurance.domain.crm.UserLogin;

import tech.lapsa.patterns.dao.GeneralDAO;
import tech.lapsa.patterns.dao.NotFound;

@Local
public interface UserLoginDAO extends GeneralDAO<UserLogin, Integer> {

    UserLogin getByName(String name) throws NotFound;

    default Optional<UserLogin> optionalByName(String name) {
	try {
	    return Optional.of(getByName(name));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }
}
