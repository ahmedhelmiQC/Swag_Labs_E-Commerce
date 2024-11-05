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
public class TC04_CheckOut {
    String UserName = DataUtils.getJosnData("validlogin","username");
    String Password = DataUtils.getJosnData("validlogin","password");
    String FirstName = DataUtils.getJosnData("information","firstName");
    String LastName = DataUtils.getJosnData("information","lastName");
    String ZipCode = new Faker().number().digits(5);


    public TC04_CheckOut() throws FileNotFoundException {
    }
    @BeforeMethod
    public void login() throws IOException {
        String browser = getPropertyData("environment","Browser");
        LogsUtilis.info(getProperty(browser));
        setupDriver(browser);
        LogsUtilis.info("Edge Browser is Opened" );
        getDriver().get(getPropertyData("environment","BASE_URL"));
        LogsUtilis.info("Page Is Redirect To The Home Page");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new P01_LoginPage(getDriver()).enterUserName(UserName).enterPassword(Password).clickOnLoginButton();
    }
    @Test
    public void checkOutTC () throws IOException {
        new P02_ProductPage(getDriver()).addRandomProducts(5,6).clickOnCartButton();
        new P03_CartPage(getDriver()).clickingOnCheckOutButton();
        new P04_CheckOutPage(getDriver()).fillInformation(FirstName,LastName,ZipCode).clickOnContinueButton();
        LogsUtilis.info(FirstName + " "+LastName + " " + ZipCode);
        Assert.assertTrue(Utility.verifyURL(getDriver(), getPropertyData("environment","OVERVIEW_URl")));
    }
    @AfterMethod
    public void quit(){
        getDriver().quit();
    }
}
