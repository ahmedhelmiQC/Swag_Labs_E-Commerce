package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.findWebElement;

public class P06_FinshOrderPage {

    final private By thanksMessage = By.tagName("h2");
    private final WebDriver driver;

    public P06_FinshOrderPage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean checkVisibilityOfThanksMessage()
    {
        return findWebElement(driver,thanksMessage).isDisplayed();
    }
}
