package tech.lapsa.insurance.dao;

import javax.ejb.Local;

import com.lapsa.insurance.domain.InsuranceRequest;

@Local
public interface InsuranceRequestDAO extends GeneralInsuranceRequestDAO<InsuranceRequest> {
}
