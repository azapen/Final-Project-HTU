import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	}

	@Test(priority = 2)
	public void CheckInDate_CheckOutDate() {

		// Choosing Today's Date for Check_In And Tomorrow For Check_Out
		WebElement DateField = driver.findElement(By.xpath("//button[@data-testid='searchbox-dates-container']"));
		DateField.click();
		DateField.click();

		WebElement TodaysDateButton = driver.findElement(By.xpath("//span[@data-date='" + todayDate + "']"));
		TodaysDateButton.click();
		WebElement TomorrowsDateButton = driver.findElement(By.xpath("//span[@data-date='" + TommorowDate + "']"));
		TomorrowsDateButton.click();
	}

	@Test(priority = 3)
	public void Visitors_Rooms_Children() {
		// Choosing numbers Of Visitors,Rooms And Children
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
	}

	@Test(priority = 4)
	public void NavigateToAccommodationDetails() throws InterruptedException {
		// Click on The First accommodation in results

		List<WebElement> ListOfSearchResult = driver.findElements(By.cssSelector(".b87c397a13.a3e0b4ffd1"));
		ListOfSearchResult.get(0).click();

		// Switching between Tabs
		Set<String> handels = driver.getWindowHandles();
		List<String> windowList = new ArrayList<>(handels);
		driver.switchTo().window(windowList.get(1));
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
		driver.switchTo().window(windowList.get(0));
		System.out.println(driver.getTitle());

	}

	@Test(priority = 5)
	public void setRandomPriceFilter() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

		List<WebElement> SLiders = driver.findElements(By.className("fc835e65e6"));
		WebElement sliderMin = SLiders.get(0);
		WebElement sliderMax = SLiders.get(1);

		// Safer random drag range since you said max is about 200
		int moveMinBy = 10 + rand.nextInt(40); 
		int moveMaxBy = -(10 + rand.nextInt(40)); 
		move.clickAndHold(sliderMin).moveByOffset(moveMinBy, 0).release().perform();
		Thread.sleep(500);
		move.clickAndHold(sliderMax).moveByOffset(moveMaxBy, 0).release().perform();
		Thread.sleep(500);

	}

	@Test(priority = 6)
	public void sortByRating() throws InterruptedException {
		WebElement sortDropdown = driver.findElement(By.className("cd46a6a263"));
		sortDropdown.click();
		Thread.sleep(1000); // Wait for options to load
		// 2. Click the "Top Reviewed" option (bayesian_review_score)
		WebElement topReviewed = driver.findElement(By.xpath("//button[@data-id='bayesian_review_score']"));
		topReviewed.click();

	}
}
