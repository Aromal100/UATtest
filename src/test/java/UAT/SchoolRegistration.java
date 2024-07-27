package UAT;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class SchoolRegistration {
	
	WebDriver driver;
	String CSV_file="C:\\Users\\RESBEE-218\\Downloads\\onboard.csv";
	
	
	@BeforeTest
	public void urlsetup()  {
		driver = new ChromeDriver();
		driver.get("https://aaeb-school.sims.uat.ethiocheno.com/");
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void createschoolform() throws InterruptedException {
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
	   	
	}
	
	@Test
	public void test() throws CsvValidationException, IOException, InterruptedException, AWTException, TimeoutException
	{
		CSVReader reader= new CSVReader(new FileReader(CSV_file));
		String[] cell;
		reader.readNext();
		
		 
		
		if ((cell=reader.readNext())!=null)
		{
			    WebElement schoolNameField = driver.findElement(By.id("organization_name"));
		        schoolNameField.sendKeys(cell[0]); // School Name

		        WebElement usernameField = driver.findElement(By.id("domain_name"));
		        usernameField.sendKeys(cell[1]); // Username

		        WebElement codeField = driver.findElement(By.id("organization_code"));
		        codeField.sendKeys(cell[2]); // Code

		        WebElement emailField = driver.findElement(By.id("organization_email"));
		        emailField.sendKeys(cell[3]); // Email

		        WebElement contactNumberField = driver.findElement(By.id("phone_number"));
		        contactNumberField.sendKeys(cell[5]); // Contact Number

		        WebElement assignmanager = driver.findElement(By.id("assign_organization_manager"));
		        assignmanager.sendKeys(cell[4]); // License Number
		        
		        String yearofestablishment = cell[8]; 
		        System.out.println("year from csv :"+ yearofestablishment);
		        
		        String[] schoolALevel=cell[9].split(",");			        
		        String schooltype=cell[10];  
		        String ownership=cell[11];
		        String schoolcategory=cell[12];			    
		        String subcity=cell[14];
		        String woreda=cell[15];
		        String language=cell[27];
		        String program=cell[34];
		        String schoolL=cell[40];
		        String digitalL=cell[42];
		        String virtualL=cell[43];
		        String trainingC=cell[44];
		        String IctC=cell[45];
		        String digitalF=cell[47];
		        
		        driver.findElement(By.id("year_of_establishment_")).click();
		        driver.findElement(By.xpath("//span[@id='activeyear']")).click();
		        findAndClickYear(driver, yearofestablishment);
		        //School level dropdown
		        
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		        WebElement schoolleveldropdown=driver.findElement(By.xpath("//span[@role='combobox']"));
		        schoolleveldropdown.click();
		        Thread.sleep(2000);
		        Robot r= new Robot();
		        for(int i=0;i<15;i++)
		        {
		        	Thread.sleep(2000);
		        	r.keyPress(KeyEvent.VK_BACK_SPACE);
		        	r.keyRelease(KeyEvent.VK_BACK_SPACE);
		        }
                WebElement dropdown=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"school_academic_level_\"]")));
                Select s= new Select(dropdown);
                
				for (String level : schoolALevel) {
                    s.selectByVisibleText(level.trim());
                    System.out.println("Selected the school level: " + level.trim());
                }
		        driver.findElement(By.xpath("//*[@id=\"page_1\"]/div")).click();
			    
		        //dropdowns   
	            WebElement dropdown1=driver.findElement(By.xpath("//select[@id='school_type_']"));
		        Select s1= new Select(dropdown1);
		        s1.selectByValue(schooltype);
		        System.out.println("Selected the school type :" + schooltype);
		        
		        WebElement dropdown2=driver.findElement(By.xpath("//select[@id='ownership']"));
		        Select s2= new Select(dropdown2);
		        s2.selectByValue(ownership);
		        System.out.println("Selected the ownership  :" + ownership);
		        
		        WebElement dropdown3=driver.findElement(By.xpath("//select[@id='school_category_']"));
		        Select s3= new Select(dropdown3);
		        s3.selectByValue(schoolcategory);
		        System.out.println("Selected the schoolcategory  :" + schoolcategory);
		        
		        Thread.sleep(2000);
		       driver.findElement(By.id("saveNextBtn")).click();    
		      
		       
		       //page 2
				WebElement dropdown4= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sub_city_\"]")));	
				Select s4=new Select(dropdown4);
				s4.selectByVisibleText(subcity);
				System.out.println("Selected the subcity  :" + subcity);
		        
				WebElement dropdown5= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"woreda\"]")));	
				Select s5=new Select(dropdown5);
				s5.selectByVisibleText(woreda);
				System.out.println("Selected the woreda  :" + woreda);
				
				WebElement choose = driver.findElement(By.id("academic_language_NaN"));
				Select s6=new Select(choose);
				s6.selectByVisibleText(language);
				System.out.println("Selected the language  :" + language);
				
				driver.findElement(By.xpath("//div[@id='fieldCont_4']//div[1]//label[1]//input[1]")).click();
				
				WebElement dropdown7=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"education_delivery_program\"]")));
				Select s7= new Select(dropdown7);
				s7.selectByVisibleText(program);
				System.out.println("Selected the program  :" + program);
				
				driver.findElement(By.xpath("//button[@id='saveNextBtn']")).click();
     
				//School facility Availabilty
				WebElement schoollibrary=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"school_library\"]")));
				Select s8=new Select(schoollibrary);
				s8.selectByVisibleText(schoolL);
				System.out.println("Selected the schoollibrary  :" + schoolL);

				
				Thread.sleep(2000);
				WebElement digitallibrary=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"digital_library\"]")));
				Select s9=new Select(digitallibrary);
				s9.selectByVisibleText(digitalL);
				System.out.println("Selected the digitallibrary  :" + digitalL);
				
				Thread.sleep(2000);
				WebElement virtallibrary=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"virtual_library\"]")));
				Select s10=new Select(virtallibrary);
				s10.selectByVisibleText(virtualL);
				System.out.println("Selected the virtallibrary  :" + virtualL);
				
				Thread.sleep(2000);
				WebElement trainingcenter=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"training_center\"]")));
				Select s11=new Select(trainingcenter);
				s11.selectByVisibleText(trainingC);
				System.out.println("Selected the trainingcenter  :" + trainingC);
				
				Thread.sleep(2000);
				WebElement ictcenter=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ict_center\"]")));
				Select s12=new Select(ictcenter);
				s12.selectByVisibleText(IctC);
				System.out.println("Selected the ictcenter  :" + IctC);
				
				Thread.sleep(2000);
				WebElement digitalfacility=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"digital_information_collection_facility_\"]")));
				Select s13=new Select(digitalfacility);
				s13.selectByVisibleText(digitalF);
				System.out.println("Selected the digitalfacility  :" + digitalF);
				
				
				Thread.sleep(2000);
				//driver.findElement(By.xpath("//span[normalize-space()='Save']")).click();
     	}
	}
	//Datepicker
	public void findAndClickYear(WebDriver driver2, String targetyear) throws InterruptedException {
		
		String sidebutton="//button[@id='btnmonthprev']//*[name()='svg']";
		String yearbutton="//button[normalize-space()=" +targetyear+ "]";
		
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(10));
		while(true)
		{
			try {
			WebElement yearsbutton=w.until(ExpectedConditions.elementToBeClickable(By.xpath(yearbutton)));
			yearsbutton.click();
			System.out.println("clicked on the year:" + targetyear);
			  break;
				
			}
			catch(Exception e) {
				WebElement yearslider=driver.findElement(By.xpath(sidebutton));
				yearslider.click();
				System.out.println("Clicked side button to navigate to the year");
			}
		}
		
		boolean gregorianCalendarSuccess = false;
		try {
		WebElement Janbutton=w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Jan']")));
		Janbutton.click();
		driver.findElement(By.xpath("//button[@id='06']")).click();
     	gregorianCalendarSuccess=true;
		}
		catch(Exception e)
		{
			System.out.println("The gregorian calnder failed to click trying the ethopian calender to click");	 
		}                                                    
		 if (!gregorianCalendarSuccess) {
		while(true)
		{
			try {
				driver.findElement(By.xpath("//span[@id='activeyear']")).click();
				   WebElement yearsbutton=w.until(ExpectedConditions.elementToBeClickable(By.xpath(yearbutton)));
				   yearsbutton.click();
				   System.out.println("clicked on the year:" + targetyear);
				    break;
			}
			catch(Exception e) {
				WebElement yearslider=driver.findElement(By.xpath(sidebutton));
				yearslider.click();
				 System.out.println("Clicked side button to navigate to the year"); 			    
			}
		}
		
			boolean ethiopianCalendarSuccess = false;
			try {
				WebElement eButton = w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'መስከረም')]")));
		        eButton.click();
		        driver.findElement(By.xpath("//button[@id='02']")).click();
		        System.out.println("Ethiopian calender year has been selected");
		        ethiopianCalendarSuccess=true;
			}catch(Exception e)
			{
				System.out.println("Failed to click the year");
			}
				
		}
		}
}			                                                        