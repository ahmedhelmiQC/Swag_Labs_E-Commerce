package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P02_ProductPage;
import Pages.P03_CartPage;
import Utilities.DataUtils;
import Utilities.LogsUtilis;
import Utilities.Utility;
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

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC03_Cart {
        String UserName = DataUtils.getJosnData("validlogin","username");
        String Password = DataUtils.getJosnData("validlogin","password");

    public TC03_Cart() throws FileNotFoundException {
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
    public void comparingPriceTC(){
        new P02_ProductPage(getDriver()).addRandomProducts(5,6).clickOnCartButton();
       Assert.assertTrue(new P03_CartPage(getDriver()).comparingPrices(new P02_ProductPage(getDriver()).getTotalPriceOfSelectedProducts()));
   }
   @Test
   public void clickONCheckOutButtonTC() throws IOException {
        new P02_ProductPage(getDriver()).addRandomProducts(3,6).clickOnCartButton();
        new P03_CartPage(getDriver()).clickingOnCheckOutButton();
        Assert.assertTrue(Utility.verifyURL(getDriver(),getPropertyData("environment","CheckOut_URL")));
   }

    @AfterMethod
    public void quit(){
        getDriver().quit();
    }
}
