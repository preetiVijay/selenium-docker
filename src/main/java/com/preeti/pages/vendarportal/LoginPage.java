package com.preeti.pages.vendarportal;

import com.preeti.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.username));
        return this.username.isDisplayed();
    }

    public void goTo(String url){
        this.driver.get(url);
    }

    public void setUserCredentials(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
    }

    public void clickOnLoginButton(){
        this.loginButton.click();
    }
}
