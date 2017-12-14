package tech.lapsa.insurance.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.domain.crm.User;

import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.patterns.dao.GeneralDAO;
import tech.lapsa.patterns.dao.NotFound;

public interface UserDAO extends GeneralDAO<User, Integer> {

    @Local
    public interface UserDAOLocal extends UserDAO {
    }

    @Remote
    public interface UserDAORemote extends UserDAO {
    }

    User getByLogin(String login) throws IllegalArgument, NotFound;

    List<User> findAll();

    List<User> findVisible();

    List<User> findAllWhoCreatedRequest();

    List<User> findAllWithNoGroup();
}
