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
import tech.lapsa.insurance.dao.PeristenceOperationFailed;
import tech.lapsa.insurance.dao.filter.RequestFilter;

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
	if (filter.getRequestType() != null)
	    whereOptions.add(cb.equal(root.get(InsuranceRequest_.type), filter.getRequestType()));

	// payment method
	if (filter.getPaymentMethod() != null)
	    whereOptions.add(cb.equal(root.get(InsuranceRequest_.payment).get(PaymentData_.method),
		    filter.getPaymentMethod()));

	// payment status
	if (filter.getPaymentStatus() != null)
	    whereOptions.add(cb.equal(root.get(InsuranceRequest_.payment).get(PaymentData_.status),
		    filter.getPaymentStatus()));

	// obtaining method
	if (filter.getObtainingMethod() != null)
	    whereOptions.add(cb.equal(root.get(InsuranceRequest_.obtaining).get(ObtainingData_.method),
		    filter.getObtainingMethod()));

	// obtaining status
	if (filter.getObtainingStatus() != null)
	    whereOptions.add(cb.equal(root.get(InsuranceRequest_.obtaining).get(ObtainingData_.status),
		    filter.getObtainingStatus()));

	// transaction status
	if (filter.getTransactionStatus() != null)
	    whereOptions
		    .add(cb.equal(root.get(InsuranceRequest_.transactionStatus),
			    filter.getTransactionStatus()));

	// agreement number mask
	{
	    Predicate total = Predictates.textMatches(cb, root.get(InsuranceRequest_.agreementNumber),
		    filter.getAgreementNumberMask());
	    if (total != null)
		whereOptions.add(total);
	}

	// transaction problem
	if (filter.getTransactionProblem() != null)
	    whereOptions
		    .add(cb.equal(root.get(InsuranceRequest_.transactionProblem),
			    filter.getTransactionProblem()));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findByPaymentExternalId(String externalId) throws PeristenceOperationFailed {
	// SELECT e
	// FROM InsuranceRequest e
	// WHERE e.payment.paymentReference = :paymentReference

	try {
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<T> cq = cb.createQuery(entityClass);
	    Root<T> root = cq.from(entityClass);
	    cq.select(root)
		    .where(cb.equal(root.get(InsuranceRequest_.payment)
			    .get(PaymentData_.externalId), externalId));

	    TypedQuery<T> q = em.createQuery(cq);
	    return resultListNoCached(q);
	} catch (Throwable e) {
	    throw new PeristenceOperationFailed(e);
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findOpenUnpaidByPaycardOnline() throws PeristenceOperationFailed {
	// SELECT e
	// FROM InsuranceRequest e
	// WHERE e.status = com.lapsa.insurance.crm.RequestStatus.OPEN
	// AND e.payment.method =
	// com.lapsa.insurance.elements.PaymentMethod.PAYCARD_ONLINE
	// AND e.payment.status = com.lapsa.insurance.crm.PaymentStatus.PENDING
	// AND e.payment.paymentReference IS NOT NULL

	try {
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
	} catch (Throwable e) {
	    throw new PeristenceOperationFailed(e);
	}
    }
}
