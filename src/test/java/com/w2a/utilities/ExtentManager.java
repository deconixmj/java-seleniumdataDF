package com.w2a.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.*;
import java.io.File;

//public class ExtentManager {
//
//    public static ExtentReports extent;
//
//    public static ExtentReports getInstance(){
//        if(extent==null){
//            extent=new ExtentReports(System.getProperty("user.dir")+ "\\target\\surefire-reports\\html\\extent.html",true);
//
//            extent.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\extentconfig\\ReportsConfig.xml\\"));
//        }
//
//        return extent;
//    }
//}

public class ExtentManager{
    private static ExtentReports extent;

    public static ExtentReports createInstance(String filename){
        ExtentHtmlReporter htmlreporter=new ExtentHtmlReporter(filename);
        htmlreporter.config().setTheme(Theme.STANDARD);
        htmlreporter.config().setDocumentTitle(filename);
        htmlreporter.config().setEncoding("utf-8");
        htmlreporter.config().setReportName(filename);

        extent = new ExtentReports();
        extent.attachReporter(htmlreporter);
        extent.setSystemInfo("Automation Tester", "Rahul Arora");
        extent.setSystemInfo("Organization", "Way2Automation");
        extent.setSystemInfo("Build no", "W2A-1234");
        return extent;

    }
}