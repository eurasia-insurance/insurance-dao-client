package tech.lapsa.insurance.dao;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.domain.Request;

public interface RequestDAO extends GeneralRequestDAO<Request>, EJBConstants {

    public static final String BEAN_NAME = "RequestDAOBean";

    @Local
    public interface RequestDAOLocal extends RequestDAO, GeneralRequestDAOLocal<Request> {
    }

    @Remote
    public interface RequestDAORemote extends RequestDAO, GeneralRequestDAORemote<Request> {
    }
}
