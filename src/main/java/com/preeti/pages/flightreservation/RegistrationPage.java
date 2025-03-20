package com.preeti.pages.flightreservation;

import com.preeti.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends AbstractPage {

    @FindBy(id = "firstName")
    private WebElement firstname;

    @FindBy(id = "lastName")
    private WebElement lastname;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(name = "street")
    private WebElement street;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "zip")
    private WebElement zip;

    @FindBy(id = "register-btn")
    private WebElement register;

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.firstname));
        return this.firstname.isDisplayed();
    }

    public void goTo(String url){
        this.driver.get(url);
    }

    public void setUserName(String firstname, String lastname){
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
    }

    public void setUserLoginCredentials(String email, String password){
        this.email.sendKeys(email);
        this.password.sendKeys(password);
    }

    public void setUserAddress(String street, String city, String zip){
        this.street.sendKeys(street);
        this.city.sendKeys(city);
        this.zip.sendKeys(zip);
    }

    public void clickOnRegisterButton(){
        this.register.click();
    }
}
