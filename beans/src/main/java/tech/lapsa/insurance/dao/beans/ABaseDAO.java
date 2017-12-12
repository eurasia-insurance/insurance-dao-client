package tech.lapsa.insurance.dao.beans;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lapsa.insurance.jpa.InsuranceConstants;

import tech.lapsa.patterns.dao.beans.AGeneralDAO;

public abstract class ABaseDAO<T extends Serializable, I extends Serializable> extends AGeneralDAO<T, I> {

    protected ABaseDAO(Class<T> entityClazz) {
	super(entityClazz);
    }

    @PersistenceContext(unitName = InsuranceConstants.PERSISTENCE_UNIT_NAME)
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }
}
