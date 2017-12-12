package test.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.lapsa.insurance.domain.policy.PolicyRequest;

import tech.lapsa.insurance.dao.PolicyRequestDAO.PolicyRequestDAOLocal;
import tech.lapsa.java.commons.logging.MyLogger;
import tech.lapsa.patterns.dao.NotFound;

public class PolicyRequstDAOTestCase extends ArquillianBaseTestCase {

    @Inject
    private PolicyRequestDAOLocal dao;

    private static MyLogger logger = MyLogger.getDefault();

    private PolicyRequest entity;

    @Before
    public void createEntity() {
	logger.INFO.log("createEntity(): Create entity");
	entity = dao.save(newPolicyRequest());
    }

    @Test
    public void testCreateAndSave() throws NotFound {
	PolicyRequest testFind = dao.getById(entity.getId());
	assertThat(testFind, not(nullValue()));
    }

    private static final String CHANGED_REQUESTER_NAME = "NEW REQUESTER";

    @Test
    public void testCreateAndSaveAndChangeAndUpdate() throws NotFound {
	logger.INFO.log("testCreateAndSaveAndChangeAndUpdate(): Find entity");
	PolicyRequest testFind = dao.getById(entity.getId());

	logger.INFO.log("testCreateAndSaveAndChangeAndUpdate(): Change entity");
	testFind.getRequester().setName(CHANGED_REQUESTER_NAME);
	assertThat(testFind.getRequester().getName(), equalTo(CHANGED_REQUESTER_NAME));

	logger.INFO.log("testCreateAndSaveAndChangeAndUpdate(): Persist changed entity");
	assertThat(testFind.getId(), equalTo(entity.getId()));
	assertThat(testFind.getRequester().getName(), equalTo(CHANGED_REQUESTER_NAME));
	testFind = dao.save(testFind);

	logger.INFO.log("testCreateAndSaveAndChangeAndUpdate(): Find another entity");
	PolicyRequest testFind2 = dao.getById(testFind.getId());
	assertThat(testFind2.getId(), equalTo(testFind.getId()));
	assertThat(testFind2.getRequester().getName(), equalTo(testFind.getRequester().getName()));
	assertThat(testFind2.getId(), equalTo(testFind.getId()));
    }

    @Test
    public void testCreateAndSaveAndChangeAndReset() throws NotFound {
	logger.INFO.log("testCreateAndSaveAndChangeAndReset(): Find entity");
	PolicyRequest testFind = dao.getById(entity.getId());

	logger.INFO.log("testCreateAndSaveAndChangeAndReset(): Change entity");
	testFind.getRequester().setName(CHANGED_REQUESTER_NAME);
	assertThat(testFind.getRequester().getName(), equalTo(CHANGED_REQUESTER_NAME));

	logger.INFO.log("testCreateAndSaveAndChangeAndReset(): Reset entity");
	testFind = dao.restore(testFind);
	assertThat(testFind.getRequester().getName(), equalTo(entity.getRequester().getName()));

	logger.INFO.log("testCreateAndSaveAndChangeAndReset(): Find another entity");
	PolicyRequest testFind2 = dao.getById(entity.getId());
	assertThat(testFind2.getRequester().getName(), equalTo(entity.getRequester().getName()));
	assertThat(testFind2.getId(), equalTo(entity.getId()));
    }

    // CREATORS

    private PolicyRequest newPolicyRequest() {
	return TestObjectsCreatorHelper.generatePolicyRequest();
    }
}
