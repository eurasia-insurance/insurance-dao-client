package tech.lapsa.insurance.dao.beans;

import static com.lapsa.insurance.jpaUnit.InsuranceConstants.*;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tech.lapsa.patterns.dao.beans.AGeneralDAO;

public abstract class ABaseDAO<T extends Serializable, I extends Serializable> extends AGeneralDAO<T, I> {

    protected ABaseDAO(Class<T> entityClazz) {
	super(entityClazz);
    }

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }
}
