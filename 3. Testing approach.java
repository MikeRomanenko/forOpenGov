/*I'd start with Functional testing by cheking all internal links in web pages, any forms for submitting or getting the information from user. First I do it manually.

Find out number of pages. Locate all links on each page. For each page I create a new class which going to contain all links from that page. Make assertinons for each link that it is there and you can open it. Then I do the rest of the pages the same way. After that I do main test which is going to go thorugh the all pages with links.*/

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageTest
{
	private WebDriver d;
	private String url = "http://opengov.com";
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Drivers/chromedriver.exe"); //pls provide Your path to chromedriver.exe
		d = new ChromeDriver();
		d.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		d.manage().window().maximize();
		
	}

	@Test
	public void test() throws FileNotFoundException
	{
		d.get(url);
		d.findElement(By.linkText("Careers")).click();
		d.findElement(By.linkText("View Open Positions")).click();
		Select dept = new Select(d.findElement(By.cssSelector("select[name='department']")));
		dept.selectByVisibleText("Engineering");
		Select city = new Select(d.findElement(By.cssSelector("select[name='location']")));
		city.selectByVisibleText("Redwood City");
		
		List<WebElement> list = d.findElements(By.cssSelector("span[class='boxTriggerList__hdg']"));
		
		assert(list.size() != 0) : "No positions found!";
			System.out.println("Number of positions available " + list.size());
			
		for (int i = 0 ; i < list.size(); i++) 
			{  
			System.out.println(list.get(i).getText()); 
			
			}
	}
	
	@After
	public void basetearDown() throws Exception
	{
		d.quit();
	}
}
