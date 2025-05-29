import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases extends TestData {

	// 1. Search & Navigation
	@BeforeTest
	public void SetUp() {
		// 1.1 Navigate to Home page
		EnterTheWebsite();
	}

	// 1.2 Search for Accommodations
	@Test(priority = 1)
	public void Search_for_Accommodations() throws InterruptedException {

		WebElement DestinationField = driver.findElement(By.className("b915b8dc0b"));
		DestinationField.sendKeys(EnglishCities[RandomEnglishCityIndex]);
		Thread.sleep(1000);
		WebElement TheListOfDestination = driver.findElement(By.className("e03644d405"));
		List<WebElement> Destinations = TheListOfDestination.findElements(By.tagName("li"));
		// Choosing The First Option
		Destinations.get(0).click();

		WebElement DateField = driver.findElement(By.xpath("//button[@data-testid='searchbox-dates-container']"));
		DateField.click();
		DateField.click();
		// Choosing Today's Date for Check_In And Tomorrow For Check_Out

		WebElement TodaysDateButton = driver.findElement(By.xpath("//span[@data-date='" + todayDate + "']"));
		TodaysDateButton.click();
		WebElement TomorrowsDateButton = driver.findElement(By.xpath("//span[@data-date='" + TommorowDate + "']"));
		TomorrowsDateButton.click();
		

	}

}
