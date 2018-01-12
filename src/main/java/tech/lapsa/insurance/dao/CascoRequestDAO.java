package tech.lapsa.insurance.dao;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.domain.casco.CascoRequest;

public interface CascoRequestDAO extends GeneralInsuranceRequestDAO<CascoRequest>, EJBConstants {

    public static final String BEAN_NAME = "CascoRequestDAOBean";

    @Local
    public interface CascoRequestDAOLocal extends CascoRequestDAO, GeneralInsuranceRequestDAOLocal<CascoRequest> {
    }

    @Remote
    public interface CascoRequestDAORemote extends CascoRequestDAO, GeneralInsuranceRequestDAORemote<CascoRequest> {
    }
}
