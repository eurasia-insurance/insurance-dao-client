package tech.lapsa.insurance.dao;

import javax.ejb.Local;

import com.lapsa.insurance.domain.crm.UserLogin;

@Local
public interface UserLoginDAO extends GeneralDAO<UserLogin, Integer> {
    UserLogin findByName(String name) throws PeristenceOperationFailed, EntityNotFound;
}
