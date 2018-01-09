package tech.lapsa.insurance.dao;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.java.commons.exceptions.IllegalState;

public interface InsuranceDAOPingService extends EJBConstants {

    public static final String BEAN_NAME = "InsuranceDAOPingServiceBean";

    @Local
    public interface InsuranceDAOPingServiceLocal extends InsuranceDAOPingService {
    }

    @Remote
    public interface InsuranceDAOPingServiceRemote extends InsuranceDAOPingService {
    }

    void ping() throws IllegalState;
}
