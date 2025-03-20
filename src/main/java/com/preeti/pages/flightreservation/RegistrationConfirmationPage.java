package com.preeti.pages.flightreservation;

import com.preeti.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id = "go-to-flights-search")
    private WebElement flightSearch;

    @FindBy(css = "#registration-confirmation-section p b")
    private WebElement firstName;

    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightSearch));
        return this.flightSearch.isDisplayed();
    }

    public void flightSearchButton(){
        this.flightSearch.click();
    }

    public String getActualFirstName(){
        return this.firstName.getText();
    }
}
