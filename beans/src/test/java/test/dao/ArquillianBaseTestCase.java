package test.dao;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.runner.RunWith;

import tech.lapsa.insurance.dao.beans.AEntityManagerDAO;
import tech.lapsa.lapsa.arquillian.archive.ArchiveBuilderFactory;

@RunWith(Arquillian.class)
public abstract class ArquillianBaseTestCase {

    @Deployment(testable = true)
    public static Archive<?> createDeploymentEAR() {
	return ArchiveBuilderFactory.newEarBuilder() //
		.withModule(ArchiveBuilderFactory.newEjbBuilder() //
			.withPackageOf(AEntityManagerDAO.class) //
			.withManifestFolder() //
			.build() //
			.dumpingTo(System.out::println)) //
		.withRuntimeDependencies()
		.build() //
		.dumpingTo(System.out::println) //
		.asEnterpriseArchive();
    }
}
