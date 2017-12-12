package tech.lapsa.insurance.dao;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.domain.policy.PolicyRequest;

public interface PolicyRequestDAO extends GeneralInsuranceRequestDAO<PolicyRequest> {

    @Local
    public interface PolicyRequestDAOLocal extends PolicyRequestDAO {
    }

    @Remote
    public interface PolicyRequestDAORemote extends PolicyRequestDAO {
    }
}
