package tech.lapsa.insurance.dao;

import java.util.List;

import com.lapsa.insurance.domain.InsuranceRequest;

import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface GeneralInsuranceRequestDAO<T extends InsuranceRequest> extends GeneralRequestDAO<T> {

    List<T> findByPaymentInvoiceNumber(String invoiceNumber) throws IllegalArgument;
}
