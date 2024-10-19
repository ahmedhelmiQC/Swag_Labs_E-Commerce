package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class P01_LoginPage {

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private WebDriver drive;


    public P01_LoginPage (WebDriver driver){
        this.drive = driver;
    }
    public P01_LoginPage enterUserName(String usernametext){
        Utility.sendData(drive,username,usernametext);
        return this;
    }
    public P01_LoginPage enterPassword(String passwordText){
        Utility.sendData(drive,password,passwordText);
    return this;
    }
    public P02_ProductPage clickOnLoginButton(){
        Utility.clickingOnElement(drive,loginButton);
        return new P02_ProductPage(drive);
    }

    public boolean assertLoginTC(String expectedValue){
        return drive.getCurrentUrl().equals(expectedValue);
    }
}
