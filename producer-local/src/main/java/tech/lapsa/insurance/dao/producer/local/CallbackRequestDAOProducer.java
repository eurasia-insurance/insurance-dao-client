package tech.lapsa.insurance.dao.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.dao.CallbackRequestDAO;
import tech.lapsa.insurance.dao.CallbackRequestDAO.CallbackRequestDAOLocal;
import tech.lapsa.insurance.dao.EJBViaCDI;

@Dependent
public class CallbackRequestDAOProducer {

    @EJB
    private CallbackRequestDAOLocal ejb;

    @Produces
    @EJBViaCDI
    public CallbackRequestDAO getEjb() {
	return ejb;
    }
}
