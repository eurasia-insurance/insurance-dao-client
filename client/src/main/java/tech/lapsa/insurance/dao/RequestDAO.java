package tech.lapsa.insurance.dao;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.domain.Request;

public interface RequestDAO extends GeneralRequestDAO<Request>, EJBConstants {

    @Local
    public interface RequestDAOLocal extends RequestDAO {
    }

    @Remote
    public interface RequestDAORemote extends RequestDAO {
    }
}
