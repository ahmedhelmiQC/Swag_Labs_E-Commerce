package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class  Utility {
    private static final  String SCREENSHOT_PATH = "test-outputs/screenshoot/";


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
        new Actions(driver).scrollToElement((WebElement) locator).perform();
        driver.findElement(locator);

    }
                    /// Scrolling Using Javascript///
    public static void scrolling(WebDriver driver,By locator){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",findWebElement(driver,locator));
    }


                         ///  Timestamp
    public static String getTimesTemp (){
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }
                      /// DropDown ///
    public static void dropDown(WebDriver driver , By locator,String option){
        new Select(findWebElement(driver,locator)).deselectByVisibleText(option);
    }
                      /// Take Screen Shoot //
    public static void takeScreenShoot(WebDriver driver , String screenshotName) {

        try {
            File screenshotSRC = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Take a screenshot //
            File screenshotFile = new File(SCREENSHOT_PATH + screenshotName + "_"+getTimesTemp()+".png");  // Save the screenshot using Apache Commons IO

            FileUtils.copyFile(screenshotSRC, screenshotFile); /// copy the screenshot "screenshotSRC" file to the screenshotFile location

                 // Add the screenshot as an attachment in Allure report
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath()))); //(name , path)
        }
        catch (Exception e){
            e.printStackTrace(); // message (System.err.println("Failed to take screenshot"))
        }

    }
                        /// Take Full Screen Shoot //
    public static void takeFullScreenShoot(WebDriver driver, By locator){
        Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                .highlight(findWebElement(driver,locator)).save(SCREENSHOT_PATH);
    }








}
