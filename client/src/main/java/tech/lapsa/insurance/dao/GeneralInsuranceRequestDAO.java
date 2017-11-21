package tech.lapsa.insurance.dao;

import java.util.List;

import com.lapsa.insurance.domain.InsuranceRequest;

public interface GeneralInsuranceRequestDAO<T extends InsuranceRequest> extends GeneralRequestDAO<T> {

    List<T> findByPaymentExternalId(String paymentReference);
}
