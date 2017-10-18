package test.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.lapsa.insurance.domain.policy.PolicyRequest;
import com.lapsa.insurance.elements.PaymentMethod;
import com.lapsa.insurance.elements.PaymentStatus;

import tech.lapsa.insurance.dao.PolicyRequestDAO;
import tech.lapsa.patterns.dao.NotFound;

public class PolicyRequestDAO_findOpenUnpaidByPaycardOnline extends ArquillianBaseTestCase {

    @Inject
    private PolicyRequestDAO dao;

    @Test
    public void generalTest() throws NotFound {
	PolicyRequest o1 = TestObjectsCreatorHelper.generatePolicyRequest();
	o1.getPayment().setMethod(PaymentMethod.PAYCARD_ONLINE);
	o1.getPayment().setExternalId("111");
	o1.getPayment().setStatus(PaymentStatus.PENDING);
	o1 = dao.save(o1);

	PolicyRequest o2 = TestObjectsCreatorHelper.generatePolicyRequest();
	o2.getPayment().setMethod(PaymentMethod.PAYCASH);
	o2.getPayment().setExternalId(null);
	o2.getPayment().setStatus(PaymentStatus.PENDING);
	o2 = dao.save(o2);

	PolicyRequest o3 = TestObjectsCreatorHelper.generatePolicyRequest();
	o3.getPayment().setMethod(PaymentMethod.PAYCARD_ONLINE);
	o3.getPayment().setExternalId("222");
	o3.getPayment().setStatus(PaymentStatus.DONE);
	o3 = dao.save(o3);

	List<PolicyRequest> testFind = dao.findOpenUnpaidByPaycardOnline();
	assertThat(testFind, allOf(not(emptyCollectionOf(PolicyRequest.class)), hasSize(1)));
	PolicyRequest testO = testFind.get(0);
	assertThat(testO.getId(), is(o1.getId()));
    }
}
