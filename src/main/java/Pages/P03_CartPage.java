package Pages;

import Utilities.LogsUtilis;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_CartPage {

    static float totalPrice = 0;
    private final By priceOfSelectedProductLocator = By.xpath("//button[.='Remove']//preceding-sibling::div[@class='inventory_item_price']");
    private final By checkOutButton = By.id("checkout");
    private final WebDriver driver;

    public P03_CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTotalPrice(){
        try {
            List<WebElement> priceOfSelectedPrice = driver.findElements(priceOfSelectedProductLocator);
            for (int i = 1 ; i <= priceOfSelectedPrice.size() ; i++){
                By element = By.xpath("(//button[.='Remove']//preceding-sibling::div[@class='inventory_item_price'])["+ i +"]");
                String fullText = Utility.getText(driver,element);
                totalPrice+= Float.parseFloat(fullText.replace("$",""));
            }
            LogsUtilis.info("total Price : "+ totalPrice);
            return String.valueOf(totalPrice);
        }catch (Exception e){
            LogsUtilis.error(e.getMessage());
        return "0";
        }
    }
    public boolean comparingPrices(String price){
        return getTotalPrice().equals(price);
    }
    public P04_CheckOutPage clickingOnCheckOutButton(){
        LogsUtilis.info("The Page Is Redirected to The CheckOut Page");
        Utility.clickingOnElement(driver,checkOutButton);
        return new P04_CheckOutPage(driver);
    }



}


