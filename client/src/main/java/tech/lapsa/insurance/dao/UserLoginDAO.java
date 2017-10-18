package tech.lapsa.insurance.dao;

import java.util.Optional;

import javax.ejb.Local;

import com.lapsa.insurance.domain.crm.UserLogin;

@Local
public interface UserLoginDAO extends GeneralDAO<UserLogin, Integer> {

    UserLogin findByName(String name) throws NotFound;

    default Optional<UserLogin> optionalByName(String name) {
	try {
	    return Optional.of(findByName(name));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }
}
