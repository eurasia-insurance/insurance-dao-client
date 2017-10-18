package test.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lapsa.insurance.domain.policy.PolicyRequest;

import tech.lapsa.insurance.dao.NotFound;
import tech.lapsa.insurance.dao.PolicyRequestDAO;

public class PolicyRequstDAOTestCase extends ArquillianBaseTestCase {

    @Inject
    private PolicyRequestDAO dao;

    private static Logger logger;

    private PolicyRequest entity;

    @BeforeClass
    public static void init() {
	logger = Logger.getLogger(PolicyRequstDAOTestCase.class.getName());
    }

    @Before
    public void createEntity() {
	logger.info("createEntity(): Create entity");
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
	logger.info("testCreateAndSaveAndChangeAndUpdate(): Find entity");
	PolicyRequest testFind = dao.getById(entity.getId());

	logger.info("testCreateAndSaveAndChangeAndUpdate(): Change entity");
	testFind.getRequester().setName(CHANGED_REQUESTER_NAME);
	assertThat(testFind.getRequester().getName(), equalTo(CHANGED_REQUESTER_NAME));

	logger.info("testCreateAndSaveAndChangeAndUpdate(): Persist changed entity");
	assertThat(testFind.getId(), equalTo(entity.getId()));
	assertThat(testFind.getRequester().getName(), equalTo(CHANGED_REQUESTER_NAME));
	testFind = dao.save(testFind);

	logger.info("testCreateAndSaveAndChangeAndUpdate(): Find another entity");
	PolicyRequest testFind2 = dao.getById(testFind.getId());
	assertThat(testFind2.getId(), equalTo(testFind.getId()));
	assertThat(testFind2.getRequester().getName(), equalTo(testFind.getRequester().getName()));
	assertThat(testFind2.getId(), equalTo(testFind.getId()));
    }

    @Test
    public void testCreateAndSaveAndChangeAndReset() throws NotFound {
	logger.info("testCreateAndSaveAndChangeAndReset(): Find entity");
	PolicyRequest testFind = dao.getById(entity.getId());

	logger.info("testCreateAndSaveAndChangeAndReset(): Change entity");
	testFind.getRequester().setName(CHANGED_REQUESTER_NAME);
	assertThat(testFind.getRequester().getName(), equalTo(CHANGED_REQUESTER_NAME));

	logger.info("testCreateAndSaveAndChangeAndReset(): Reset entity");
	testFind = dao.restore(testFind);
	assertThat(testFind.getRequester().getName(), equalTo(entity.getRequester().getName()));

	logger.info("testCreateAndSaveAndChangeAndReset(): Find another entity");
	PolicyRequest testFind2 = dao.getById(entity.getId());
	assertThat(testFind2.getRequester().getName(), equalTo(entity.getRequester().getName()));
	assertThat(testFind2.getId(), equalTo(entity.getId()));
    }

    // CREATORS

    private PolicyRequest newPolicyRequest() {
	return TestObjectsCreatorHelper.generatePolicyRequest();
    }
}
