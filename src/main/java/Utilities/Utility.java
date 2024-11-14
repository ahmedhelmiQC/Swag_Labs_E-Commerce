package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import dev.failsafe.internal.util.Assert;
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
import java.util.*;

public class  Utility {
    private static final String SCREENSHOT_PATH = "test-outputs/screenshoot/";


    //TODO  clicking On Element
    public static void clickingOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    //TODO Send Data
    public static void sendData(WebDriver driver, By locator, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }

    //TODO  Get Text
    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    //TODO  General Wait
    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //TODO  findWebElement
    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    //TODO Scrolling Using Actions
    public static void scrollUsingActions(WebDriver driver, By locator) {
        new Actions(driver).scrollToElement((WebElement) locator).perform();
        driver.findElement(locator);

    }

    //TODO  Scrolling Using Javascript
    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));
    }


    //TODO  get Times Temp
    public static String getTimesTemp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    //TODO  DropDown
    public static void dropDown(WebDriver driver, By locator, String option) {
        new Select(findWebElement(driver, locator)).deselectByVisibleText(option);
    }

    //TODO  Take Screen Shoot
    public static void takeScreenShoot(WebDriver driver, String screenshotName) {

        try {
            File screenshotSRC = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Take a screenshot //
            File screenshotFile = new File(SCREENSHOT_PATH + screenshotName + "_" + getTimesTemp() + ".png");  // Save the screenshot using Apache Commons IO

            FileUtils.copyFile(screenshotSRC, screenshotFile); /// copy the screenshot "screenshotSRC" file to the screenshotFile location

            // Add the screenshot as an attachment in Allure report
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath()))); //(name , path)
        } catch (Exception e) {
            e.printStackTrace(); // message (System.err.println("Failed to take screenshot"))
        }

    }

    //TODO  Take Full Screen Shoot
    public static void takeFullScreenShoot(WebDriver driver, By locator) {
        try {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .highlight(findWebElement(driver, locator)).save(SCREENSHOT_PATH);
        } catch (Exception e) {
            LogsUtilis.error(e.getMessage());
        }
    }

    //TODO Random Number
    public static int generateRandomNumber(int upperBound) {      // 0 >> upper-1 > 5
        return new Random().nextInt(upperBound) + 1;
    }

    //TODO  Set >> unique 1,2,3,4,5  > condition
    public static Set<Integer> generateAddUniqueNumber(int numberOfProductsNeeded, int totalNumberOfProducts) {
        Set<Integer> generatedNumber = new HashSet<>();
        while (generatedNumber.size() < numberOfProductsNeeded) {
            int renderNumber = generateRandomNumber(totalNumberOfProducts);
            generatedNumber.add(renderNumber);
        }
        return generatedNumber;
     }
     //TODO Verify URL
     public static boolean verifyURL(WebDriver driver , String expected){
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expected));
        }catch (Exception e){
            return false;
        }
        return true;
     }
     //TODO Get Last Log File
    public static File getLastFile(String folderPath){
        File forder = new File(folderPath);
        File[]files = forder.listFiles();
        if (files.length==0)
            return null;
            Arrays.sort(files,Comparator.comparingLong(File::lastModified).reversed());
            return files[0];
    }
}
