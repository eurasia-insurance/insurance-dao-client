package tech.lapsa.insurance.dao;

import java.util.List;

import com.lapsa.insurance.domain.Request;
import com.lapsa.insurance.domain.crm.User;
import com.lapsa.insurance.elements.RequestStatus;

import tech.lapsa.insurance.dao.filter.RequestFilter;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.patterns.dao.GeneralDAO;

public interface GeneralRequestDAO<T extends Request> extends GeneralDAO<T, Integer> {

    List<T> findByStatus(RequestStatus status) throws IllegalArgument;

    List<T> findAllOpen();

    List<T> findAll();

    List<T> findByFilter(RequestFilter filter) throws IllegalArgument;

    List<T> findByFilter(RequestFilter filter, boolean showNoCreators, User... onlyCreators) throws IllegalArgument;
}
