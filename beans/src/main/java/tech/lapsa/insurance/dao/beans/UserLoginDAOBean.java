package tech.lapsa.insurance.dao.beans;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.lapsa.insurance.domain.crm.UserLogin;
import com.lapsa.insurance.domain.crm.UserLogin_;

import tech.lapsa.insurance.dao.EntityNotFound;
import tech.lapsa.insurance.dao.PeristenceOperationFailed;
import tech.lapsa.insurance.dao.UserLoginDAO;

@Stateless
public class UserLoginDAOBean extends AGeneralDAO<UserLogin, Integer> implements UserLoginDAO {
    public UserLoginDAOBean() {
	super(UserLogin.class);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public UserLogin findByName(final String login) throws PeristenceOperationFailed, EntityNotFound {
	try {
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<UserLogin> cq = cb.createQuery(entityClass);
	    Root<UserLogin> root = cq.from(entityClass);
	    cq.select(root)
		    .where(
			    cb.equal(root.get(UserLogin_.name), login));
	    TypedQuery<UserLogin> q = em.createQuery(cq);
	    return putNoCacheHints(q).getSingleResult();
	} catch (NoResultException e) {
	    throw new EntityNotFound(e);
	} catch (Throwable e) {
	    throw new PeristenceOperationFailed(e);
	}
    }
}
