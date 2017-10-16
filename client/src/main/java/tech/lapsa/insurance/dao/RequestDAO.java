package tech.lapsa.insurance.dao;

import javax.ejb.Local;

import com.lapsa.insurance.domain.Request;

@Local
public interface RequestDAO extends GeneralRequestDAO<Request> {
}
