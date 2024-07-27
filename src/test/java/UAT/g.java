package UAT;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class g {
	WebDriver driver;
	
	@Test
	public void test() throws InterruptedException, AWTException
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://aaeb-school.sims.uat.ethiocheno.com/");
		driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("email")).sendKeys("admin@tria.plc");
		driver.findElement(By.id("SubmitBtn")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("password")).sendKeys("school@dmin");	
		driver.findElement(By.id("continueBtn")).click();
		Thread.sleep(2000);
		WebElement school=driver.findElement(By.xpath("//span[normalize-space()='Schools']"));
		JavascriptExecutor ex= (JavascriptExecutor) driver;
		ex.executeScript("arguments[0].click();", school);
		WebElement addschool=driver.findElement(By.xpath("//span[normalize-space()='Add New School']"));
		ex.executeScript("arguments[0].click();", addschool);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement schoolleveldropdown=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='select2-selection__rendered']")));
        schoolleveldropdown.click();
        Thread.sleep(2000);
        Robot r= new Robot();
        for(int i=0;i<15;i++)
        {
        	r.keyPress(KeyEvent.VK_BACK_SPACE);
        	r.keyRelease(KeyEvent.VK_BACK_SPACE);
        }
		
	}

}
