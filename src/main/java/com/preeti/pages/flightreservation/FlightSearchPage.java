package com.preeti.pages.flightreservation;

import com.preeti.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;

public class FlightSearchPage extends AbstractPage {

    @FindBy(id = "passengers")
    private WebElement passengersList;

    @FindBy(id = "search-flights")
    private WebElement searchFlightButton;

    public FlightSearchPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengersList));
        return this.passengersList.isDisplayed();
    }

    public void setPassenger(String numberOfPassengers){
        Select passengers = new Select(this.passengersList);
        passengers.selectByValue(numberOfPassengers);
    }

    public void clickSearchFlightButton(){
        this.searchFlightButton.click();
    }
}
