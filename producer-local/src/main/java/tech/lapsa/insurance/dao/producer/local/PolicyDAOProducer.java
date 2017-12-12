package tech.lapsa.insurance.dao.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.dao.EJBViaCDI;
import tech.lapsa.insurance.dao.PolicyDAO;
import tech.lapsa.insurance.dao.PolicyDAO.PolicyDAOLocal;

@Dependent
public class PolicyDAOProducer {

    @EJB
    private PolicyDAOLocal ejb;

    @Produces
    @EJBViaCDI
    public PolicyDAO getEjb() {
	return ejb;
    }
}
