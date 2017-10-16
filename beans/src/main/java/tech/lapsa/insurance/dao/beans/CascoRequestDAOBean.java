package tech.lapsa.insurance.dao.beans;

import javax.ejb.Stateless;

import com.lapsa.insurance.domain.casco.CascoRequest;

import tech.lapsa.insurance.dao.CascoRequestDAO;

@Stateless
public class CascoRequestDAOBean extends AGeneralInsuranceRequestDAO<CascoRequest>
	implements CascoRequestDAO {

    public CascoRequestDAOBean() {
	super(CascoRequest.class);
    }
}
