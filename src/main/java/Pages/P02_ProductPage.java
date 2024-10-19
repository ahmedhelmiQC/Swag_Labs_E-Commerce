package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P02_ProductPage {

    private static List<WebElement> allProducts;
    private final By addToCartButtonForAllProducts = By.xpath("//button[@class]");
    private final By clickOnCartButton  = By.className("shopping_cart_link");
    private final By numberOfSelectedProducts = By.xpath("//button[.='Remove']");
    private final By numberOfProductsOnCart = By.className("shopping_cart_badge");
    private final By priceOfSelectedProducts = By.className("inventory_item_price");
    private final WebDriver driver;

    public P02_ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public P02_ProductPage addAllProductsToCart(){
        allProducts = driver.findElements(addToCartButtonForAllProducts);
        for (int i=1 ; i<=allProducts.size() ; i++){
            By addToCartButtonForAllProducts = By.xpath("(//button[@class])[\"+i+\"]");
            Utility.clickingOnElement(driver,addToCartButtonForAllProducts);
        }
        return this;
    }


}
