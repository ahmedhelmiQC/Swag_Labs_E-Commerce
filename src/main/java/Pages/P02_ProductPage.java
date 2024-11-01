package Pages;

import Utilities.LogsUtilis;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class P02_ProductPage {

    private static List<WebElement> allProducts;
    private static List<WebElement> selectedProducts;
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
        LogsUtilis.info(" Number Of All Product : " + allProducts.size());
        for (int i=1 ; i<=allProducts.size() ; i++){
            By addToCartButtonForAllProducts = By.xpath("(//button[@class])["+ i +"]");
            Utility.clickingOnElement(driver,addToCartButtonForAllProducts);
        }
        return this;
    }
    public String getNumberOfProductsOnCartIcon(){
        LogsUtilis.info("Number Of Product On Cart : " + Utility.getText(driver,numberOfProductsOnCart));
        try {
            return Utility.getText(driver,numberOfProductsOnCart);
        }
        catch (Exception e){
            return "0";
        }
    }
    public String getNumberOfSelectedProducts(){
         selectedProducts = driver.findElements(numberOfSelectedProducts);
        LogsUtilis.info("Number Of Selected Products : " + selectedProducts.size());
        try {
         return String.valueOf(selectedProducts.size());
        }
        catch (Exception e){
            return "0";
        }
    }

    public By getNumberOfProductsOnCart() {
        return numberOfProductsOnCart;
    }

    public boolean comparingNumberOfSelectedWithNumberInCart(){
        return getNumberOfProductsOnCartIcon().equals(getNumberOfSelectedProducts());
    }
    public P03_Cart clickOnCartButton(){
        Utility.clickingOnElement(driver,clickOnCartButton);
        return new P03_Cart(driver);

    }
    public P02_ProductPage addRandomProducts(int numberOfNeededProducts , int totalNumberOfProducts) {
        Set<Integer> randomNumbers = Utility.generateAddUniqueNumber(numberOfNeededProducts, totalNumberOfProducts);
        LogsUtilis.info("Random Number :" + randomNumbers );
        for (int random : randomNumbers) {
            By addToCartButtonForAllProducts = By.xpath("(//button[@class])[" + random + "]");
            Utility.clickingOnElement(driver, addToCartButtonForAllProducts);
        }
        return this;
    }




}
