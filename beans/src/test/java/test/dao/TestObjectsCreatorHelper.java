package test.dao;

import java.time.Instant;
import java.time.LocalDate;

import com.lapsa.fin.FinCurrency;
import com.lapsa.insurance.domain.CalculationData;
import com.lapsa.insurance.domain.ContactData;
import com.lapsa.insurance.domain.DriverLicenseData;
import com.lapsa.insurance.domain.IdentityCardData;
import com.lapsa.insurance.domain.ObtainingData;
import com.lapsa.insurance.domain.OriginData;
import com.lapsa.insurance.domain.PaymentData;
import com.lapsa.insurance.domain.PersonalData;
import com.lapsa.insurance.domain.RequesterData;
import com.lapsa.insurance.domain.ResidenceData;
import com.lapsa.insurance.domain.VehicleCertificateData;
import com.lapsa.insurance.domain.policy.Policy;
import com.lapsa.insurance.domain.policy.PolicyDriver;
import com.lapsa.insurance.domain.policy.PolicyRequest;
import com.lapsa.insurance.domain.policy.PolicyVehicle;
import com.lapsa.insurance.elements.DeliveryTimeSlot;
import com.lapsa.insurance.elements.IdentityCardType;
import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.InsuredAgeClass;
import com.lapsa.insurance.elements.InsuredExpirienceClass;
import com.lapsa.insurance.elements.ObtainingMethod;
import com.lapsa.insurance.elements.PaymentStatus;
import com.lapsa.insurance.elements.Sex;
import com.lapsa.insurance.elements.VehicleAgeClass;
import com.lapsa.insurance.elements.VehicleClass;
import com.lapsa.international.country.Country;
import com.lapsa.international.localization.LocalizationLanguage;
import com.lapsa.international.phone.CountryCode;
import com.lapsa.international.phone.PhoneNumber;
import com.lapsa.kz.country.KZArea;
import com.lapsa.kz.country.KZCity;

import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import tech.lapsa.kz.vehicle.VehicleRegNumber;

public class TestObjectsCreatorHelper {

    private static final String LAPSA_TEST_GMAIL_COM = "lapsa.test@gmail.com";

    public static PolicyRequest generatePolicyRequest() {
	PolicyRequest request = new PolicyRequest();
	request.setCreated(Instant.now());

	{
	    Policy policy = request.getPolicy();
	    {
		CalculationData calculation = policy.getCalculation();
		calculation.setCalculatedPremiumCost(500d);
		calculation.setPremiumCurrency(FinCurrency.KZT);
	    }
	    {
		{
		    PolicyDriver driver = policy.addDriver(new PolicyDriver());
		    driver.setIdNumber(TaxpayerNumber.of("123123123127"));
		    driver.setHasAnyPrivilege(false);
		    driver.setAgeClass(InsuredAgeClass.OVER25);
		    driver.setExpirienceClass(InsuredExpirienceClass.MORE2);
		    driver.setInsuranceClassType(InsuranceClassType.CLASS_3);
		    {
			ContactData contactData = driver.getContactData();
			contactData.setEmail(LAPSA_TEST_GMAIL_COM);
			contactData.setPhone(PhoneNumber.of(CountryCode.KZ, "701", "9377979"));
		    }
		    {
			DriverLicenseData driverLicenseData = driver.getDriverLicenseData();
			driverLicenseData.setNumber("123123");
			driverLicenseData.setDateOfIssue(LocalDate.now());
		    }
		    {
			IdentityCardData identityCardData = driver.getIdentityCardData();
			identityCardData.setType(IdentityCardType.ID_CARD);
			identityCardData.setDateOfIssue(LocalDate.now());
			identityCardData.setIssuingAuthority("МВД РК");
			identityCardData.setNumber("12321321");
		    }
		    {
			OriginData originData = driver.getOriginData();
			originData.setCountry(Country.KAZ);
		    }
		    {
			PersonalData personalData = driver.getPersonalData();
			personalData.setSurename("Исаев");
			personalData.setName("Вадим");
			personalData.setPatronymic("Олегович");
			personalData.setSex(Sex.MALE);
			personalData.setDayOfBirth(LocalDate.now());
		    }
		    {
			ResidenceData residenceData = driver.getResidenceData();
			residenceData.setAddress("ул. Курмангазы, 104");
			residenceData.setCity(KZCity.ALM);
			residenceData.setResident(true);
		    }
		}

	    }
	    {
		{
		    PolicyVehicle vehicle = policy.addVehicle(new PolicyVehicle());
		    vehicle.setCity(KZCity.ALM);
		    vehicle.setForcedMajorCity(true);
		    vehicle.setArea(KZArea.GALM);
		    vehicle.setVehicleAgeClass(VehicleAgeClass.OVER7);
		    vehicle.setVehicleClass(VehicleClass.CAR);

		    vehicle.setColor("черный");
		    vehicle.setManufacturer("Infiniti");
		    vehicle.setModel("QX70");
		    vehicle.setVinCode("QWEWQEWQEWQEWQ");
		    vehicle.setYearOfManufacture(2014);
		    {
			VehicleCertificateData certificateData = vehicle.getCertificateData();
			certificateData.setDateOfIssue(LocalDate.now());
			certificateData.setNumber("123123");
			certificateData.setRegistrationNumber(VehicleRegNumber.of("237blm01"));
		    }

		}
	    }
	}

	{
	    ObtainingData obtaining = request.getObtaining();
	    obtaining.setMethod(ObtainingMethod.DELIVERY);
	    obtaining.setDeliveryCity(KZCity.ALM);
	    obtaining.setDeliveryDate(LocalDate.now());
	    obtaining.setDeliveryTime(DeliveryTimeSlot.DURING_THE_DAY);
	    obtaining.setDeliveryAddress("ул. Желтоксан, 59");
	}

	{
	    PaymentData payment = request.getPayment();
	    payment.setStatus(PaymentStatus.PENDING);
	    payment.setInvoiceNumber("921321321321");
	}

	{
	    RequesterData requester = request.getRequester();
	    requester.setName("Исаев Вадим Олегович");
	    requester.setEmail(LAPSA_TEST_GMAIL_COM);
	    requester.setPhone(PhoneNumber.of(CountryCode.KZ, "701", "9377979"));
	    requester.setPreferLanguage(LocalizationLanguage.RUSSIAN);
	    requester.setAllowSpam(true);
	    requester.setAllowProcessPersonalData(true);
	}
	return request;
    }
}