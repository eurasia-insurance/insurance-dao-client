package tech.lapsa.insurance.dao.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.dao.EJBViaCDI;
import tech.lapsa.insurance.dao.UserDAO;
import tech.lapsa.insurance.dao.UserDAO.UserDAOLocal;

@Dependent
public class UserDAOProducer {

    @EJB
    private UserDAOLocal ejb;

    @Produces
    @EJBViaCDI
    public UserDAO getEjb() {
	return ejb;
    }
}
