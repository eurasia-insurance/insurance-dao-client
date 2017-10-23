package tech.lapsa.insurance.dao.beans;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.lapsa.insurance.domain.InsuranceRequest;
import com.lapsa.insurance.domain.InsuranceRequest_;
import com.lapsa.insurance.domain.ObtainingData_;
import com.lapsa.insurance.domain.PaymentData_;
import com.lapsa.insurance.domain.Request_;
import com.lapsa.insurance.elements.PaymentMethod;
import com.lapsa.insurance.elements.PaymentStatus;
import com.lapsa.insurance.elements.RequestStatus;

import tech.lapsa.insurance.dao.GeneralInsuranceRequestDAO;
import tech.lapsa.insurance.dao.filter.RequestFilter;
import tech.lapsa.patterns.dao.beans.Predictates;

public abstract class AGeneralInsuranceRequestDAO<T extends InsuranceRequest>
	extends AGeneralRequestDAO<T>
	implements GeneralInsuranceRequestDAO<T> {

    public AGeneralInsuranceRequestDAO(Class<T> entityClass) {
	super(entityClass);
    }

    @Override
    protected void prepareRequestFilterPredictates(RequestFilter filter, CriteriaBuilder cb, Root<T> root,
	    List<Predicate> whereOptions) {
	super.prepareRequestFilterPredictates(filter, cb, root, whereOptions);

	// request type
	filter.optionalRequestType() //
		.map(x -> cb.equal(root.get(InsuranceRequest_.type), x)) //
		.ifPresent(whereOptions::add);

	// payment method
	filter.optionalPaymentMethod() //
		.map(x -> cb.equal(root.get(InsuranceRequest_.payment).get(PaymentData_.method), x)) //
		.ifPresent(whereOptions::add);

	// payment status
	filter.optionalPaymentStatus() //
		.map(x -> cb.equal(root.get(InsuranceRequest_.payment).get(PaymentData_.status), x)) //
		.ifPresent(whereOptions::add);

	// payment external Id
	filter.optionalPaymentExternalId() //
		.map(x -> cb.equal(root.get(InsuranceRequest_.payment).get(PaymentData_.externalId), x)) //
		.ifPresent(whereOptions::add);

	// obtaining method
	filter.optionalObtainingMethod() //
		.map(x -> cb.equal(root.get(InsuranceRequest_.obtaining).get(ObtainingData_.method), x)) //
		.ifPresent(whereOptions::add);

	// obtaining status
	filter.optionalObtainingStatus() //
		.map(x -> cb.equal(root.get(InsuranceRequest_.obtaining).get(ObtainingData_.status), x)) //
		.ifPresent(whereOptions::add);

	// transaction status
	filter.optionalTransactionStatus() //
		.map(x -> cb.equal(root.get(InsuranceRequest_.transactionStatus), x)) //
		.ifPresent(whereOptions::add);

	// agreement number mask
	Predictates.textMatches(cb, root.get(InsuranceRequest_.agreementNumber),
		filter.getAgreementNumberMask()) //
		.ifPresent(whereOptions::add);

	// transaction problem
	filter.optionalTransactionProblem() //
		.map(x -> cb.equal(root.get(InsuranceRequest_.transactionProblem), x)) //
		.ifPresent(whereOptions::add);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findByPaymentExternalId(String externalId) {
	// SELECT e
	// FROM InsuranceRequest e
	// WHERE e.payment.paymentReference = :paymentReference

	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<T> cq = cb.createQuery(entityClass);
	Root<T> root = cq.from(entityClass);
	cq.select(root)
		.where(cb.equal(root.get(InsuranceRequest_.payment)
			.get(PaymentData_.externalId), externalId));

	TypedQuery<T> q = em.createQuery(cq);
	return resultListNoCached(q);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findOpenUnpaidByPaycardOnline() {
	// SELECT e
	// FROM InsuranceRequest e
	// WHERE e.status = com.lapsa.insurance.crm.RequestStatus.OPEN
	// AND e.payment.method =
	// com.lapsa.insurance.elements.PaymentMethod.PAYCARD_ONLINE
	// AND e.payment.status = com.lapsa.insurance.crm.PaymentStatus.PENDING
	// AND e.payment.paymentReference IS NOT NULL

	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<T> cq = cb.createQuery(entityClass);
	Root<T> root = cq.from(entityClass);
	cq.select(root)
		.where(
			cb.and(
				cb.equal(root.get(Request_.status), RequestStatus.OPEN),
				cb.equal(root.get(InsuranceRequest_.payment).get(PaymentData_.method),
					PaymentMethod.PAYCARD_ONLINE),
				cb.equal(root.get(InsuranceRequest_.payment).get(PaymentData_.status),
					PaymentStatus.PENDING),
				cb.isNotNull(
					root.get(InsuranceRequest_.payment).get(PaymentData_.externalId))));

	TypedQuery<T> q = em.createQuery(cq);
	return resultListNoCached(q);
    }
}
