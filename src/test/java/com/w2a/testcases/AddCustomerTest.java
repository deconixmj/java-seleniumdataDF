package com.w2a.testcases;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.Hashtable;

public class AddCustomerTest extends TestBase {

//    @Test(dataProvider = "getData")
    @Test(dataProviderClass = TestUtil.class,dataProvider = "dp")
    public void addCustomerTest(Hashtable<String,String> data) throws InterruptedException {

//        driver.findElement(By.cssSelector(OR.getProperty("addCustBtn_CSS"))).click();

        if(!data.get("runmode").equals("Y")){

            throw new SkipException("Skipping the test case as the Run mode for data set is NO");
        }
//        driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstname);
//        driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastname);
//        driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(postcode);
//        driver.findElement(By.cssSelector(OR.getProperty("addbtn_CSS"))).click();

        click("addCustBtn_CSS");
        type("firstname_CSS",data.get("firstname"));
        type("lastname_CSS",data.get("lastname"));
        type("postcode_CSS",data.get("postcode"));
        click("addbtn_CSS");

        Alert alert=wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
        alert.accept();
        Thread.sleep(3000);

    }


// Created common data provider method in TestUtil class.
//    @DataProvider
//    public Object[][] getData(){
//        String sheetname="AddCustomerTest";
//        int rows=excel.getRowCount(sheetname);
//        int cols=excel.getColumnCount(sheetname);
//
//        Object[][] data=new Object[rows-1][cols];
//
//        for (int rowNum=2;rowNum<=rows;rowNum++){
//            for(int colNum=0;colNum< cols;colNum++){
//                data[rowNum-2][colNum]=excel.getCellData(sheetname,colNum,rowNum);
//            }
//        }
//        return data;
//
//    }

}
