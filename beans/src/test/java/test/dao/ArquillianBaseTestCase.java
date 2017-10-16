package test.dao;

import static tech.lapsa.lapsa.arquillian.shrinkwrap.ShrinkWrapTools.*;

import java.util.logging.Logger;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import tech.lapsa.insurance.dao.beans.AGeneralDAO;

@RunWith(Arquillian.class)
public abstract class ArquillianBaseTestCase {

    @Deployment(testable = true)
    public static Archive<?> createDeploymentEAR() {
	EnterpriseArchive ear = createEAR();
	ear.addAsModule(createOwnEJB());
	earAddRuntimeDependencies(ear);
	Logger.getGlobal().info(ear.toString(true));
	return ear;
    }

    private static JavaArchive createOwnEJB() {
	JavaArchive ejb = createEJB();
	ejb.addPackages(true, AGeneralDAO.class.getPackage());
	jarAddManifestFolderRecursive(ejb);
	Logger.getGlobal().info(ejb.toString(true));
	return ejb;
    }
}
