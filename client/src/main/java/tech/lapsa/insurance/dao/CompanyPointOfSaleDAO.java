package tech.lapsa.insurance.dao;

import java.util.List;

import javax.ejb.Local;

import com.lapsa.insurance.domain.CompanyPointOfSale;

@Local
public interface CompanyPointOfSaleDAO extends GeneralDAO<CompanyPointOfSale, Integer> {

    List<CompanyPointOfSale> findAll();
}
