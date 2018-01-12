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

	List<T> findByFilter(RequestFilter filter, RequestSort sort, boolean showNoCreators, User... onlyCreators)
		throws IllegalArgument;
    }

    public interface GeneralRequestDAORemote<T extends Request> extends GeneralRequestDAO<T> {

	List<T> findByStatus(int from, int limit, RequestStatus status) throws IllegalArgument;

	List<T> findAllOpen(int from, int limit) throws IllegalArgument;

	ListWithStats<T> findAllOpenWithStats(int from, int limit) throws IllegalArgument;

	List<T> findAll(int from, int limit) throws IllegalArgument;

	ListWithStats<T> findAllWithStats(int from, int limit) throws IllegalArgument;

	List<T> findByFilter(int from, int limit, RequestFilter requestFilter) throws IllegalArgument;

	ListWithStats<T> findByFilterWithStats(int from, int limit, RequestFilter requestFilter, RequestSort sort)
		throws IllegalArgument;

	List<T> findByFilter(int from, int limit, RequestFilter filter, RequestSort sort, boolean showNoCreators,
		User... onlyCreators) throws IllegalArgument;

	ListWithStats<T> findByFilterWithStats(int from, int limit, RequestFilter filter, RequestSort sort,
		boolean showNoCreators, User... onlyCreators) throws IllegalArgument;
    }

    Long countAll();

    Long countAllOpen();

    Long countByStatus(RequestStatus status) throws IllegalArgument;

    Long countByFilter(RequestFilter filter) throws IllegalArgument;

    Long countByFilter(RequestFilter filter, boolean showNoCreators, User... onlyCreators) throws IllegalArgument;

}
