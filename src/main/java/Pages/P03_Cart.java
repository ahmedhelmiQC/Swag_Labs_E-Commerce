package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_Cart {

    private final By checkOutButton = By.id("checkout");
    private final WebDriver driver;

    public P03_Cart(WebDriver driver) {
        this.driver = driver;
    }
}
