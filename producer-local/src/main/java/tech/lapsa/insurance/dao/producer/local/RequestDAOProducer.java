package tech.lapsa.insurance.dao.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.dao.EJBViaCDI;
import tech.lapsa.insurance.dao.RequestDAO;
import tech.lapsa.insurance.dao.RequestDAO.RequestDAOLocal;

@Dependent
public class RequestDAOProducer {

    @EJB
    private RequestDAOLocal ejb;

    @Produces
    @EJBViaCDI
    public RequestDAO getEjb() {
	return ejb;
    }
}
