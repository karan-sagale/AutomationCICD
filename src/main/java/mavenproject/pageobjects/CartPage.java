package mavenproject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mavenproject.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver; // LandingPage class driver variable

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;

	public CartPage(WebDriver driver) { // StanAlone class driver variable

		super(driver); // To send back the driver to AbstractComponent Parent Class

		this.driver = driver; // Assigned StandAlone class driver to this (i.e. LandingPage) class driver
								// variable
		PageFactory.initElements(driver, this);
		// To initialize WebElements using FindBy annotation of Page Object Model and
		// Page Factory pattern.
		// It is used to declare and locate web elements within a Page Object class.

	}

	public Boolean VerifyProductDisplay(String productName) {

		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));

		return match;

	}

	public CheckoutPage goToCheckout() {

		checkoutEle.click();

		return new CheckoutPage(driver);
	}

}
