package tech.lapsa.insurance.dao.beans;

import javax.ejb.Stateless;

import com.lapsa.insurance.domain.InsuranceRequest;

import tech.lapsa.insurance.dao.InsuranceRequestDAO;

@Stateless
public class InsuranceRequestDAOBean extends AGeneralInsuranceRequestDAO<InsuranceRequest>
	implements InsuranceRequestDAO {

    public InsuranceRequestDAOBean() {
	super(InsuranceRequest.class);
    }
}
