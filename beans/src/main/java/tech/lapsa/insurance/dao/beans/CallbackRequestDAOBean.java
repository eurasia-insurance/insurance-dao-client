package tech.lapsa.insurance.dao.beans;

import javax.ejb.Stateless;

import com.lapsa.insurance.domain.CallbackRequest;

import tech.lapsa.insurance.dao.CallbackRequestDAO.CallbackRequestDAOLocal;
import tech.lapsa.insurance.dao.CallbackRequestDAO.CallbackRequestDAORemote;

@Stateless
public class CallbackRequestDAOBean
	extends AGeneralRequestDAO<CallbackRequest>
	implements CallbackRequestDAOLocal, CallbackRequestDAORemote {

    public CallbackRequestDAOBean() {
	super(CallbackRequest.class);
    }
}
