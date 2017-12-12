package tech.lapsa.insurance.dao.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.dao.EJBViaCDI;
import tech.lapsa.insurance.dao.PolicyRequestDAO;
import tech.lapsa.insurance.dao.PolicyRequestDAO.PolicyRequestDAORemote;

@Dependent
public class PolicyRequestDAOProducer {

    @EJB
    private PolicyRequestDAORemote ejb;

    @Produces
    @EJBViaCDI
    public PolicyRequestDAO getEjb() {
	return ejb;
    }
}
