package mavenproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mavenproject.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver; // LandingPage class driver variable

	public LandingPage(WebDriver driver) { // StanAlone class driver variable

		super(driver); // To send back the driver to AbstractComponent Parent Class

		this.driver = driver; // Assigned StandAlone class driver to this (i.e. LandingPage) class driver
								// variable
		PageFactory.initElements(driver, this);
		// To initialize WebElements using FindBy annotation of Page Object Model and
		// Page Factory pattern.
		// It is used to declare and locate web elements within a Page Object class.

	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	// PageFactory annotation to find the elements - Alternative to above step
	@FindBy(id = "userEmail")
	WebElement userEmail;

	// driver.findElement(By.id("userPassword"))
	@FindBy(id = "userPassword")
	WebElement passwordEle;

	// driver.findElement(By.id("login"))
	@FindBy(id = "login")
	WebElement submit;

	// ng-tns-c4-13 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr
	// toast-error
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String password) { // Action method

		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client");

	}

	public String getErrorMessage() {

		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
