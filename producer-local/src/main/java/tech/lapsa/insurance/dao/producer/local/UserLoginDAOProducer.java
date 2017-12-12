package tech.lapsa.insurance.dao.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.dao.EJBViaCDI;
import tech.lapsa.insurance.dao.UserLoginDAO;
import tech.lapsa.insurance.dao.UserLoginDAO.UserLoginDAOLocal;

@Dependent
public class UserLoginDAOProducer {

    @EJB
    private UserLoginDAOLocal ejb;

    @Produces
    @EJBViaCDI
    public UserLoginDAO getEjb() {
	return ejb;
    }
}
