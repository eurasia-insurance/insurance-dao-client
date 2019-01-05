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
import com.lapsa.insurance.elements.RequestCancelationReason;
import com.lapsa.insurance.elements.ContractStatus;

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
    private User pickedBy;
    private User completedBy;
    private User closedBy;

    // InsuranceRequest properties

    private InsuranceRequestType insuranceRequestType;
    private PaymentStatus paymentStatus;
    private String invoiceNumber;
    private String paymentReference;
    private String paymentMethodNameMask;
    private String paymentCard;
    private String paymentCardBank;
    private ContractStatus contractStatus;
    private String agreementNumberMask;
    private RequestCancelationReason requestCancelationReason;

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

    public User getPickedBy() {
	return pickedBy;
    }

    public void setPickedBy(User pickedBy) {
	this.pickedBy = pickedBy;
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

    public String getInvoiceNumber() {
	return invoiceNumber;
    }

    public Optional<String> optionalInvoiceNumber() {
	return MyOptionals.of(getInvoiceNumber());
    }

    public void setInvoiceNumber(String invoiceNumber) {
	this.invoiceNumber = invoiceNumber;
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

    public ContractStatus getContractStatus() {
	return contractStatus;
    }

    public Optional<ContractStatus> optionalContractStatus() {
	return MyOptionals.of(getContractStatus());
    }

    public void setContractStatus(ContractStatus contractStatus) {
	this.contractStatus = contractStatus;
    }

    public String getAgreementNumberMask() {
	return agreementNumberMask;
    }

    public void setAgreementNumberMask(String agreementNumberMask) {
	this.agreementNumberMask = agreementNumberMask;
    }

    public RequestCancelationReason getRequestCancelationReason() {
	return requestCancelationReason;
    }

    public Optional<RequestCancelationReason> optionalRequestCancelationReason() {
	return MyOptionals.of(getRequestCancelationReason());
    }

    public void setRequestCancelationReason(RequestCancelationReason requestCancelationReason) {
	this.requestCancelationReason = requestCancelationReason;
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

    public String getPaymentCardBank() {
	return paymentCardBank;
    }

    public Optional<String> optPaymentCardBank() {
	return MyOptionals.of(paymentCardBank);
    }

    public void setPaymentCardBank(String paymentCardBank) {
	this.paymentCardBank = paymentCardBank;
    }

    public String getPaymentCard() {
	return paymentCard;
    }

    public Optional<String> optPaymentCard() {
	return MyOptionals.of(paymentCard);
    }

    public void setPaymentCard(String paymentCard) {
	this.paymentCard = paymentCard;
    }

}
