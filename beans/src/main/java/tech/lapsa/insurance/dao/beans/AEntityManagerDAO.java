package tech.lapsa.insurance.dao.beans;

import static com.lapsa.insurance.jpaUnit.InsuranceConstants.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tech.lapsa.patterns.dao.beans.AGeneralDAO;

public abstract class AEntityManagerDAO<T, I> extends AGeneralDAO<T, I> {

    protected AEntityManagerDAO(Class<T> entityClazz) {
	super(entityClazz);
    }

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }
}
