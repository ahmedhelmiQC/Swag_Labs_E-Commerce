package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_CheckOutPage {
    private final WebDriver driver;

    final private By firstName = By.id("first-name");
    final private By lastName = By.id("last-name");
    final private By zipCode = By.id("postal-code");
    final private By continueButton = By.id("continue");
    public P04_CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }
    public P04_CheckOutPage fillInformation(String fName , String lName , String code){
        Utility.sendData(driver,firstName,fName);
        Utility.sendData(driver,lastName,lName);
        Utility.sendData(driver,zipCode,code);
        return this;
    }
    public P05_OverView clickOnContinueButton(){
        Utility.clickingOnElement(driver,continueButton);
        return new P05_OverView(driver);

    }

}
