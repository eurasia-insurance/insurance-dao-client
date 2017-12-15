package tech.lapsa.insurance.dao;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.domain.crm.UserLogin;

import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.patterns.dao.GeneralDAO;
import tech.lapsa.patterns.dao.NotFound;

public interface UserLoginDAO extends GeneralDAO<UserLogin, Integer>, EJBConstants {

    @Local
    public interface UserLoginDAOLocal extends UserLoginDAO {
    }

    @Remote
    public interface UserLoginDAORemote extends UserLoginDAO {
    }

    UserLogin getByName(String name) throws IllegalArgument, NotFound;
}
