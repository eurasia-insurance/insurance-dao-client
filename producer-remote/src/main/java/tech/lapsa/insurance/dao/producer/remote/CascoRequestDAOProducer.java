package tech.lapsa.insurance.dao.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.dao.CascoRequestDAO;
import tech.lapsa.insurance.dao.CascoRequestDAO.CascoRequestDAORemote;
import tech.lapsa.insurance.dao.EJBViaCDI;

@Dependent
public class CascoRequestDAOProducer {

    @EJB
    private CascoRequestDAORemote ejb;

    @Produces
    @EJBViaCDI
    public CascoRequestDAO getEjb() {
	return ejb;
    }
}
