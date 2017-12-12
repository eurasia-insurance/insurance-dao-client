package tech.lapsa.insurance.dao.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.dao.EJBViaCDI;
import tech.lapsa.insurance.dao.RequestDAO;
import tech.lapsa.insurance.dao.RequestDAO.RequestDAORemote;

@Dependent
public class RequestDAOProducer {

    @EJB
    private RequestDAORemote ejb;

    @Produces
    @EJBViaCDI
    public RequestDAO getEjb() {
	return ejb;
    }
}
