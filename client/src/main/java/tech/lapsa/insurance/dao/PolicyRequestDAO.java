package tech.lapsa.insurance.dao;

import javax.ejb.Local;

import com.lapsa.insurance.domain.policy.PolicyRequest;

@Local
public interface PolicyRequestDAO extends GeneralInsuranceRequestDAO<PolicyRequest> {
}
