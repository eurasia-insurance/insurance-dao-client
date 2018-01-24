package tech.lapsa.insurance.dao;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.domain.CallbackRequest;

public interface CallbackRequestDAO extends GeneralInsuranceRequestDAO<CallbackRequest>, EJBConstants {

    public static final String BEAN_NAME = "CallbackRequestDAOBean";

    @Local
    public interface CallbackRequestDAOLocal extends CallbackRequestDAO, GeneralInsuranceRequestDAOLocal<CallbackRequest> {
    }

    @Remote
    public interface CallbackRequestDAORemote extends CallbackRequestDAO, GeneralInsuranceRequestDAORemote<CallbackRequest> {
    }
}
