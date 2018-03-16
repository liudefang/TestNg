package com.jfz.erp.online.purchase.thrifttestng;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PlayRecordTest {
	
	
     
	  static WebDriver driver;
			static String baseUrl;
			@Test(priority =5)
			public void setUp() throws Exception {
				baseUrl = "http://10.1.2.211:8080/login.jsp";
				//设定连接chrome浏览器驱动程序所在的磁盘位置，并添加为系统属性值
				System.setProperty("webdriver.chrome.driver", "E:\\功能测试架构相关\\Selenium最新版全家桶\\chromedriver.exe");
				driver = new ChromeDriver();
				
			}
			@Test(priority =6)
			public  void visitLogin()throws Exception {
				driver.get(baseUrl + "");
				
				driver.manage().window().maximize();
				
		       //文本框内输入用户名
		       driver.findElement(By.name("username")).sendKeys("defang2");
		       //文本框内输入密码
		       driver.findElement(By.name("password")).sendKeys("123");
		       //点击登录
		       driver.findElement(By.className("submit_wrap")).click();
		       
		       //审核合同通过
		       Thread.sleep(2000);
		       driver.switchTo().frame("home");  
		       System.out.println("进入1111");
		       driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/div/div[2]/div/div[1]/ul/li[1]/div[2]/p/span[@class='label label-success arrow-in-right']")).click();
               
		       //先将当前浏览器窗口的句柄存储到parentWindowHandle变量中
		      // String parentWindowHandle = driver.getWindowHandle();
		       //System.out.println("parentWindowHandle:"+parentWindowHandle);
		       //找到页面上唯一的链接元素，存储到
		       
		     //得到当前窗口的句柄  
		        String currentWindow = driver.getWindowHandle();  
		        //得到所有窗口的句柄  
		        Set<String> handles = driver.getWindowHandles();  
		        Iterator<String> it = handles.iterator();  
		        while(it.hasNext()){  
		            String handle = it.next();  
		            if(currentWindow.equals(handle)) continue;  
		            WebDriver window = driver.switchTo().window(handle);  
		            System.out.println("title,url = "+window.getTitle()+","+window.getCurrentUrl());  
		        }  
		       

//		       switchtoWindows.execute("platform");
		       System.out.println("进入6111");
		       
		       System.out.println(driver.getCurrentUrl());
		       
//		       String  a ="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:70px; z-index:-1; border-width:0px;";
// 		       WebElement b=driver.findElement(By.xpath("//iframe[@styple='position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:70px; z-index:-1; border-width:0px;']"));
// 		       driver.switchTo().frame(b);
////		       driver.switchTo().frame("position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:70px; z-index:-1; border-width:0px;"); 
//		       System.out.println("进入4111");
		       driver.findElement(By.xpath(".//*[@id='btnAgree']")).click();  //.//*[@id='btnAgree']
		       
		       System.out.println("进入6222");
		     //得到当前窗口的句柄  
		        String currentWindow1 = driver.getWindowHandle();  
		      //得到所有窗口的句柄  
		        Set<String> handles1 = driver.getWindowHandles();  
		        Iterator<String> it1 = handles1.iterator();  
		        while(it1.hasNext()){  
		            String handle1 = it1.next();  
		            if(currentWindow1.equals(handle1)) continue;  
		            WebDriver window1 = driver.switchTo().window(handle1);  
		            System.out.println("title1,url1 = "+window1.getTitle()+","+window1.getCurrentUrl());  
		        }  
		        System.out.println("进入6333"+currentWindow1);
		      // driver.switchTo().frame("position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:70px; z-index:-1; border-width:0px;"); 
		       driver.switchTo().frame("ligerwindow1499497099672");
		      
		       driver.findElement(By.id(".//*[@id='dataFormSave'")).click(); 
		      // driver.findElement(By.xpath(".//*[@id='dataFormSave']")).click();
		       // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
		       driver.findElement(By.cssSelector("div.l-dialog-btn-inner")).click();
		       
			}
			
			
			
		
			
      
		     

}
