package tech.lapsa.insurance.dao.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.dao.EJBViaCDI;
import tech.lapsa.insurance.dao.UserDAO;
import tech.lapsa.insurance.dao.UserDAO.UserDAORemote;

@Dependent
public class UserDAOProducer {

    @EJB
    private UserDAORemote ejb;

    @Produces
    @EJBViaCDI
    public UserDAO getEjb() {
	return ejb;
    }
}
