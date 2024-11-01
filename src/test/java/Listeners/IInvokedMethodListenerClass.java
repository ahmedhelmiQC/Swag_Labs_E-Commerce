package Listeners;

import Pages.P02_ProductPage;
import Utilities.LogsUtilis;
import Utilities.Utility;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;


import java.io.File;

import static DriverFactory.DriverFactory.getDriver;


public class IInvokedMethodListenerClass implements IInvokedMethodListener {

    public void beforeInvocation(
            IInvokedMethod method, ITestResult testResult, ITestContext context) {

    }


   public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
       //  File logFile = Utility.getLastFile(LogsUtilis.LOGS_PATH);
       if (testResult.getStatus() == ITestResult.FAILURE) {
           LogsUtilis.info("TestCase" + testResult.getName() + "failed");
           Utilities.Utility.takeScreenShoot(getDriver(), testResult.getName()); /// valid tes case
           Utility.takeFullScreenShoot(getDriver(),new P02_ProductPage(getDriver()).getNumberOfProductsOnCart());
       }
   }
}
