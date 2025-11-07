package com.hamrobazaar.utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

    private static final Logger logger = LogManager.getLogger(DriverManager.class);

	
	public static WebDriver getDriver(String browser, boolean headless) {
		WebDriver driver;
		
		switch(browser.toLowerCase()) {
		case "chrome" : 
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				if(headless) {
					chromeOptions.addArguments("--headless");
				}
				chromeOptions.addArguments("--start-maximized");
				chromeOptions.addArguments("--disable-notifications");
				driver = new ChromeDriver(chromeOptions);
				break;
		
		case "firefox" :
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if(headless) {
				firefoxOptions.addArguments("--headless");
			}
			driver = new FirefoxDriver(firefoxOptions);
			break;
			
		case "edge" : 
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			if(headless) {
				edgeOptions.addArguments("--headless");
			}
			driver = new EdgeDriver(edgeOptions);
			break;
		
		default: 
			logger.info("Browser not supported, defaulting to Chrome");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		return driver;
	}
	

}
