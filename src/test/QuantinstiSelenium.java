package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class QuantinstiSelenium {

	public static String browser;
	static WebDriver driver; 

	public static void main(String[] args) {

		setBrowser();
		setBrowserConfig();
		runTest();
	}

	public static void setBrowser() {
		browser = "Chrome";
	}

	public static void setBrowserConfig() {
		String ProjectLocation = System.getProperty("user.dir" );

		if (browser.contains("Firefox")) {
			System.setProperty("webdriver.gecko.driver",  ProjectLocation + "/lib/ geckodriver/geckodriver");
			driver = new FirefoxDriver();
		}

		if (browser.contains("Chrome")) {
			System.setProperty("webdriver.chrome.driver", ProjectLocation + "/lib/chromedriver/chromedriver");
			driver = new ChromeDriver();
		}

	}

	public static void runTest() {

		driver.get("https://quantra.quantinsti.com/");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);				

		WebElement loginButton = driver.findElement(By.xpath("//span[contains(text(),'Login')]"));
		loginButton.click();

		WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='Email ID']"));
		emailField.sendKeys("ajay.jaiswal003@gmail.com");

		WebElement pswdField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		pswdField.sendKeys("fortonse");

		WebElement loginBtn = driver.findElement(By.xpath("//button[@class='btn btn-plain-border width-70 margin-bottom-20 btn-new-theme']"));
		loginBtn.click();

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	

		WebElement browserCourse = driver.findElement(By.xpath("//a[contains(text(),'Browse Courses')]"));
		browserCourse.click();

		WebElement courseName = driver.findElement(By.xpath("//span[contains(text(),'Sentiment Analysis in Trading')]"));
		courseName.click();

		WebElement checkCourseName = driver.findElement(By.xpath("//h1[contains(text(),'Learning Track: Sentiment Analysis in Trading')]"));
		checkCourseName.isDisplayed();
		System.out.println("Course name given is : " + checkCourseName.getText());

		WebElement checkCoursePrice = driver.findElement(By.xpath("//span[contains(text(),'22575')]"));
		checkCoursePrice.isDisplayed();
		System.out.println("Course Price given is : " + checkCoursePrice.getText());

		WebElement enrollNow = driver.findElement(By.xpath("//button[@class='vue-ui-button btn button tertiary']//span[@class='default-slot'][contains(text(),'Enroll Now')]"));
		enrollNow.click();

		List<WebElement> coursesName = driver.findElements(By.className("cart-item-title"));
		System.out.println("No of courses present in the cart list : " + coursesName.size());
		for (WebElement webElement : coursesName) {
			String name = webElement.getText();
			System.out.println("Courses name are as follows : "  + name);	}

		WebElement cartCount = driver.findElement(By.className("cart-count"));
		System.out.println("No of items present in the cart :" + cartCount.getText());

		//		System.out.println("Check if cart count and course count matches :" + cartCount.getText().equals(courseName.getSize()));

		WebElement baseAmount = driver.findElement(By.xpath("//div[contains(text(),'22575')]"));
		System.out.println("Base Amout of the Cart :" + baseAmount.getText());

		WebElement amountPayable = driver.findElement(By.xpath("//span[contains(text(),'26639')]"));
		System.out.println("Base Amout of the Cart :" + amountPayable.getText());

		WebElement viewDetails = driver.findElement(By.linkText("View Details"));
		viewDetails.click();

		Set<String> handlesSet = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<String>(handlesSet);
		driver.switchTo().window(handlesList.get(1));
		driver.close();
		driver.switchTo().window(handlesList.get(0));

		WebElement removeLink = driver.findElement(By.linkText("Remove"));
		removeLink.click();

		WebElement toastMessage = driver.findElement(By.xpath("//div[@class='toasted toasted-primary info']"));
		System.out.println("Alert Message present on the screen :" + toastMessage.getText());

		WebElement applyCoupon = driver.findElement(By.xpath("//span[contains(text(),'Apply Coupon')]"));
		applyCoupon.click();

		WebElement sendCouponCode = driver.findElement(By.xpath("//input[@placeholder='Type coupon code']"));
		sendCouponCode.sendKeys("ABC");

		WebElement apply = driver.findElement(By.xpath("//div[@class='coupon-form__button']//span[@class='default-slot'][contains(text(),'Apply')]"));
		apply.click();

		WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(),'Oops! This coupon is no longer valid.')]"));
		System.out.println("Error message for invalid coupon : " + errorMessage.getText());

		WebElement closeModal = driver.findElement(By.xpath("//button[@class='close']//span[contains(text(),'×')]"));
		closeModal.click();

		WebElement clickOnProfile = driver.findElement(By.xpath("//div[@class='profile-pic-initials']"));
		clickOnProfile.click();

		WebElement logout = driver.findElement(By.xpath("//ul[@class='avatar-menu']//a[@class='test link'][contains(text(),'Logout')]"));
		logout.click();

	}
}