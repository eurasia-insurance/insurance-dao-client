package tech.lapsa.insurance.dao;

import javax.ejb.Local;

import com.lapsa.insurance.domain.casco.CascoRequest;

@Local
public interface CascoRequestDAO extends GeneralInsuranceRequestDAO<CascoRequest> {
}
