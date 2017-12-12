package tech.lapsa.insurance.dao.beans;

import javax.ejb.Stateless;

import com.lapsa.insurance.domain.InsuranceRequest;

import tech.lapsa.insurance.dao.InsuranceRequestDAO.InsuranceRequestDAOLocal;
import tech.lapsa.insurance.dao.InsuranceRequestDAO.InsuranceRequestDAORemote;

@Stateless
public class InsuranceRequestDAOBean
	extends AGeneralInsuranceRequestDAO<InsuranceRequest>
	implements InsuranceRequestDAOLocal, InsuranceRequestDAORemote {

    public InsuranceRequestDAOBean() {
	super(InsuranceRequest.class);
    }
}
