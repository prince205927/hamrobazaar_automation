package com.hamrobazaar.utils;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
		private static final int DEFAULT_TIMEOUT_SECONDS =30 ;
	    private static final Logger logger = LogManager.getLogger(DriverManager.class);
	    
	    public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
	    	waitForElementToBeVisible(driver,element,DEFAULT_TIMEOUT_SECONDS);
	    }
	    
	    public static void waitForElementToBeVisible(WebDriver driver, WebElement element, int timeoutInSeconds) {
	    	try {
	    		new WebDriverWait(driver,Duration.ofSeconds(timeoutInSeconds)).until(ExpectedConditions.visibilityOf(element));
	    	}
	    	catch(Exception e)
	    	{
	    		logger.error("Element not visible after"+ timeoutInSeconds + "seconds : "+ e.getMessage());
	    	}
	    }
	    
	    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
	    	waitForElementToBeClickable(driver,element,DEFAULT_TIMEOUT_SECONDS);
	    }
	    
	    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
	    	try {
	    		new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(ExpectedConditions.elementToBeClickable(element));
	    	}
	    	catch(Exception e) {
	    		logger.error("Element not clickable after" + timeoutInSeconds + "seconds:" + e.getMessage());
	    	}
	    }
	    
	    public static void waitForPageToLoad(WebDriver driver) {
	    	waitForPageToLoad(driver, DEFAULT_TIMEOUT_SECONDS);
	    }
	    
	    public static void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
	    	try {
	    		new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
	    	    .until(webDriver -> ((JavascriptExecutor) webDriver)
	    	    .executeScript("return document.readyState").equals("complete"));	    	}
	    	catch(Exception e) {
	    		logger.error("Page didn't load completely after" + timeoutInSeconds + "seconds:" +e.getMessage());
	    	}
	    }
}
