package Listeners;

import Utilities.LogsUtilis;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestResultListenerClass implements ITestListener {
    public void onTestStart(ITestResult result) {
        LogsUtilis.info("Test case" + result.getName() + "Started");
    }

    public void onTestSuccess(ITestResult result) {
        LogsUtilis.info("Test Case " + result.getName() + "passed");
    }

      public void onTestSkipped(ITestResult result) {
          LogsUtilis.info("Test Case " + result.getName() + "skipped");
    }


}


