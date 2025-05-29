import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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

		// Choosing Random Destination

		WebElement DestinationField = driver.findElement(By.className("b915b8dc0b"));
		DestinationField.sendKeys(RandomEnglishCity);
		Thread.sleep(1000);
		WebElement TheListOfDestination = driver.findElement(By.className("e03644d405"));
		List<WebElement> Destinations = TheListOfDestination.findElements(By.tagName("li"));

		// Choosing The First Option In the List
		Destinations.get(0).click();

		// Choosing Today's Date for Check_In And Tomorrow For Check_Out
		WebElement DateField = driver.findElement(By.xpath("//button[@data-testid='searchbox-dates-container']"));
		DateField.click();
		DateField.click();

		WebElement TodaysDateButton = driver.findElement(By.xpath("//span[@data-date='" + todayDate + "']"));
		TodaysDateButton.click();
		WebElement TomorrowsDateButton = driver.findElement(By.xpath("//span[@data-date='" + TommorowDate + "']"));
		TomorrowsDateButton.click();

		// Choosing numbers Of Visitors and Numbers Of Rooms
		WebElement VisitorsFielde = driver.findElement(By.className("ab2c86b370"));
		VisitorsFielde.click();
		List<WebElement> ListOfIncrementButtons = driver.findElements(
				By.cssSelector(".de576f5064.b46cd7aad7.e26a59bb37.c295306d66.c7a901b0e7.aaf9b6e287.dc8366caa6"));
		// Increase Number Of Adults to 3
		ListOfIncrementButtons.get(0).click();
		// Increase Number Of Children to 1
		ListOfIncrementButtons.get(1).click();
		// Increase Number Of Rooms to 2
		ListOfIncrementButtons.get(2).click();

		// Randomly Select The Child Age
		WebElement AgeOfTheChildSelectField = driver.findElement(By.className("ed4d3c8194"));
		Select Myselect = new Select(AgeOfTheChildSelectField);
		int NumberOfAgeOptions = (Myselect.getOptions().size()) - 1;
		int RandomAgeIndex = 1 + rand.nextInt(NumberOfAgeOptions);
		Myselect.selectByIndex(RandomAgeIndex);

		// Click On The Search Button
		WebElement SearchButton = driver.findElement(By.cssSelector(
				".de576f5064.b46cd7aad7.ced67027e5.dda427e6b5.e4f9ca4b0c.ca8e0b9533.cfd71fb584.a9d40b8d51"));
		SearchButton.click();

		// Checking If The Result is correct
		List<WebElement> ListOfLocationSearchResult = driver.findElements(By.xpath("//span[@data-testid='address']"));
		String FirstResultLocation = ListOfLocationSearchResult.get(0).getText();
		System.out.println(FirstResultLocation);
		System.out.println(RandomEnglishCity);

		Assert.assertTrue(FirstResultLocation.toLowerCase().contains(RandomEnglishCity.toLowerCase()));

		// 1.3 Search with Invalid Dates(Manual)

		// Closing The Pop Up
		WebElement CloseButton = driver.findElement(By.xpath("//button[@aria-label='Dismiss sign-in info.']"));
		CloseButton.click();

		// 1.4 Navigate to Accommodation Details

		// Click on The First accommodation in results

		List<WebElement> ListOfSearchResult = driver.findElements(By.cssSelector(".b87c397a13.a3e0b4ffd1"));
		ListOfSearchResult.get(0).click();

	}

}
