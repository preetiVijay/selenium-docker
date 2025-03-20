package com.preeti.pages.vendarportal;

import com.preeti.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);
    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarnings;

    @FindBy(id = "annual-earning")
    private WebElement annualEarnings;

    @FindBy(id = "profit-margin")
    private WebElement profitMargin;

    @FindBy(id = "available-inventory")
    private WebElement availableInventory;

    @FindBy(css = "#dataTable_filter input")
    private WebElement searchInput;

    @FindBy(id = "dataTable_info")
    private WebElement totalEntriesBySearch;

    @FindBy(css = "img.img-profile")
    private WebElement userProfilePicture;

    @FindBy(linkText = "Logout")
    private WebElement logoutButton;

    @FindBy(css = ".modal-footer a")
    private WebElement modalLogoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarnings));
        return this.monthlyEarnings.isDisplayed();
    }

    public String getMonthlyEarning(){
        return this.monthlyEarnings.getText();
    }

    public String getAnnualEarning(){
        return this.annualEarnings.getText();
    }

    public String getProfitMargin(){
        return this.profitMargin.getText();
    }

    public String getAvailableInventory(){
        return this.availableInventory.getText();
    }

    public void searchOrderHistoryBy(String keyword){
        this.searchInput.sendKeys(keyword);
    }

    public int getSearchResultsCount(){
        String[] text = this.totalEntriesBySearch.getText().split(" ");
        int count = Integer.parseInt(text[5]);
        log.info("Total number of entries by given text {}",count);
        return count;
    }

    public void userLogout(){
        this.userProfilePicture.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutButton));
        this.logoutButton.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.modalLogoutButton));
        this.modalLogoutButton.click();
    }
}
