package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
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
@Listeners ({ IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC01_Login {
    private final String UserName = DataUtils.getJosnData("validLogin","username");
    private final String Password = DataUtils.getJosnData("validLogin","password");

    public TC01_Login() throws FileNotFoundException {
    }

    @BeforeMethod
    public void setup() throws IOException {
        String browser = getPropertyData("environment","Browser");
        LogsUtils.info(browser);
        setupDriver(browser);
      LogsUtils.info("EdgeDriver is opened");
        getDriver().get(getPropertyData("environment","BASE_URL"));
        LogsUtils.info("Page Is Redirect To The Home Page");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void validLoginTC() throws IOException {
        new P01_LoginPage(getDriver()).enterUserName(UserName)
                .enterPassword(Password).clickOnLoginButton();
       Assert.assertFalse(new P01_LoginPage(getDriver()).assertLoginTC(getProperty("environment","Home_URL")));

    }
    @AfterMethod
    public void quite(){
        getDriver().quit();
    }
}
