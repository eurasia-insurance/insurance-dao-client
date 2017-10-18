package tech.lapsa.insurance.dao.beans;

import javax.ejb.Stateless;

import com.lapsa.insurance.domain.policy.Policy;

import tech.lapsa.insurance.dao.PolicyDAO;

@Stateless
public class PolicyDAOBean extends AEntityManagerDAO<Policy, Integer> implements PolicyDAO {

    public PolicyDAOBean() {
	super(Policy.class);
    }
}
