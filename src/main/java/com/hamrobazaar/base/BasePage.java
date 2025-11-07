package com.hamrobazaar.base;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hamrobazaar.utils.DriverManager;
import com.hamrobazaar.utils.WaitUtils;

public abstract class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
    protected Logger logger = LogManager.getLogger(DriverManager.class);
    
    public BasePage(WebDriver driver) {
    	this.driver = driver;
    	this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	PageFactory.initElements(driver, this);
    }
    
    protected WebElement waitForElementVisible(By locator) {
    	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    protected WebElement waitForElementClickable(By locator) {
    	return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    protected List<WebElement> waitForElementsVisible(By locator){
    	return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    
    protected void click(WebElement element) {
    	WaitUtils.waitForElementToBeClickable(driver, element);
    	element.click();
    }
    
    protected void sendKeys(WebElement element, String text) {
    	WaitUtils.waitForElementToBeVisible(driver, element);
    	element.clear();
    	element.sendKeys(text);
    }
    
    protected void sendKeys(By locator, String text) {
    	sendKeys(waitForElementVisible(locator), text);
    }
    
    protected String getText(WebElement element) {
    	WaitUtils.waitForElementToBeVisible(driver, element);
    	return element.getText();
    }
    
    protected String getText(By locator) {
    	return getText(waitForElementVisible(locator));
    }
    
    protected boolean isElementDisplayed(By locator, int timeoutInSeconds) {
    	try {
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    		return true;
    	}
    	catch(Exception e) {
    		return false;
    	}
    }
    
    protected boolean isElementDisplayed(WebElement element, int timeoutInSeconds) {
    	try {
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    		wait.until(ExpectedConditions.visibilityOf(element));
    		return true;
    	}
    	catch(Exception e) {
    		return false;
    	}
    }

}
