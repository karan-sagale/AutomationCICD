package mavenproject.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import mavenproject.TestComponents.BaseTest;
import mavenproject.TestComponents.Retry;
import mavenproject.pageobjects.CartPage;
import mavenproject.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	// public static void main(String[] args) throws InterruptedException {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		// String countryName = "india";

		landingPage.loginApplication("abc@gmail.com", "Ecom");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		// String countryName = "india";

		ProductCatalogue productCatalogue = landingPage.loginApplication("ecomuser@gmail.com", "Ecom@123");

		// ProductCatalogue productCatalogue = new ProductCatalogue(driver);

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(productName);

		CartPage cartPage = productCatalogue.goToCartPage();

		// CartPage cartPage = new CartPage(driver);

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");

		Assert.assertFalse(match); // Validations or Assertions cannot be in Page Object Classes/Files

	}

}
