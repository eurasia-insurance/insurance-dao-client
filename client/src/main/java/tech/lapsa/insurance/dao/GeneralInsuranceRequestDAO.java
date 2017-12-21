package tech.lapsa.insurance.dao;

import java.util.List;

import com.lapsa.insurance.domain.InsuranceRequest;

import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface GeneralInsuranceRequestDAO<T extends InsuranceRequest> extends GeneralRequestDAO<T> {

    public interface GeneralInsuranceRequestDAOLocal<T extends InsuranceRequest>
	    extends GeneralInsuranceRequestDAO<T>, GeneralRequestDAOLocal<T> {

	List<T> findByPaymentInvoiceNumber(String invoiceNumber) throws IllegalArgument;
    }

    public interface GeneralInsuranceRequestDAORemote<T extends InsuranceRequest>
	    extends GeneralInsuranceRequestDAO<T>, GeneralRequestDAORemote<T> {

	List<T> findByPaymentInvoiceNumber(int from, int limit, String invoiceNumber) throws IllegalArgument;
    }

}
