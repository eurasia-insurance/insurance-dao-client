package tech.lapsa.insurance.dao.beans;

import javax.ejb.Stateless;

import com.lapsa.insurance.domain.policy.PolicyRequest;

import tech.lapsa.insurance.dao.PolicyRequestDAO;

@Stateless
public class PolicyRequestDAOBean extends AGeneralInsuranceRequestDAO<PolicyRequest>
	implements PolicyRequestDAO {

    public PolicyRequestDAOBean() {
	super(PolicyRequest.class);
    }
}
