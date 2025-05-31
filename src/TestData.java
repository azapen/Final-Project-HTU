import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestData {

	String URL = "https://www.booking.com/";

	WebDriver driver = new ChromeDriver();

	JavascriptExecutor JS = (JavascriptExecutor) driver;
	Random rand = new Random();
	Actions move ;    
	static int i=1;

	String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	String TommorowDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	String[] EnglishCities = { "jeddah", "riyadh", "dubai", "Amman" };
	String[] ArabicCities = { "دبي", "جدة", "عمان" };
	int RandomEnglishCityIndex = rand.nextInt(EnglishCities.length);
	int RandomArabicCityIndex = rand.nextInt(ArabicCities.length);
	String RandomEnglishCity = EnglishCities[RandomEnglishCityIndex];
	
	String[] firstNames = { "Ali", "Omar", "Salma", "Laila", "Khalid", "Nour", "Mona", "Yousef", "Tariq", "Huda" };
    String[] lastNames = { "Ahmad", "Hussein", "Saleh", "Jaber", "Nasser", "Zein", "Farah", "Khatib", "Darwish", "Najjar" };
    String[] phoneNumbers = { "791234567", "781112233", "771234567", "782345678", "793456789", "771000111", "789876543", "777654321", "790123456", "781998877" };
    String CardNumber="4242424242424242";
    String ExpDate="12/28";
    String cnn="123";
    int FirstNameNumber= rand.nextInt(firstNames.length);
    int LastNameNumber= rand.nextInt(lastNames.length);
    int Numbers= rand.nextInt(phoneNumbers.length);

	

	public void EnterTheWebsite() {
		
		// Initialize Actions here after driver is ready
        move = new Actions(driver);
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

}

