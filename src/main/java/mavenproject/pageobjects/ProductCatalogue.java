package mavenproject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mavenproject.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver; // LandingPage class driver variable

	public ProductCatalogue(WebDriver driver) { // StanAlone class driver variable

		super(driver); // To send back the driver to AbstractComponent Parent Class

		this.driver = driver; // Assigned StandAlone class driver to this (i.e. LandingPage) class driver
								// variable
		PageFactory.initElements(driver, this);
		// To initialize WebElements using FindBy annotation of Page Object Model and
		// Page Factory pattern.
		// It is used to declare and locate web elements within a Page Object class.

	}

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	// PageFactory annotation to find the elements - Alternative to above step
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {

		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		return prod;

	}

	public void addProductToCart(String productName) throws InterruptedException {

		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);

	}

}
