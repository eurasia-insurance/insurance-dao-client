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

import tech.lapsa.insurance.dao.GeneralInsuranceRequestDAO;
import tech.lapsa.insurance.dao.filter.RequestFilter;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyStrings;
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

	// payment status
	filter.optionalPaymentStatus() //
		.map(x -> cb.equal(root.get(InsuranceRequest_.payment).get(PaymentData_.status), x)) //
		.ifPresent(whereOptions::add);

	// payment external Id
	filter.optionalPaymentInvoiceNumber() //
		.map(x -> cb.equal(root.get(InsuranceRequest_.payment).get(PaymentData_.invoiceNumber), x)) //
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
    public List<T> findByPaymentInvoiceNumber(final String invoiceNumber) throws IllegalArgument {
	MyStrings.requireNonEmpty(IllegalArgument::new, invoiceNumber, "invoiceNumber");
	// SELECT e
	// FROM InsuranceRequest e
	// WHERE e.payment.paymentReference = :paymentReference

	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<T> cq = cb.createQuery(entityClass);
	Root<T> root = cq.from(entityClass);
	cq.select(root)
		.where(cb.equal(root.get(InsuranceRequest_.payment)
			.get(PaymentData_.invoiceNumber), invoiceNumber));

	TypedQuery<T> q = em.createQuery(cq);
	return q.getResultList();
    }
}
