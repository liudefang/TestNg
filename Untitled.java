package com.jfz.erp.online.purchase.thrifttestng;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Untitled {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://10.1.2.58:8080/login.jsp";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitled() throws Exception {
    driver.get(baseUrl + "/login.jsp");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("123");
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("defang2");
    driver.findElement(By.xpath("//button[@onclick=\"document.getElementById('form-login').submit();\"]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | home | ]]
    driver.findElement(By.cssSelector("strong")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp |  | 30000]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    driver.findElement(By.id("btnAgree")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | ligerwindow1499490217589 | ]]
    driver.findElement(By.id("dataFormSave")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    driver.findElement(By.cssSelector("div.l-dialog-btn-inner")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
