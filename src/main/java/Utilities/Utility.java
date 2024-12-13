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
    private static final String SCREENSHOT_PATH = "test-outputs/screenshoot/";  // Path to save screenshots


    //TODO  Click on a Web Element after waiting until it becomes clickable
    public static void clickingOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    //TODO Send input data to a Web Element after waiting until it becomes visible
    public static void sendData(WebDriver driver, By locator, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }

    //TODO  Retrieve text from a Web Element after waiting until it becomes visible
    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    //TODO  General wait instance for more flexible use in multiple conditions
    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //TODO  Locate and return a Web Element without additional wait conditions
    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    //TODO Scrolling Using ActionsScroll to a specific element on the page using the Actions class
    public static void scrollUsingActions(WebDriver driver, By locator) {
        new Actions(driver).scrollToElement((WebElement) locator).perform();
        driver.findElement(locator);

    }

    //TODO  Scroll to a specific element using JavaScript Executor
    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));
    }


    //TODO  Get the current timestamp in a specific format
    public static String getTimesTemp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    //TODO  Interact with a dropdown element and select an option by visible text
    public static void dropDown(WebDriver driver, By locator, String option) {
        new Select(findWebElement(driver, locator)).deselectByVisibleText(option);
    }

    //TODO  Take a screenshot and save it with a timestamped filename
    public static void takeScreenShoot(WebDriver driver, String screenshotName) {

        try {       // Capture the screenshot
            File screenshotSRC = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Take a screenshot //
            // Define the file location and name
            File screenshotFile = new File(SCREENSHOT_PATH + screenshotName + "_" + getTimesTemp() + ".png");  // Save the screenshot using Apache Commons IO

            FileUtils.copyFile(screenshotSRC, screenshotFile); /// copy the screenshot "screenshotSRC" file to the screenshotFile location
            // Add the screenshot as an attachment in Allure report
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath()))); //(name , path)
        } catch (Exception e) {
            e.printStackTrace(); // message (System.err.println("Failed to take screenshot"))
        }

    }

    //TODO  Take a full-page screenshot using Shutterbug
    public static void takeFullScreenShoot(WebDriver driver, By locator) {
        try {       // Capture the screenshot
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .highlight(findWebElement(driver, locator)).save(SCREENSHOT_PATH);
        } catch (Exception e) {
            LogsUtilis.error(e.getMessage());
        }
    }

    //TODO     Generate a random number up to a specified upper bound
    public static int generateRandomNumber(int upperBound) {      // 0 >> upper-1 > 5
        return new Random().nextInt(upperBound) + 1;
    }

    //TODO    Generate a set of unique random numbers within a range
    public static Set<Integer> generateAddUniqueNumber(int numberOfProductsNeeded, int totalNumberOfProducts) {
        // Initialize a HashSet to store unique numbers
        Set<Integer> generatedNumber = new HashSet<>();
        // Keep generating numbers until the set contains the required amount
        while (generatedNumber.size() < numberOfProductsNeeded) {
            int renderNumber = generateRandomNumber(totalNumberOfProducts);
            // Add the random number to the set (duplicates are automatically ignored)
            generatedNumber.add(renderNumber);
        }
        // Return the set of unique numbers
        return generatedNumber;
     }

     //TODO   Verify if the current URL matches the expected URL
     public static boolean verifyURL(WebDriver driver , String expected){
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expected));
        }catch (Exception e){
            return false;  // Return false if URL does not match or timeout occurs
        }
        return true;
     }

     //TODO   Get the most recently modified file from a directory
    public static File getLastFile(String folderPath){
        // Create a File object for the provided folder path
        File forder = new File(folderPath);
        // Get a list of all files in the folder
        File[]files = forder.listFiles();
        if (files.length==0)  // Return null if no files are found
            return null;
        // Sort the files by last modified date in descending order
        Arrays.sort(files,Comparator.comparingLong(File::lastModified).reversed());
        // Return the first file (most recently modified file) from the sorted array
            return files[0];
    }

    //TODO   Retrieve all cookies from the current browser session
    public static Set<Cookie> getAllCookies(WebDriver driver){
        return driver.manage().getCookies();
    }

    //TODO   Restore browser session by adding previously saved cookies
    public static void restoreSession(WebDriver driver , Set<Cookie> cookies){
        for (Cookie cookie :cookies)
            driver.manage().addCookie(cookie);
    }
}
