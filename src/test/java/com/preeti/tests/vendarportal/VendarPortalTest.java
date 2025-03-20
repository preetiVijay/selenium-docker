package com.preeti.tests.vendarportal;

import com.preeti.pages.vendarportal.DashboardPage;
import com.preeti.pages.vendarportal.LoginPage;
import com.preeti.tests.AbstractTest;
import com.preeti.tests.vendarportal.model.VendorPortalTestData;
import com.preeti.util.Config;
import com.preeti.util.Constants;
import com.preeti.util.JsonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendarPortalTest extends AbstractTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void initializePageObjects(String testDataPath){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.setUserCredentials(testData.username(), testData.password());
        loginPage.clickOnLoginButton();
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());
        //finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),testData.monthlyEarnings());
        Assert.assertEquals(dashboardPage.getAnnualEarning(),testData.annualEarnings());
        Assert.assertEquals(dashboardPage.getProfitMargin(),testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(),testData.availableInventory());
        //order history search
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(),testData.searchResultsCount());
        //logout
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardPage.userLogout();
        Assert.assertTrue(loginPage.isAt());
    }
}
