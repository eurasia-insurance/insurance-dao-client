package tech.lapsa.insurance.dao.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.dao.CompanyPointOfSaleDAO;
import tech.lapsa.insurance.dao.CompanyPointOfSaleDAO.CompanyPointOfSaleDAORemote;
import tech.lapsa.insurance.dao.EJBViaCDI;

@Dependent
public class CompanyPointOfSaleDAOProducer {

    @EJB
    private CompanyPointOfSaleDAORemote ejb;

    @Produces
    @EJBViaCDI
    public CompanyPointOfSaleDAO getEjb() {
	return ejb;
    }
}
