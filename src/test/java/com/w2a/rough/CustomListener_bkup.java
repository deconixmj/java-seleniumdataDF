package com.w2a.rough;

import com.aventstack.extentreports.Status;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

//import static com.w2a.base.TestBase.test;

public class CustomListener_bkup extends TestBase implements ITestListener {

    public void onFinish(ITestContext iTestContext) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

//        test.log(Status.PASS,iTestResult.getName().toUpperCase()+"PASS");
//        rep.endTest();
//        rep.flush();
//        test.log

    }

    public void onTestFailure(ITestResult iTestResult) {

        System.setProperty("org.uncommons.reportng.escape-output","false");
//        Reporter.log("Capturing screenshot");

        try {
            TestUtil.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Reporter.log("click to see screenshot");
        Reporter.log("<a target=\"_blank\" href=\"E:\\Java_projects\\DataDF\\screenshots\\error.jpg\"> Screenshot</a>");
        // get the screenshotname here from testutil class.
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href=\"E:\\Java_projects\\DataDF\\screenshots\\error.jpg\"> <img src=\"E:\\Java_projects\\DataDF\\screenshots\\error.jpg\" height=200 width=200</a>");

//        test.log(Status.FAIL,iTestResult.getName().toUpperCase()+ "Failed with exception:"+ iTestResult.getThrowable());

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

}
