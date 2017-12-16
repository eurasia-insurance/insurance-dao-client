package tech.lapsa.insurance.dao.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.lapsa.insurance.domain.BaseEntity_;
import com.lapsa.insurance.domain.Request;
import com.lapsa.insurance.domain.Request_;
import com.lapsa.insurance.domain.RequesterData_;
import com.lapsa.insurance.domain.crm.User;
import com.lapsa.insurance.elements.RequestStatus;

import tech.lapsa.insurance.dao.GeneralRequestDAO.GeneralRequestDAOLocal;
import tech.lapsa.insurance.dao.GeneralRequestDAO.GeneralRequestDAORemote;
import tech.lapsa.insurance.dao.RequestFilter;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyExceptions;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.patterns.dao.beans.Predictates;

public abstract class AGeneralRequestDAO<T extends Request>
	extends ABaseDAO<T, Integer>
	implements GeneralRequestDAOLocal<T>, GeneralRequestDAORemote<T> {

    protected static final int MAX_LIMIT = 100;

    public AGeneralRequestDAO(final Class<T> entityClass) {
	super(entityClass);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findByStatus(final RequestStatus status) throws IllegalArgument {
	return _requireUnlimitedQuery(_queryFindByStatus(status)).getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findByStatus(int from, int limit, final RequestStatus status) throws IllegalArgument {
	return _requireLimitedQuery(from, limit, _queryFindByStatus(status)).getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findByFilter(final RequestFilter filter) throws IllegalArgument {
	return _requireUnlimitedQuery(_queryFindByFilter(filter)).getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findByFilter(int from, int limit, final RequestFilter filter) throws IllegalArgument {
	return _requireLimitedQuery(from, limit, _queryFindByFilter(filter)).getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findByFilter(final RequestFilter filter, final boolean showNoCreators,
	    final User... onlyCreators)
	    throws IllegalArgument {
	return _requireUnlimitedQuery(_queryFindByFilter(filter, showNoCreators, onlyCreators)).getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findByFilter(int from, int limit, final RequestFilter filter, final boolean showNoCreators,
	    final User... onlyCreators)
	    throws IllegalArgument {
	return _requireLimitedQuery(from, limit, _queryFindByFilter(filter, showNoCreators, onlyCreators))
		.getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findAllOpen() {
	return _requireUnlimitedQuery(_queryFindAllOpen()).getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findAllOpen(int from, int limit) throws IllegalArgument {
	return _requireLimitedQuery(from, limit, _queryFindAllOpen()).getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findAll() {
	return _requireUnlimitedQuery(_queryFindAll()).getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findAll(int from, int limit) throws IllegalArgument {
	return _requireLimitedQuery(from, limit, _queryFindAll()).getResultList();
    }

    // preparer

    protected void prepareRequestFilterPredictates(final RequestFilter filter, final CriteriaBuilder cb,
	    final Root<T> root,
	    final List<Predicate> whereOptions) {
	// request id
	if (filter.getId() != null && filter.getId() > 0)
	    whereOptions.add(
		    cb.equal(root.get(BaseEntity_.id), filter.getId()));

	// requester name mask
	Predictates.textMatches(cb, root.get(Request_.requester).get(RequesterData_.name),
		filter.getRequesterNameMask()) //
		.ifPresent(whereOptions::add);

	// requester taxpayer number
	filter.optionalRequesterTaxpayerNumber() //
		.map(x -> cb.equal(root.get(Request_.requester).get(RequesterData_.idNumber), x)) //
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

    // queries

    protected TypedQuery<T> _queryFindAllOpen() {
	try {
	    return _queryFindByStatus(RequestStatus.OPEN);
	} catch (IllegalArgument e) {
	    // it should not happens
	    throw new EJBException(e.getMessage());
	}
    }

    protected TypedQuery<T> _queryFindAll() {
	final CriteriaBuilder cb = em.getCriteriaBuilder();
	final CriteriaQuery<T> cq = cb.createQuery(entityClass);
	final Root<T> root = cq.from(entityClass);
	cq.select(root);
	final TypedQuery<T> q = em.createQuery(cq);
	return q;
    }

    protected TypedQuery<T> _queryFindByStatus(final RequestStatus status) throws IllegalArgument {
	MyObjects.requireNonNull(IllegalArgument::new, status, "status");
	final CriteriaBuilder cb = em.getCriteriaBuilder();
	final CriteriaQuery<T> cq = cb.createQuery(entityClass);
	final Root<T> root = cq.from(entityClass);
	cq.select(root)
		.where(
			cb.equal(root.get(Request_.status), status));

	final TypedQuery<T> q = em.createQuery(cq).setMaxResults(MAX_LIMIT);
	return q;
    }

    private TypedQuery<T> _queryFindByFilter(RequestFilter filter) throws IllegalArgument {
	return _queryFindByFilter(filter, true);
    }

    protected TypedQuery<T> _queryFindByFilter(final RequestFilter filter, final boolean showNoCreators,
	    final User... onlyCreators) throws IllegalArgument {
	MyObjects.requireNonNull(IllegalArgument::new, filter, "filter");

	final CriteriaBuilder cb = em.getCriteriaBuilder();
	final CriteriaQuery<T> cq = cb.createQuery(entityClass);
	final Root<T> root = cq.from(entityClass);

	final List<Predicate> whereOptions = new ArrayList<>();

	prepareRequestFilterPredictates(filter, cb, root, whereOptions);

	if (onlyCreators != null && onlyCreators.length > 0) {
	    final List<Predicate> creatorsRestriction = new ArrayList<>();
	    if (showNoCreators)
		creatorsRestriction.add(cb.isNull(root.get(Request_.createdBy)));
	    for (final User u : onlyCreators)
		creatorsRestriction.add(cb.equal(root.get(Request_.createdBy), u));
	    whereOptions.add(cb.or(creatorsRestriction.toArray(new Predicate[0])));
	}

	cq.select(root).where(cb.and(whereOptions.toArray(new Predicate[0])));

	final TypedQuery<T> q = em.createQuery(cq);
	return q;
    }

    // limits

    protected TypedQuery<T> _requireUnlimitedQuery(final TypedQuery<T> query) {
	return query.setFirstResult(0).setMaxResults(Integer.MAX_VALUE);
    }

    protected TypedQuery<T> _requireLimitedQuery(final int from, final int limit, final TypedQuery<T> query)
	    throws IllegalArgument {
	if (from < 0)
	    throw MyExceptions.format(IllegalArgument::new,
		    "Invalid from value %1$s. It should be greater than zero", from);

	if (limit <= 0 || limit > MAX_LIMIT)
	    throw MyExceptions.format(IllegalArgument::new,
		    "Invalid limit value %1$s. It should be greater than zero and not larget than maximum %2$s", limit,
		    MAX_LIMIT);

	return query.setFirstResult(from).setMaxResults(limit);
    }

}
