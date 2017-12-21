package tech.lapsa.insurance.dao.beans;

import javax.ejb.Stateless;

import com.lapsa.insurance.domain.policy.Policy;

import tech.lapsa.insurance.dao.PolicyDAO;
import tech.lapsa.insurance.dao.PolicyDAO.PolicyDAOLocal;
import tech.lapsa.insurance.dao.PolicyDAO.PolicyDAORemote;

@Stateless(name = PolicyDAO.BEAN_NAME)
public class PolicyDAOBean
	extends ABaseDAO<Policy, Integer>
	implements PolicyDAOLocal, PolicyDAORemote {

    public PolicyDAOBean() {
	super(Policy.class);
    }
}
