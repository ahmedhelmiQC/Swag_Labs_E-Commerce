package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.*;
import Utilities.DataUtils;
import Utilities.LogsUtilis;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setupDriver;
import static Utilities.DataUtils.getPropertyData;
@Listeners ({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC06_FinishOrder {

    private final String UserName = DataUtils.getJosnData("validlogin","username");
    private final String Password = DataUtils.getJosnData("validlogin","password");
    private final String FirstName = DataUtils.getJosnData("information","firstName");
    private final String LastName = DataUtils.getJosnData("information","lastName");
    private final String ZipCode = new Faker().number().digits(5);
    public TC06_FinishOrder() throws FileNotFoundException {
    }
//    @BeforeClass
//    private void login() throws IOException {
//        setupDriver(getPropertyData("environment","Browser"));
//        LogsUtilis.info("The Edge Browser Is Opened");
//        getDriver().get(getPropertyData("environment","BASE_URL"));
//        LogsUtilis.info("Page Redirect To The Home Page");
//        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        new P01_LoginPage(getDriver()).enterUserName(UserName).enterPassword(Password).clickOnLoginButton();
//    }
    @BeforeMethod
    private void setup() throws IOException {
        setupDriver(getPropertyData("environment","Browser"));
        LogsUtilis.info("The Edge Browser Is Opened");
        getDriver().get(getPropertyData("environment","BASE_URL"));
        LogsUtilis.info("Page Redirect To The Home Page");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //new P01_LoginPage(getDriver()).enterUserName(UserName).enterPassword(Password).clickOnLoginButton();
    }
    @Test
    public void verifyThankMessageIsDisplayedTC(){
        //TODO Login Step
        new P01_LoginPage(getDriver()).enterUserName(UserName)
                .enterPassword(Password).clickOnLoginButton();
        //TODO add all product to cart Step
        new P02_ProductPage(getDriver()).addAllProductsToCart().clickOnCartButton();
        //TODO Go To The Checkout Page Step
        new P03_CartPage(getDriver()).clickingOnCheckOutButton();
        //TODO fill all information Step
        new P04_CheckOutPage(getDriver()).fillInformation(FirstName,LastName,ZipCode).clickOnContinueButton();
        LogsUtilis.info(FirstName + "  "  +LastName + " " + ZipCode);
        //TODO Go To The Finish Order Page Step
        new P05_OverView(getDriver()).clickOnFinishButton();

        //TODO verify the thanks message is displayed
        Assert.assertTrue(new P06_FinshOrderPage(getDriver()).checkVisibilityOfThanksMessage());
    }

    @AfterMethod
    private void quite(){
        getDriver().quit();
    }
}
