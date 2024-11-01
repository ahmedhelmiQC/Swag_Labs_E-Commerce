package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;

import Pages.P01_LoginPage;
import Pages.P02_ProductPage;
import Utilities.DataUtils;
import Utilities.LogsUtilis;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setupDriver;
import static Utilities.DataUtils.getPropertyData;
import static java.lang.System.getProperty;

@Listeners ({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC02_Product {
        String UserName = DataUtils.getJosnData("validlogin","username");
        String Password = DataUtils.getJosnData("validlogin","password");

    public TC02_Product() throws FileNotFoundException {
    }

    @BeforeMethod
    public void login() throws IOException {
        String browser = getPropertyData("environment","Browser");
        LogsUtilis.info(getProperty("browser"));
        setupDriver(browser);
        LogsUtilis.info("Edge Browser Is Opened ");
        getDriver().get(getPropertyData("environment","BASE_URL"));
        LogsUtilis.info("Page Is Redirect To Home Page");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new P01_LoginPage(getDriver()).enterUserName(UserName).enterPassword(Password).clickOnLoginButton();
    }
   @Test
    public void addAllProductTC(){
        new P02_ProductPage(getDriver()).addAllProductsToCart();
        Assert.assertTrue(new P02_ProductPage(getDriver()).comparingNumberOfSelectedWithNumberInCart());
    }
    @Test
    public void addRandomProductsToCartTC(){
        new P02_ProductPage(getDriver()).addRandomProducts(5,6);
        Assert.assertTrue(new P02_ProductPage(getDriver()).comparingNumberOfSelectedWithNumberInCart());
    }

    @AfterMethod
    public void quit(){
        getDriver().quit();
    }
}
