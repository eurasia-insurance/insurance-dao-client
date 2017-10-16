package util;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.lapsa.insurance.domain.CompanyPointOfSale;
import com.lapsa.international.localization.LocalizationLanguage;

public class Localization {
    public static void main(String[] args) {
	Properties prop = new Properties();
	prop.setProperty("javax.persistence.jdbc.driver", "org.mariadb.jdbc.Driver");
	prop.setProperty("javax.persistence.jdbc.url", "jdbc:mysql://test-ias01.theeurasia.local:3306/eurasia36_dev");
	prop.setProperty("javax.persistence.jdbc.user", "eurasia36_dev");
	prop.setProperty("javax.persistence.jdbc.password", "HWwUCkt8pk50jjR7");
//	prop.setProperty("javax.persistence.schema-generation.database.action", "create");

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("insurancePU", prop);
	EntityManager em = emf.createEntityManager();

	List<CompanyPointOfSale> poses = em
		.createQuery("SELECT e FROM CompanyPointOfSale e", CompanyPointOfSale.class)
		.getResultList();
	em.getTransaction().begin();
	for (CompanyPointOfSale e : poses) {
	    for (LocalizationLanguage ll : LocalizationLanguage.values()) {
		e.getNameLocalization().put(ll, e.getName());
		e.getAddress().getStreetLocalization().put(ll, e.getAddress().getStreet());
		em.merge(e);
		em.flush();
	    }
	}
	em.getTransaction().commit();
    }
}
