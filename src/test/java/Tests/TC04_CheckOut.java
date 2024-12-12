package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P02_ProductPage;
import Pages.P03_CartPage;
import Pages.P04_CheckOutPage;
import Utilities.DataUtils;
import Utilities.LogsUtilis;
import Utilities.Utility;
import com.github.javafaker.Faker;
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

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC04_CheckOut {
    private Set<Cookie>cookies;
    String UserName = DataUtils.getJosnData("validlogin","username");
    String Password = DataUtils.getJosnData("validlogin","password");
    String FirstName = DataUtils.getJosnData("information","firstName");
    String LastName = DataUtils.getJosnData("information","lastName");
    String ZipCode = new Faker().number().digits(5);


    public TC04_CheckOut() throws FileNotFoundException {
    }
    @BeforeClass
    public void login() throws IOException {
        String browser = System.getProperty("browser") !=null ? System.getProperty("browser") : getPropertyData("environment","Browser");
        LogsUtilis.info(System.getProperty("browser"));
        setupDriver(browser);
        //LogsUtilis.info("The Edge Browser Is Opened");
        getDriver().get(getPropertyData("environment","BASE_URL"));
        LogsUtilis.info("The Page is Redirected To URL");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new P01_LoginPage(getDriver()).enterUserName(UserName).enterPassword(Password).clickOnLoginButton();
        cookies = getAllCookies(getDriver());
        getDriver().quit();
    }
    @BeforeMethod
    public void setup() throws IOException {
        // Condition ? ture or false
        String browser = System.getProperty("browser") !=null ? System.getProperty("browser") : getPropertyData("environment","Browser");
                LogsUtilis.info(System.getProperty("browser"));
        setupDriver(browser);

        getDriver().get(getPropertyData("environment","BASE_URL"));
            LogsUtilis.info("Page Is Redirect To The Home Page");
        restoreSession(getDriver(),cookies);
        getDriver().get(getPropertyData("environment","HOME_URL"));

        getDriver().navigate().refresh();
    }
    @Test
    public void checkOutTC () throws IOException {
        //TODO Add Random Products Step
        new P02_ProductPage(getDriver()).addRandomProducts(5,6).clickOnCartButton();
        //TODO Go To CheckOut Page Step
        new P03_CartPage(getDriver()).clickingOnCheckOutButton();
        //TODO Fill All Information Step
        new P04_CheckOutPage(getDriver()).fillInformation(FirstName,LastName,ZipCode).clickOnContinueButton();
        LogsUtilis.info(FirstName + " "+LastName + " " + ZipCode);
        //TODO Verify Redirect To Overview
        Assert.assertTrue(Utility.verifyURL(getDriver(), getPropertyData("environment","OVERVIEW_URl")));
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
