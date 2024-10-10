package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utility {
                    /// Clicking //
    public static void clickingOnElement(WebDriver driver , By locator){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }
                /// Send Data//
    public static void sendData(WebDriver driver,By locator, String data){
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }
                    // Get Data ///
    public static void getData(WebDriver driver, By locator){
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).getText();
    }
                /// General Wait//
    public static WebDriverWait generalWait(WebDriver driver){
        return new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public static WebElement findWebElement(WebDriver driver,By locator)
    {
        return driver.findElement(locator);
    }
            /// Scrolling ///
    public static void scrolling(WebDriver driver,By locator){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",findWebElement(driver,locator))
    }
            /// Drop Down ///
    public static void dropDown(WebDriver driver , By locator){
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }




}
