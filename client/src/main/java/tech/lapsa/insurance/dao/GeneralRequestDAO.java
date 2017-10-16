package tech.lapsa.insurance.dao;

import java.util.List;

import com.lapsa.insurance.domain.Request;
import com.lapsa.insurance.domain.crm.User;
import com.lapsa.insurance.elements.RequestStatus;

import tech.lapsa.insurance.dao.filter.RequestFilter;

public interface GeneralRequestDAO<T extends Request> extends GeneralDAO<T, Integer> {

    List<T> findByStatus(RequestStatus status) throws PeristenceOperationFailed;

    List<T> findAllOpen() throws PeristenceOperationFailed;

    List<T> findAll() throws PeristenceOperationFailed;

    List<T> findByFilter(RequestFilter filter) throws PeristenceOperationFailed;

    List<T> findByFilter(RequestFilter filter, boolean showNoCreators, User... onlyCreators)
	    throws PeristenceOperationFailed;
}
