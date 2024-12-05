package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_LoginPage;
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

import static DriverFactory.DriverFactory.*;
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
        // Condition ? ture or false
        String browser = System.getProperty("Browser") !=null? System.getProperty("browser") : getPropertyData("environment","Browser");
        LogsUtilis.info(System.getProperty("browser"));
        setupDriver(browser);
        LogsUtilis.info("The Browser is opened");
        getDriver().get(getPropertyData("environment","BASE_URL"));
        LogsUtilis.info("Page Is Redirect To The Home Page");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    @Test
    public void validLoginTC() throws IOException {
        new P01_LoginPage(getDriver()).enterUserName(UserName)
                .enterPassword(Password).clickOnLoginButton();
       Assert.assertTrue(new P01_LoginPage(getDriver())
               .assertLoginTC(getPropertyData("environment","HOME_URL")));

    }
    @AfterMethod
    public void quite()throws IOException{
        getDriver().quit();
    }
}
