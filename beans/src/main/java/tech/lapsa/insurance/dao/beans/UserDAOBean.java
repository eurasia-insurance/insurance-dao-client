package tech.lapsa.insurance.dao.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.lapsa.insurance.domain.Request;
import com.lapsa.insurance.domain.Request_;
import com.lapsa.insurance.domain.crm.User;
import com.lapsa.insurance.domain.crm.UserLogin;
import com.lapsa.insurance.domain.crm.User_;

import tech.lapsa.insurance.dao.EntityNotFound;
import tech.lapsa.insurance.dao.PeristenceOperationFailed;
import tech.lapsa.insurance.dao.UserDAO;
import tech.lapsa.insurance.dao.UserLoginDAO;

@Stateless
public class UserDAOBean extends AGeneralDAO<User, Integer> implements UserDAO {
    public UserDAOBean() {
	super(User.class);
    }

    @Inject
    private UserLoginDAO userLoginDAO;

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User findByLogin(final String login) throws PeristenceOperationFailed, EntityNotFound {
	UserLogin userLogin = userLoginDAO.findByName(login);
	return userLogin.getUser();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<User> findAll() throws PeristenceOperationFailed {
	try {
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<User> cq = cb.createQuery(entityClass);
	    Root<User> root = cq.from(entityClass);
	    cq.select(root);
	    TypedQuery<User> q = em.createQuery(cq);
	    return resultListNoCached(q);
	} catch (Throwable e) {
	    throw new PeristenceOperationFailed(e);
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<User> findVisible() throws PeristenceOperationFailed {
	try {
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<User> cq = cb.createQuery(entityClass);
	    Root<User> root = cq.from(entityClass);
	    cq.select(root).where(cb.isFalse(root.get(User_.hidden)));
	    TypedQuery<User> q = em.createQuery(cq);
	    return resultListNoCached(q);
	} catch (Throwable e) {
	    throw new PeristenceOperationFailed(e);
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<User> findAllWhoCreatedRequest() throws PeristenceOperationFailed {
	try {
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<User> cq = cb.createQuery(entityClass);
	    Root<Request> root = cq.from(Request.class);
	    cq.select(root.get(Request_.createdBy))
		    .distinct(true);
	    TypedQuery<User> q = em.createQuery(cq);
	    return resultListNoCached(q);
	} catch (Throwable e) {
	    throw new PeristenceOperationFailed(e);
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<User> findAllWithNoGroup() throws PeristenceOperationFailed {
	try {
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<User> cq = cb.createQuery(entityClass);
	    Root<User> root = cq.from(entityClass);
	    cq.select(root).where(cb.isEmpty(root.get(User_.groups)));
	    TypedQuery<User> q = em.createQuery(cq);
	    return resultListNoCached(q);
	} catch (Throwable e) {
	    throw new PeristenceOperationFailed(e);
	}
    }

}
