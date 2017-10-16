package tech.lapsa.insurance.dao;

import java.util.List;

import javax.ejb.Local;

import com.lapsa.insurance.domain.crm.User;

@Local
public interface UserDAO extends GeneralDAO<User, Integer> {
    User findByLogin(String login) throws PeristenceOperationFailed, EntityNotFound;

    List<User> findAll() throws PeristenceOperationFailed;

    List<User> findVisible() throws PeristenceOperationFailed;

    List<User> findAllWhoCreatedRequest() throws PeristenceOperationFailed;

    List<User> findAllWithNoGroup() throws PeristenceOperationFailed;
}
