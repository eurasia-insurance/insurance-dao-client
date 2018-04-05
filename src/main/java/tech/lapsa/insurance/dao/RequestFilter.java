package tech.lapsa.insurance.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import com.lapsa.insurance.domain.crm.User;
import com.lapsa.insurance.elements.InsuranceRequestType;
import com.lapsa.insurance.elements.PaymentStatus;
import com.lapsa.insurance.elements.ProgressStatus;
import com.lapsa.insurance.elements.RequestStatus;
import com.lapsa.insurance.elements.TransactionProblem;
import com.lapsa.insurance.elements.TransactionStatus;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

public class RequestFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    // Request properties

    private Integer id;
    private String requesterNameMask;

    private RequestStatus requestStatus;
    private ProgressStatus progressStatus;

    private LocalDateTime createdAfter;
    private LocalDateTime createdBefore;
    private LocalDateTime completedAfter;
    private LocalDateTime completedBefore;

    private User createdBy;
    private User acceptedBy;
    private User completedBy;
    private User closedBy;

    // InsuranceRequest properties

    private InsuranceRequestType insuranceRequestType;
    private PaymentStatus paymentStatus;
    private String paymentInvoiceNumber;
    private String paymentReference;
    private String paymentMethodNameMask;
    private TransactionStatus transactionStatus;
    private String agreementNumberMask;
    private TransactionProblem transactionProblem;

    private TaxpayerNumber requesterTaxpayerNumber;

    public RequestFilter() {
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getRequesterNameMask() {
	return requesterNameMask;
    }

    public void setRequesterNameMask(String requesterNameMask) {
	this.requesterNameMask = requesterNameMask;
    }

    public RequestStatus getRequestStatus() {
	return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
	this.requestStatus = requestStatus;
    }

    public ProgressStatus getProgressStatus() {
	return progressStatus;
    }

    public void setProgressStatus(ProgressStatus progressStatus) {
	this.progressStatus = progressStatus;
    }

    public LocalDateTime getCreatedAfter() {
	return createdAfter;
    }

    public void setCreatedAfter(LocalDateTime createdAfter) {
	this.createdAfter = createdAfter;
    }

    public LocalDateTime getCreatedBefore() {
	return createdBefore;
    }

    public void setCreatedBefore(LocalDateTime createdBefore) {
	this.createdBefore = createdBefore;
    }

    public LocalDateTime getCompletedAfter() {
	return completedAfter;
    }

    public void setCompletedAfter(LocalDateTime completedAfter) {
	this.completedAfter = completedAfter;
    }

    public LocalDateTime getCompletedBefore() {
	return completedBefore;
    }

    public void setCompletedBefore(LocalDateTime completedBefore) {
	this.completedBefore = completedBefore;
    }

    public User getAcceptedBy() {
	return acceptedBy;
    }

    public void setAcceptedBy(User acceptedBy) {
	this.acceptedBy = acceptedBy;
    }

    public User getCompletedBy() {
	return completedBy;
    }

    public void setCompletedBy(User completedBy) {
	this.completedBy = completedBy;
    }

    public InsuranceRequestType getRequestType() {
	return insuranceRequestType;
    }

    public Optional<InsuranceRequestType> optionalRequestType() {
	return MyOptionals.of(getRequestType());
    }

    public void setRequestType(InsuranceRequestType insuranceRequestType) {
	this.insuranceRequestType = insuranceRequestType;
    }

    public PaymentStatus getPaymentStatus() {
	return paymentStatus;
    }

    public Optional<PaymentStatus> optionalPaymentStatus() {
	return MyOptionals.of(getPaymentStatus());
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
	this.paymentStatus = paymentStatus;
    }

    public String getPaymentInvoiceNumber() {
	return paymentInvoiceNumber;
    }

    public Optional<String> optionalPaymentInvoiceNumber() {
	return MyOptionals.of(getPaymentInvoiceNumber());
    }

    public void setPaymentInvoiceNumber(String paymentInvoiceNumber) {
	this.paymentInvoiceNumber = paymentInvoiceNumber;
    }

    public String getPaymentReference() {
	return paymentReference;
    }

    public Optional<String> optPaymentReference() {
	return MyOptionals.of(paymentReference);
    }

    public void setPaymentReference(String paymentReference) {
	this.paymentReference = paymentReference;
    }

    public TransactionStatus getTransactionStatus() {
	return transactionStatus;
    }

    public Optional<TransactionStatus> optionalTransactionStatus() {
	return MyOptionals.of(getTransactionStatus());
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
	this.transactionStatus = transactionStatus;
    }

    public String getAgreementNumberMask() {
	return agreementNumberMask;
    }

    public void setAgreementNumberMask(String agreementNumberMask) {
	this.agreementNumberMask = agreementNumberMask;
    }

    public TransactionProblem getTransactionProblem() {
	return transactionProblem;
    }

    public Optional<TransactionProblem> optionalTransactionProblem() {
	return MyOptionals.of(getTransactionProblem());
    }

    public void setTransactionProblem(TransactionProblem transactionProblem) {
	this.transactionProblem = transactionProblem;
    }

    public User getCreatedBy() {
	return createdBy;
    }

    public User getClosedBy() {
	return closedBy;
    }

    public void setCreatedBy(User createdBy) {
	this.createdBy = createdBy;
    }

    public void setClosedBy(User closedBy) {
	this.closedBy = closedBy;
    }

    public TaxpayerNumber getRequesterTaxpayerNumber() {
	return requesterTaxpayerNumber;
    }

    public Optional<TaxpayerNumber> optionalRequesterTaxpayerNumber() {
	return MyOptionals.of(getRequesterTaxpayerNumber());
    }

    public void setRequesterTaxpayerNumber(TaxpayerNumber requesterTaxpayerNumber) {
	this.requesterTaxpayerNumber = requesterTaxpayerNumber;
    }

    public ZoneId getZoneId() {
	return ZoneId.systemDefault();
    }

    public String getPaymentMethodNameMask() {
        return paymentMethodNameMask;
    }

    public void setPaymentMethodNameMask(String paymentMethodNameMask) {
        this.paymentMethodNameMask = paymentMethodNameMask;
    }
}
