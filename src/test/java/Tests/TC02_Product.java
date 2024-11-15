package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;

import Pages.P01_LoginPage;
import Pages.P02_ProductPage;
import Utilities.DataUtils;
import Utilities.LogsUtilis;
import Utilities.Utility;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setupDriver;
import static Utilities.DataUtils.getPropertyData;
import static Utilities.Utility.getAllCookies;
import static Utilities.Utility.restoreSession;
import static java.lang.System.getProperty;

@Listeners ({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC02_Product {
        String UserName = DataUtils.getJosnData("validlogin","username");
        String Password = DataUtils.getJosnData("validlogin","password");
       private Set<Cookie>cookies;

    public TC02_Product() throws FileNotFoundException {
    }
    @BeforeClass
    public void login() throws IOException {
        String browser= getPropertyData("environment","Browser");
        LogsUtilis.info("The Edge Browser Opened");
        setupDriver(browser);
        LogsUtilis.info("The Page Redirect To Home Page");
        getDriver().get(getPropertyData("environment","BASE_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new P01_LoginPage(getDriver()).enterUserName(UserName).enterPassword(Password).clickOnLoginButton();
        cookies = getAllCookies(getDriver());
        getDriver().quit();
    }

    @BeforeMethod
    public void setup() throws IOException {
        String browser = getPropertyData("environment","Browser");
        LogsUtilis.info("Edge Browser Is Opened ");
        setupDriver(browser);
        getDriver().get(getPropertyData("environment","BASE_URL"));
        LogsUtilis.info("Page Is Redirect To Home Page");
       restoreSession(getDriver(),cookies);
        getDriver().get(getPropertyData("environment","HOME_URL"));
        getDriver().navigate().refresh();

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
    @Test
    public void clickOnCartButton() throws IOException {
        new P02_ProductPage(getDriver()).clickOnCartButton();
        Assert.assertTrue(new P02_ProductPage(getDriver()).verifyCartPageURL(getPropertyData("environment","CART_URL")));
    }

    @AfterMethod
    public void quit(){
        getDriver().quit();
    }
    @AfterClass
    public void deleteSession(){
        cookies.clear();
    }

}
