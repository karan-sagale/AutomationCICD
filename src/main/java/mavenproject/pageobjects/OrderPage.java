package mavenproject.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mavenproject.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver; // LandingPage class driver variable

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;

	public OrderPage(WebDriver driver) { // StanAlone class driver variable

		super(driver); // To send back the driver to AbstractComponent Parent Class

		this.driver = driver; // Assigned StandAlone class driver to this (i.e. LandingPage) class driver
								// variable
		PageFactory.initElements(driver, this);
		// To initialize WebElements using FindBy annotation of Page Object Model and
		// Page Factory pattern.
		// It is used to declare and locate web elements within a Page Object class.

	}

	public Boolean VerifyOrderDisplay(String productName) {

		Boolean match = productNames.stream().anyMatch(product -> product.getText().equals(productName));

		return match;

	}

}
