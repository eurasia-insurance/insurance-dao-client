package tech.lapsa.insurance.dao.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.lapsa.insurance.domain.CompanyPointOfSale;

import tech.lapsa.insurance.dao.CompanyPointOfSaleDAO;

@Stateless
public class CompanyPointOfSaleDAOBean extends AGeneralDAO<CompanyPointOfSale, Integer>
	implements CompanyPointOfSaleDAO {

    public CompanyPointOfSaleDAOBean() {
	super(CompanyPointOfSale.class);
    }

    @Override
    public List<CompanyPointOfSale> findAll() {
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<CompanyPointOfSale> cq = cb.createQuery(CompanyPointOfSale.class);
	Root<CompanyPointOfSale> root = cq.from(CompanyPointOfSale.class);
	cq.select(root);
	TypedQuery<CompanyPointOfSale> q = em.createQuery(cq);
	return resultListNoCached(q);
    }

}
