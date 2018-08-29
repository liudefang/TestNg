package com.test.erp.online.purchase.thrifttestng;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class switchtoWindows {
	
	
     
	  static WebDriver driver;
		public static boolean execute(String url){
			boolean flag = false;
			try {
				String currentHandle = driver.getWindowHandle();
				Set<String> handles = 	driver.getWindowHandles();
				for (String s : handles) {
					if (s.equals(currentHandle))
						continue;
					else {
						driver.switchTo().window(s);
						if (driver.getCurrentUrl().contains(url)) {
							flag = true;
							System.out.println("Switch to window: " + url + " successfully!");
							break;
						} else
							continue;
					}
				}
			} catch (NoSuchWindowException e) {
				System.out.printf("Window: " + url + " cound not found!", e.fillInStackTrace());
				flag = false;
			}
			return flag;
		}
}