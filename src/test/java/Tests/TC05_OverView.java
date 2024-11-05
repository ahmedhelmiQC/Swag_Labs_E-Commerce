package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.*;
import Utilities.DataUtils;
import Utilities.LogsUtilis;
import Utilities.Utility;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setupDriver;
import static Utilities.DataUtils.getPropertyData;
import static java.lang.System.getProperty;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC05_OverView {
    private final String UserName  = DataUtils.getJosnData("validlogin", "username");
    private final String Password  = DataUtils.getJosnData("validlogin","password");
    private final String FirstName = DataUtils.getJosnData("information","firstName");
    private final String LastName  = DataUtils.getJosnData("information","lastName");
    private final String ZipCode   = new Faker().number().digits(5);

    public TC05_OverView() throws FileNotFoundException {
    }
//    @BeforeClass
//    public void login() throws IOException {
//        setupDriver(getPropertyData("environment","Browser"));
//        LogsUtilis.info("Edge Browser Is Opened");
//        getDriver().get(getPropertyData("environment", "BASE_URL"));
//        LogsUtilis.info("Page Redirect To The Home Page");
//        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        new P01_LoginPage(getDriver()).enterUserName(UserName).enterPassword(Password).clickOnLoginButton();
//    }
    @BeforeMethod
    public void setup() throws IOException {
        String browser = getPropertyData("environment","Browser");
        LogsUtilis.info("Edge Browser Is Opened");
        setupDriver(browser);
        getDriver().get(getPropertyData("environment","BASE_URL"));
        LogsUtilis.info("The Page Redirected To The Home Page");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new P01_LoginPage(getDriver()).enterUserName(UserName).enterPassword(Password).clickOnLoginButton();
    }

    @Test
    public void comparingTotalPriceTC(){
        new P02_ProductPage(getDriver()).addAllProductsToCart().clickOnCartButton();
        new P03_CartPage(getDriver()).clickingOnCheckOutButton();
        new P04_CheckOutPage(getDriver()).fillInformation(FirstName,LastName,ZipCode).clickOnContinueButton();
        Assert.assertTrue(new P05_OverView(getDriver()).comparingPrice());
    }
    @AfterMethod
    public void quit(){
        getDriver().quit();
    }
}
