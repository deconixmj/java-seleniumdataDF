package com.w2a.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.w2a.listeners.CustomListeners;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;
import org.apache.log4j.Logger;

public class TestBase {

    /* initialization of-->
    webdriver
    properties
    logs
    extent reports
    DB
    excel
    Mail

     */


    public static WebDriver driver;
    public static Properties config=new Properties();
    public static Properties OR=new Properties();
    public static FileInputStream fis;
    public static FileInputStream fis1;

    public static Logger log= Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");

    public static WebDriverWait wait;

    public static String browser;

    //    public ExtentReports rep=ExtentManager.createInstance()
//    public static ExtentTest test;


    @BeforeSuite
    public void setUp(){

        if (driver==null){
            try {
                fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");

            } catch (FileNotFoundException e){
                e.printStackTrace();
            }


            try {
                config.load(fis);
                log.debug("config file loaded");
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileInputStream fis1= null;
            try {
                fis1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                OR.load(fis1);
                log.debug("Locator object loaded");
            } catch (IOException e) {
                e.printStackTrace();
            }


            if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){

                browser = System.getenv("browser");
            }else{

                browser = config.getProperty("browser");

            }

            config.setProperty("browser", browser);

            if(config.getProperty("browser").equals("firefox")){
                //Incase you are using latest versiom of geck driver then you need to use below code,
                System.getProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
                driver = new FirefoxDriver();
                log.debug("FF launched");

            } else if(config.getProperty("browser").equals("chrome")){
                System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
                driver =new ChromeDriver();
                log.debug("chrome launched");
            } else if (config.getProperty("browser").equals("edge")){
                System.getProperty("webdriver.edge.driver",System.getProperty("user.dir")+"E:\\Java_projects\\DataDF\\src\\test\\resources\\executables\\msedgedriver.exe");
                driver =new EdgeDriver();
            }

            driver.get(config.getProperty("testsiteurl"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
            wait=new WebDriverWait(driver,5);


        }

    }


    // This is common click method for multiple locators from multiple pages in Page factory.
//    public void click(String locator){
//        driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
//        test.log(Status.INFO,"Clicking on "+locator);
//
//    }
//
//    public void type(String locator,String value){
//        driver.findElement(By.cssSelector(OR.getProperty()))
//    }


    public void click(String locator) {

        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).click();
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(OR.getProperty(locator))).click();
        }
        CustomListeners.testReport.get().log(Status.INFO, "Clicking on : " + locator);
    }

    public void type(String locator, String value) {

        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
        }

        CustomListeners.testReport.get().log(Status.INFO, "Typing in : " + locator + " entered value as " + value);

    }

    static WebElement dropdown;

    public void select(String locator, String value) {

        if (locator.endsWith("_CSS")) {
            dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
        } else if (locator.endsWith("_XPATH")) {
            dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
        } else if (locator.endsWith("_ID")) {
            dropdown = driver.findElement(By.id(OR.getProperty(locator)));
        }

        Select select = new Select(dropdown);
        select.selectByVisibleText(value);

        CustomListeners.testReport.get().log(Status.INFO, "Selecting from dropdown : " + locator + " value as " + value);

    }

    public static void verifyEquals(String expected, String actual) throws IOException {

        try {

            Assert.assertEquals(actual, expected);

        } catch (Throwable t) {

            TestUtil.captureScreenshot();
            // ReportNG
            Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
            Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
                    + " height=200 width=200></img></a>");
            Reporter.log("<br>");
            Reporter.log("<br>");
            // Extent Reports
            CustomListeners.testReport.get().log(Status.FAIL, " Verification failed with exception : " + t.getMessage());
//            CustomListeners.testReport.get().log(Status.FAIL, (Markup) CustomListeners.testReport.get().addScreenCaptureFromPath(TestUtil.screenshotName));

        }

    }

    public boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }catch(NoSuchElementException e){
            return false;

        }
    }

    @AfterSuite
    public void tearDown(){
        if(driver!=null) {
            driver.quit();
        }
        log.debug("test completed");
    }



}
