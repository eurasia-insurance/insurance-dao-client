package tech.lapsa.insurance.dao.beans;

import javax.ejb.Stateless;

import com.lapsa.insurance.domain.CallbackRequest;

import tech.lapsa.insurance.dao.CallbackRequestDAO;

@Stateless
public class CallbackRequestDAOBean extends AGeneralRequestDAO<CallbackRequest> implements CallbackRequestDAO {

    public CallbackRequestDAOBean() {
	super(CallbackRequest.class);
    }
}
