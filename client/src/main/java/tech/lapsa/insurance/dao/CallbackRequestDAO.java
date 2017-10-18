package tech.lapsa.insurance.dao;

import javax.ejb.Local;

import com.lapsa.insurance.domain.CallbackRequest;

@Local
public interface CallbackRequestDAO extends GeneralRequestDAO<CallbackRequest> {
}
