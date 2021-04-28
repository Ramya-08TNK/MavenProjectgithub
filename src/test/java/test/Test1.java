package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 {
	@Test(priority=2,dependsOnMethods="MagentoRegister",enabled=false)//by adding group=smoke,we can test only test cases belong to smoke testcase catogory
	//*testng is a plugin.use to run many testcases one by one.means constructing the testcases
	//testng is not only for selenium.we can use anywhere with java.its a Maven Project
	 //priority shows in which priority testcase should excicute
	//if we use dependsOnMethods, current testcase(Magentologin) would excicute after excicuting Magentoregister without failure
	//if we use enabled=false, current testcase excicute whether Magentoregister testcase failed or passed
	 
	public void Magentologin(){       //Magentologin is testcase name
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		
		driver.get("http://magento.com");
		
		driver.findElement(By.xpath("//*[@id=\"gnav_509\"]/span/span/span")).click();	
		driver.findElement(By.id("email")).sendKeys("sriramya80d@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("ram08pwd");
		driver.findElement(By.name("send")).click();
		
		
		wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div"),
		"Invalid login or password."));
		
		String error_msg=driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).getText();
		System.out.println(error_msg);
		driver.quit();
	}
	@Test(priority=3)
	public void MagentoRegister()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		
		driver.get("http://magento.com");
		// TODO Auto-generated method stub
		driver.findElement(By.xpath("//*[@id=\"gnav_509\"]/span/span/span")).click();	
		driver.findElement(By.id("register")).click();
		driver.findElement(By.id("firstname")).sendKeys("Ramya");
		driver.findElement(By.id("lastname")).sendKeys("Kalai");
		driver.findElement(By.name("email")).sendKeys("sriramya80d@gmail.com");
		driver.findElement(By.id("self_defined_company")).sendKeys("Nisaan");
		driver.findElement(By.name("password")).sendKeys("Nisaanpwd@1234");//password
		driver.findElement(By.name("password_confirmation")).sendKeys("Nisaanpwd21234");//confirm password
		
		Select cp=new Select(driver.findElement(By.id("company_type")));//to perform select from drop down list box
		//cp.selectByIndex(3);
		//cp.selectByValue(arg0);
		cp.selectByVisibleText("Provides deployment, customization and integration services to merchants");
		
		Select role=new Select(driver.findElement(By.id("individual_role")));
		role.selectByValue("technical/developer");
		
		Select country=new Select(driver.findElement(By.id("country")));
		country.selectByValue("AT");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"recaptcha-f979c2ff515d921c34af9bd2aee8ef076b719d03\"]/div/div/iframe")));//switch to frame in the browser .
		//the frame in the current browser own by some other comepany.if we want to inspect the frame we should switch the 
		//to frame as switching windows.then only we can access the details of frame
		driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]/div[1]")).click();
		driver.switchTo().defaultContent(); //switching back to browser
		
		
		
		
		if(!driver.findElement(By.id("agree_terms[]")).isSelected())
		{
			
		driver.findElement(By.id("agree_terms[]")).click();
		}
		
		

	}
	
	}

		
	


