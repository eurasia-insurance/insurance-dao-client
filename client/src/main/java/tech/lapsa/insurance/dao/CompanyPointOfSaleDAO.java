package tech.lapsa.insurance.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.domain.CompanyPointOfSale;

import tech.lapsa.patterns.dao.GeneralDAO;

public interface CompanyPointOfSaleDAO extends GeneralDAO<CompanyPointOfSale, Integer>, EJBConstants {

    public static final String BEAN_NAME = "CompanyPointOfSaleDAOBean";
    
    @Local
    public interface CompanyPointOfSaleDAOLocal extends CompanyPointOfSaleDAO {
    }

    @Remote
    public interface CompanyPointOfSaleDAORemote extends CompanyPointOfSaleDAO {
    }

    List<CompanyPointOfSale> findAll();
}
