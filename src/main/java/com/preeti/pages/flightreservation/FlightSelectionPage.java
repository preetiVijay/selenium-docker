package com.preeti.pages.flightreservation;

import com.preeti.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage {

    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlights;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlights;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightButton;

    public FlightSelectionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightButton));
        return this.confirmFlightButton.isDisplayed();
    }

    public void selectFlights(){
        int randomDeparture = ThreadLocalRandom.current().nextInt(0,departureFlights.size());
        this.departureFlights.get(randomDeparture).click();
        int randomArrival = ThreadLocalRandom.current().nextInt(0,this.arrivalFlights.size());
        this.arrivalFlights.get(randomArrival).click();
    }

    public void clickOnConfirmFlightButton(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.confirmFlightButton));
        this.confirmFlightButton.click();
    }
}
