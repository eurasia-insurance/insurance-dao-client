package tech.lapsa.insurance.dao;

import javax.ejb.Local;

import com.lapsa.insurance.domain.policy.Policy;

@Local
public interface PolicyDAO extends GeneralDAO<Policy, Integer> {
}
