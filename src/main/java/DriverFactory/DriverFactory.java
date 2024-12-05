package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setupDriver(String browser){
        switch (browser.toLowerCase())
        {
            case "chrome" :
                ChromeOptions chromeoptions = new ChromeOptions();
                chromeoptions.addArguments("--start-maximized");
//                chromeoptions.addArguments("--headless");
//                chromeoptions.addArguments("--no-sandbox");
//                chromeoptions.addArguments("--disable-dev-shm-usage");
                driverThreadLocal.set(new ChromeDriver(chromeoptions));

                break;
            case "firefox":
                driverThreadLocal.set(new FirefoxDriver());
                break;
            default:
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");
                //options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--remote-debugging-port=9222");
               // options.setBinary("/usr/bin/microsoft-edge");
                driverThreadLocal.set(new EdgeDriver(options));
        }
    }
    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }
    public static void quite(){
        getDriver().quit();
        driverThreadLocal.remove();
    }
}
