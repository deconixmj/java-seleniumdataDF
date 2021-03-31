package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void LoginAsBankManager() throws InterruptedException {

        driver.findElement(By.cssSelector(OR.getProperty("bmlBtn_CSS"))).click();

        Thread.sleep(3000);
        log.debug("Inside Login test");

        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))),"Login not successfull");
        log.debug("Login test successfully executed");

//        Assert.fail("Login not suucessful");
//        Reporter.log("Login successfully executed");
//      Reporter.log("<a target=\"_blank\" href=\"E:\\Java_projects\\DataDF\\screenshots\\error.jpg\"> Screenshot</a>");
//        Reporter.log("<a target=\"_blank\" href=\"E:\\Java_projects\\DataDF\\screenshots\\error.jpg\"> <img src=\"E:\\Java_projects\\DataDF\\screenshots\\error.jpg\" height=200 width=200</a>");

    }
}
