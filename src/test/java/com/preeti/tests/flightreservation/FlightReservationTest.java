package com.preeti.tests.flightreservation;

import com.preeti.pages.flightreservation.*;
import com.preeti.tests.AbstractTest;
import com.preeti.tests.flightreservation.model.FlightReservationTestData;
import com.preeti.util.Config;
import com.preeti.util.Constants;
import com.preeti.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {
    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters({"testDataPath"})
   public void initializePageVariables(String testDataPath){
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.setUserName(testData.firstName(), testData.password());
        registrationPage.setUserLoginCredentials(testData.email(), testData.password());
        registrationPage.setUserAddress(testData.street(), testData.city(), testData.zip());
        registrationPage.clickOnRegisterButton();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getActualFirstName(), testData.firstName());
        registrationConfirmationPage.flightSearchButton();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest(){
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.setPassenger(testData.passengersCount());
        flightSearchPage.clickSearchFlightButton();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest(){
        FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlights();
        flightSelectionPage.clickOnConfirmFlightButton();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
    }
}
