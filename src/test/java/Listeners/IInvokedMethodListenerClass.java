package Listeners;

import Utilities.LogsUtils;
import Utilities.Utility;
import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;


import static DriverFactory.DriverFactory.getDriver;


public class IInvokedMethodListenerClass implements IInvokedMethodListener {

    public void beforeInvocation(
            IInvokedMethod method, ITestResult testResult, ITestContext context) {

        // not implemented
    }


   public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
      if (testResult.getStatus()==ITestResult.FAILURE)
          LogsUtils.info("Test Case " + testResult.getName() + "failed");
      Utilities.Utility.takeScreenShoot(getDriver(),testResult.getName());
    }

    }
