package com.preeti.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.preeti.Listener.TestListener;
import com.preeti.pages.vendarportal.DashboardPage;
import com.preeti.pages.vendarportal.LoginPage;
import com.preeti.util.Config;
import com.preeti.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners({TestListener.class})
public class AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected WebDriver driver;

    @BeforeSuite
    public void setUpConfig(){
        Config.initialize();
    }

    @BeforeTest

//    @Parameters({"browser"})
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        ctx.setAttribute(Constants.DRIVER, this.driver);
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();
        if(Constants.FIREFOX.equalsIgnoreCase((Config.get(Constants.BROWSER)))){
            capabilities = new FirefoxOptions();
        }
//        if(System.getProperty("browser").equalsIgnoreCase("chrome")){
////         if(browser.equalsIgnoreCase("chrome")){
//            capabilities = new ChromeOptions();
//        }
//        else{
//            capabilities = new FirefoxOptions();
//        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        log.info("Grid url: {}", url);
        return new RemoteWebDriver(new URL(url),capabilities);
    }

    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }

//    @AfterMethod
//    public void sleep(){
//        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
//    }

}
