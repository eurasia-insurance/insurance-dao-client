package tech.lapsa.insurance.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import com.lapsa.insurance.domain.crm.User;
import com.lapsa.insurance.elements.InsuranceRequestStatus;
import com.lapsa.insurance.elements.InsuranceRequestType;
import com.lapsa.insurance.elements.PaymentStatus;
import com.lapsa.insurance.elements.ProgressStatus;
import com.lapsa.insurance.elements.InsuranceRequestCancellationReason;

import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

public class RequestFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    public RequestFilter() {
    }

    public ZoneId getZoneId() {
	return ZoneId.systemDefault();
    }

    // id

    private Integer id;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    // requesterNameMask

    private String requesterNameMask;

    public String getRequesterNameMask() {
	return requesterNameMask;
    }

    public void setRequesterNameMask(String requesterNameMask) {
	this.requesterNameMask = requesterNameMask;
    }

    // showMode

    public enum ShowMode {
	INBOX, ARCHIVED, ALL;
    }

    private ShowMode showMode = ShowMode.INBOX;

    public ShowMode getShowMode() {
	return showMode;
    }

    public void setShowMode(ShowMode showMode) {
	this.showMode = MyObjects.requireNonNull(showMode, "showMode");
    }

    public boolean isShowInbox() {
	return ShowMode.INBOX.equals(showMode);
    }

    public boolean isShowArchived() {
	return ShowMode.ARCHIVED.equals(showMode);
    }

    public boolean isShowAll() {
	return ShowMode.ALL.equals(showMode);
    }

    public void letShowInbox() {
	this.showMode = ShowMode.INBOX;
    }

    public void letShowArchived() {
	this.showMode = ShowMode.ARCHIVED;
    }

    public void letShowAll() {
	this.showMode = ShowMode.ALL;
    }

    // progress status

    private ProgressStatus progressStatus;

    public ProgressStatus getProgressStatus() {
	return progressStatus;
    }

    public void setProgressStatus(ProgressStatus progressStatus) {
	this.progressStatus = progressStatus;
    }

    // createdAfter

    private LocalDateTime createdAfter;

    public LocalDateTime getCreatedAfter() {
	return createdAfter;
    }

    public void setCreatedAfter(LocalDateTime createdAfter) {
	this.createdAfter = createdAfter;
    }

    // createdBefore

    private LocalDateTime createdBefore;

    public LocalDateTime getCreatedBefore() {
	return createdBefore;
    }

    public void setCreatedBefore(LocalDateTime createdBefore) {
	this.createdBefore = createdBefore;
    }

    // completedAfter

    private LocalDateTime completedAfter;

    public LocalDateTime getCompletedAfter() {
	return completedAfter;
    }

    public void setCompletedAfter(LocalDateTime completedAfter) {
	this.completedAfter = completedAfter;
    }

    // completedBefore

    private LocalDateTime completedBefore;

    public LocalDateTime getCompletedBefore() {
	return completedBefore;
    }

    public void setCompletedBefore(LocalDateTime completedBefore) {
	this.completedBefore = completedBefore;
    }

    // pickedBy

    private User pickedBy;

    public User getPickedBy() {
	return pickedBy;
    }

    public void setPickedBy(User pickedBy) {
	this.pickedBy = pickedBy;
    }

    // completedBy

    private User completedBy;

    public User getCompletedBy() {
	return completedBy;
    }

    public void setCompletedBy(User completedBy) {
	this.completedBy = completedBy;
    }

    // insuranceRequestType

    private InsuranceRequestType insuranceRequestType;

    public InsuranceRequestType getRequestType() {
	return insuranceRequestType;
    }

    public Optional<InsuranceRequestType> optionalRequestType() {
	return MyOptionals.of(insuranceRequestType);
    }

    public void setRequestType(InsuranceRequestType insuranceRequestType) {
	this.insuranceRequestType = insuranceRequestType;
    }

    // paymentStatus

    private PaymentStatus paymentStatus;

    public PaymentStatus getPaymentStatus() {
	return paymentStatus;
    }

    public Optional<PaymentStatus> optionalPaymentStatus() {
	return MyOptionals.of(paymentStatus);
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
	this.paymentStatus = paymentStatus;
    }

    // invoiceNumber

    private String invoiceNumber;

    public String getInvoiceNumber() {
	return invoiceNumber;
    }

    public Optional<String> optionalInvoiceNumber() {
	return MyOptionals.of(invoiceNumber);
    }

    public void setInvoiceNumber(String invoiceNumber) {
	this.invoiceNumber = invoiceNumber;
    }

    // paymentReference

    private String paymentReference;

    public String getPaymentReference() {
	return paymentReference;
    }

    public Optional<String> optPaymentReference() {
	return MyOptionals.of(paymentReference);
    }

    public void setPaymentReference(String paymentReference) {
	this.paymentReference = paymentReference;
    }

    // insuranceRequestStatus

    private InsuranceRequestStatus insuranceRequestStatus;

    public InsuranceRequestStatus getInsuranceRequestStatus() {
	return insuranceRequestStatus;
    }

    public Optional<InsuranceRequestStatus> optionalInsuranceRequestStatus() {
	return MyOptionals.of(insuranceRequestStatus);
    }

    public void setInsuranceRequestStatus(InsuranceRequestStatus insuranceRequestStatus) {
	this.insuranceRequestStatus = insuranceRequestStatus;
    }

    // agreementNumberMask

    private String agreementNumberMask;

    public String getAgreementNumberMask() {
	return agreementNumberMask;
    }

    public void setAgreementNumberMask(String agreementNumberMask) {
	this.agreementNumberMask = agreementNumberMask;
    }

    // insuranceRequestCancellationReason

    private InsuranceRequestCancellationReason insuranceRequestCancellationReason;

    public InsuranceRequestCancellationReason getInsuranceRequestCancellationReason() {
	return insuranceRequestCancellationReason;
    }

    public Optional<InsuranceRequestCancellationReason> optionalInsuranceRequestCancellationReason() {
	return MyOptionals.of(insuranceRequestCancellationReason);
    }

    public void setInsuranceRequestCancellationReason(InsuranceRequestCancellationReason insuranceRequestCancellationReason) {
	this.insuranceRequestCancellationReason = insuranceRequestCancellationReason;
    }

    // createdBy

    private User createdBy;

    public User getCreatedBy() {
	return createdBy;
    }

    public void setCreatedBy(User createdBy) {
	this.createdBy = createdBy;
    }

    // requesterTaxpayerNumber

    private TaxpayerNumber requesterTaxpayerNumber;

    public TaxpayerNumber getRequesterTaxpayerNumber() {
	return requesterTaxpayerNumber;
    }

    public Optional<TaxpayerNumber> optionalRequesterTaxpayerNumber() {
	return MyOptionals.of(getRequesterTaxpayerNumber());
    }

    public void setRequesterTaxpayerNumber(TaxpayerNumber requesterTaxpayerNumber) {
	this.requesterTaxpayerNumber = requesterTaxpayerNumber;
    }

    // paymentMethodNameMask

    private String paymentMethodNameMask;

    public String getPaymentMethodNameMask() {
	return paymentMethodNameMask;
    }

    public void setPaymentMethodNameMask(String paymentMethodNameMask) {
	this.paymentMethodNameMask = paymentMethodNameMask;
    }

    // paymentCardBank

    private String paymentCardBank;

    public String getPaymentCardBank() {
	return paymentCardBank;
    }

    public Optional<String> optPaymentCardBank() {
	return MyOptionals.of(paymentCardBank);
    }

    public void setPaymentCardBank(String paymentCardBank) {
	this.paymentCardBank = paymentCardBank;
    }

    // paymentCard

    private String paymentCard;

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
