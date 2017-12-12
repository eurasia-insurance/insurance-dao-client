package tech.lapsa.insurance.dao.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.dao.CallbackRequestDAO;
import tech.lapsa.insurance.dao.CallbackRequestDAO.CallbackRequestDAORemote;
import tech.lapsa.insurance.dao.EJBViaCDI;

@Dependent
public class CallbackRequestDAOProducer {

    @EJB
    private CallbackRequestDAORemote ejb;

    @Produces
    @EJBViaCDI
    public CallbackRequestDAO getEjb() {
	return ejb;
    }
}
