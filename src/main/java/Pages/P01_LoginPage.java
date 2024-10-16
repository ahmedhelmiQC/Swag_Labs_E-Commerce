package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class P01_LoginPage {
    private static final By username = By.id("user-name");
    private static final By password = By.id("password");
    private static final By loginButton = By.id("login-button");
    public final WebDriver driver;

    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public P01_LoginPage enterUserName (String usernametext){
        Utilities.Utility.sendData(driver,username,usernametext);
        return this;
    }
    public P01_LoginPage enterPassword(String passwordtext){
        Utilities.Utility.sendData(driver,password,passwordtext);
        return this;
    }
    public P02_ProductPage clickOnLoginButton(){
        Utilities.Utility.clickingOnElement(driver,loginButton);
        return new P02_ProductPage(driver);
    }
   public boolean assertLoginTC(String expectedvalue){
        return driver.getCurrentUrl().equals(expectedvalue);
   }

}
