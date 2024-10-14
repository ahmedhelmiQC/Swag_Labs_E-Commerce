package Listeners;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestListenerCass implements ITestListener {
    public void onTestStart(ITestResult result) {
        LogsUtils.info("Test case" + result.getName() + "Started");
    }

    public void onTestSuccess(ITestResult result) {
      LogsUtils.info("Test Case " + result.getName() + "passed");
    }

      public void onTestSkipped(ITestResult result) {
      LogsUtils.info("Test Case " + result.getName() + "skipped");
    }


}


