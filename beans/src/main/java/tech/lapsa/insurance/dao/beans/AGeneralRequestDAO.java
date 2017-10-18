package tech.lapsa.insurance.dao.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.lapsa.insurance.domain.Request;
import com.lapsa.insurance.domain.Request_;
import com.lapsa.insurance.domain.RequesterData_;
import com.lapsa.insurance.domain.crm.User;
import com.lapsa.insurance.elements.RequestStatus;

import tech.lapsa.insurance.dao.GeneralRequestDAO;
import tech.lapsa.insurance.dao.filter.RequestFilter;
import tech.lapsa.patterns.dao.beans.Predictates;

public abstract class AGeneralRequestDAO<T extends Request>
	extends AEntityManagerDAO<T, Integer>
	implements GeneralRequestDAO<T> {

    public AGeneralRequestDAO(Class<T> entityClass) {
	super(entityClass);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findByStatus(final RequestStatus status) {
	// SELECT e
	// FROM InsuranceRequest e
	// WHERE e.status = :status

	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<T> cq = cb.createQuery(entityClass);
	Root<T> root = cq.from(entityClass);
	cq.select(root)
		.where(
			cb.equal(root.get(Request_.status), status));

	TypedQuery<T> q = em.createQuery(cq);
	return resultListNoCached(q);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findByFilter(RequestFilter filter) {
	return findByFilter(filter, true);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findByFilter(RequestFilter filter, boolean showNoCreators, User... onlyCreators) {
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<T> cq = cb.createQuery(entityClass);
	Root<T> root = cq.from(entityClass);

	List<Predicate> whereOptions = new ArrayList<>();

	prepareRequestFilterPredictates(filter, cb, root, whereOptions);

	if (onlyCreators != null && onlyCreators.length > 0) {
	    List<Predicate> creatorsRestriction = new ArrayList<>();
	    if (showNoCreators)
		creatorsRestriction.add(cb.isNull(root.get(Request_.createdBy)));
	    for (User u : onlyCreators)
		creatorsRestriction.add(cb.equal(root.get(Request_.createdBy), u));
	    whereOptions.add(cb.or(creatorsRestriction.toArray(new Predicate[0])));
	}

	cq.select(root).where(cb.and(whereOptions.toArray(new Predicate[0])));

	TypedQuery<T> q = em.createQuery(cq);
	return resultListNoCached(q);
    }

    protected void prepareRequestFilterPredictates(RequestFilter filter, CriteriaBuilder cb, Root<T> root,
	    List<Predicate> whereOptions) {
	// request id
	if (filter.getId() != null && filter.getId() > 0) {
	    whereOptions.add(
		    cb.equal(root.get(Request_.id), filter.getId()));
	}

	// requester name mask
	Predictates.textMatches(cb, root.get(Request_.requester).get(RequesterData_.name),
		filter.getRequesterNameMask()) //
		.ifPresent(whereOptions::add);

	// requester ID number mask
	Predictates.textMatches(cb, root.get(Request_.requester).get(RequesterData_.idNumber),
		filter.getRequesterIdNumberMask()) //
		.ifPresent(whereOptions::add);

	// request status
	if (filter.getRequestStatus() != null)
	    whereOptions.add(cb.equal(root.get(Request_.status), filter.getRequestStatus()));

	// request source
	filter.optionalRequestSource() //
		.map(x -> cb.equal(root.get(Request_.source), x)) //
		.ifPresent(whereOptions::add);

	// progress status
	if (filter.getProgressStatus() != null)
	    whereOptions.add(cb.equal(root.get(Request_.progressStatus), filter.getProgressStatus()));

	if (filter.getZoneId() != null) {

	    // created after
	    if (filter.getCreatedAfter() != null)
		whereOptions
			.add(cb.greaterThanOrEqualTo(root.get(Request_.created),
				filter.getCreatedAfter().atZone(filter.getZoneId()).toInstant()));

	    // created before
	    if (filter.getCreatedBefore() != null)
		whereOptions.add(cb.lessThanOrEqualTo(root.get(Request_.created),
			filter.getCreatedBefore().atZone(filter.getZoneId()).toInstant()));

	    // completed after
	    if (filter.getCompletedAfter() != null)
		whereOptions.add(
			cb.greaterThanOrEqualTo(root.get(Request_.completed),
				filter.getCompletedAfter().atZone(filter.getZoneId()).toInstant()));

	    // completed before
	    if (filter.getCompletedBefore() != null)
		whereOptions
			.add(cb.lessThanOrEqualTo(root.get(Request_.completed),
				filter.getCompletedBefore().atZone(filter.getZoneId()).toInstant()));

	}

	// created by
	if (filter.getCreatedBy() != null)
	    whereOptions
		    .add(cb.equal(root.get(Request_.createdBy), filter.getCreatedBy()));

	// accepted by
	if (filter.getAcceptedBy() != null)
	    whereOptions
		    .add(cb.equal(root.get(Request_.acceptedBy), filter.getAcceptedBy()));

	// completed by
	if (filter.getCompletedBy() != null)
	    whereOptions
		    .add(cb.equal(root.get(Request_.completedBy), filter.getCompletedBy()));

	// closed by
	if (filter.getClosedBy() != null)
	    whereOptions
		    .add(cb.equal(root.get(Request_.closedBy), filter.getClosedBy()));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findAllOpen() {
	return findByStatus(RequestStatus.OPEN);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findAll() {
	// SELECT e
	// FROM InsuranceRequest e

	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<T> cq = cb.createQuery(entityClass);
	Root<T> root = cq.from(entityClass);
	cq.select(root);

	TypedQuery<T> q = em.createQuery(cq);
	return resultListNoCached(q);
    }

}
