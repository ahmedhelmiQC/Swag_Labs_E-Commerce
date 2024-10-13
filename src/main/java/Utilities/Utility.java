package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class  Utility {


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

                         /// Scrolling Using Actions///
    public static void scrollUsingActions (WebDriver driver , By locator){
        Actions actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(locator)).perform();

    }
                    /// Scrolling Using Javascript///
    public static void scrolling(WebDriver driver,By locator){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",findWebElement(driver,locator));
    }
                      /// DropDown ///
    public static void dropDown(WebDriver driver , By locator){
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
                      /// Take Screen Shoot //
    public static void takeScreenShoot(WebDriver driver , String imageName) throws IOException {
        String path = "test-outputs/screenshoot/";
    File src =( (TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); // Take a screenshot //
    File target = new File(path+imageName+".png");  // Save the screenshot using Apache Commons IO

        FileUtils.copyFile(src,target); /// copy the screenshot "src" file to the target location

    }






}
