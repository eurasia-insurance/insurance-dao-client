package tech.lapsa.insurance.dao;

import java.util.List;

import com.lapsa.insurance.domain.Request;
import com.lapsa.insurance.domain.crm.User;
import com.lapsa.insurance.elements.RequestStatus;

import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.patterns.dao.GeneralDAO;

public interface GeneralRequestDAO<T extends Request> extends GeneralDAO<T, Integer> {

    public interface GeneralRequestDAOLocal<T extends Request> extends GeneralRequestDAO<T> {
	List<T> findByStatus(RequestStatus status) throws IllegalArgument;

	List<T> findAllOpen();

	List<T> findAll();

	List<T> findByFilter(RequestFilter filter) throws IllegalArgument;

	List<T> findByFilter(RequestFilter filter, boolean showNoCreators, User... onlyCreators)
		throws IllegalArgument;
    }

    public interface GeneralRequestDAORemote<T extends Request> extends GeneralRequestDAO<T> {
	List<T> findByStatus(int from, int limit, RequestStatus status) throws IllegalArgument;

	List<T> findAllOpen(int from, int limit) throws IllegalArgument;

	List<T> findAll(int from, int limit) throws IllegalArgument;

	List<T> findByFilter(int from, int limit, RequestFilter filter) throws IllegalArgument;

	List<T> findByFilter(int from, int limit, RequestFilter filter, boolean showNoCreators, User... onlyCreators)
		throws IllegalArgument;
    }

}
