package tech.lapsa.insurance.dao;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.domain.policy.Policy;

import tech.lapsa.patterns.dao.GeneralDAO;

public interface PolicyDAO extends GeneralDAO<Policy, Integer>, EJBConstants {

    public static final String BEAN_NAME = "PolicyDAOBean";

    @Local
    public interface PolicyDAOLocal extends PolicyDAO {
    }

    @Remote
    public interface PolicyDAORemote extends PolicyDAO {
    }
}
