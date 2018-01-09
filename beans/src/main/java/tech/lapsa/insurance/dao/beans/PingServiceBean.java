package tech.lapsa.insurance.dao.beans;

import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lapsa.insurance.jpa.InsuranceConstants;
import com.lapsa.insurance.jpa.InsuranceVersion;

import tech.lapsa.insurance.dao.PingService;
import tech.lapsa.insurance.dao.PingService.PingServiceLocal;
import tech.lapsa.insurance.dao.PingService.PingServiceRemote;
import tech.lapsa.java.commons.exceptions.IllegalState;
import tech.lapsa.java.commons.function.MyExceptions;
import tech.lapsa.java.commons.function.MyMaps;

@Stateless(name = PingService.BEAN_NAME)
public class PingServiceBean implements PingServiceLocal, PingServiceRemote {

    @PersistenceContext(unitName = InsuranceConstants.PERSISTENCE_UNIT_NAME)
    protected EntityManager em;

    private static final String HINT_JAVAX_PERSISTENCE_CACHE_STORE_MODE = "javax.persistence.cache.storeMode";
    private static final String HINT_JAVAX_PERSISTENCE_CACHE_RETREIVE_MODE = "javax.persistence.cache.retreiveMode";

    private static final Map<String, Object> NO_CACHE_PROPS = MyMaps.of( //
	    HINT_JAVAX_PERSISTENCE_CACHE_RETREIVE_MODE, CacheRetrieveMode.BYPASS, //
	    HINT_JAVAX_PERSISTENCE_CACHE_STORE_MODE, CacheStoreMode.REFRESH);

    @Override
    public void ping() throws IllegalState {
	try {
	    em.find(InsuranceVersion.class, 1, NO_CACHE_PROPS);
	} catch (Exception e) {
	    throw MyExceptions.illegalStateFormat(e.getMessage());
	}
    }

}
