package Pages;

import Utilities.LogsUtilis;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_OverView {

    final private By subTotalAmount = By.className("summary_subtotal_label");
    final private By tax = By.className("summary_tax_label");
    final private By totalAmount = By.className("summary_total_label");
    private final WebDriver driver;

    public P05_OverView(WebDriver driver) {
        this.driver = driver;
    }

    public float getsubTotalAmount(){
        LogsUtilis.info("subTotalAmount"+ Utility.getText(driver,subTotalAmount).replace("Item total: $",""));
           return Float.parseFloat(Utility.getText(driver,subTotalAmount).replace("Item total: $",""));
    }
    public float getTax(){
        LogsUtilis.info("Tax :" + Utility.getText(driver,tax).replace("Tax: $",""));
         return  Float.parseFloat(Utility.getText(driver,tax).replace("Tax: $",""));
    }
    public float actualTotalAmount(){
        LogsUtilis.info("Actual Total Amount" + Utility.getText(driver,totalAmount).replace("Total: $",""));
        return Float.parseFloat(Utility.getText(driver,totalAmount).replace("Total: $",""));
    }
    public String calculateTotalPrice(){
        LogsUtilis.info("Calculating Total Price" + (getsubTotalAmount()+getTax()));
        return String.valueOf(getsubTotalAmount()+getTax());
    }
    public boolean comparingPrice(){
        return calculateTotalPrice().equals(String.valueOf(actualTotalAmount()));
    }
}
