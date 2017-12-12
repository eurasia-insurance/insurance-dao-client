package tech.lapsa.insurance.dao.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.dao.EJBViaCDI;
import tech.lapsa.insurance.dao.PolicyRequestDAO;
import tech.lapsa.insurance.dao.PolicyRequestDAO.PolicyRequestDAOLocal;

@Dependent
public class PolicyRequestDAOProducer {

    @EJB
    private PolicyRequestDAOLocal ejb;

    @Produces
    @EJBViaCDI
    public PolicyRequestDAO getEjb() {
	return ejb;
    }
}
