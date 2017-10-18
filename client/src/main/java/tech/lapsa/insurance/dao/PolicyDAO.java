package tech.lapsa.insurance.dao;

import javax.ejb.Local;

import com.lapsa.insurance.domain.policy.Policy;

import tech.lapsa.patterns.dao.GeneralDAO;

@Local
public interface PolicyDAO extends GeneralDAO<Policy, Integer> {
}
