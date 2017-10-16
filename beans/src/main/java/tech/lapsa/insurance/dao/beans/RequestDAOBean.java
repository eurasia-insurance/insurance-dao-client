package tech.lapsa.insurance.dao.beans;

import javax.ejb.Stateless;

import com.lapsa.insurance.domain.Request;

import tech.lapsa.insurance.dao.RequestDAO;

@Stateless
public class RequestDAOBean extends AGeneralRequestDAO<Request>
	implements RequestDAO {

    public RequestDAOBean() {
	super(Request.class);
    }
}
