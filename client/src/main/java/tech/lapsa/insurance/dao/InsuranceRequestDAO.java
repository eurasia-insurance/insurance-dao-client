package tech.lapsa.insurance.dao;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.domain.InsuranceRequest;

public interface InsuranceRequestDAO extends GeneralInsuranceRequestDAO<InsuranceRequest>, EJBConstants {

    @Local
    public interface InsuranceRequestDAOLocal
	    extends InsuranceRequestDAO, GeneralInsuranceRequestDAOLocal<InsuranceRequest> {
    }

    @Remote
    public interface InsuranceRequestDAORemote
	    extends InsuranceRequestDAO, GeneralInsuranceRequestDAORemote<InsuranceRequest> {
    }
}
