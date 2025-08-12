package mavenproject.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mavenproject.TestComponents.BaseTest;
import mavenproject.pageobjects.CartPage;
import mavenproject.pageobjects.CheckoutPage;
import mavenproject.pageobjects.ConfirmationPage;
import mavenproject.pageobjects.LandingPage;
import mavenproject.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {

		landingPage = this.launchApplication();

	}

	@Given("^Log in with username (.+) and password (.+)$")
	public void log_in_username_and_password(String username, String password) {

		productCatalogue = landingPage.loginApplication(username, password);

	}

	@When("^I add the product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(productName);

	}

	@And("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);

		Assert.assertTrue(match); // Validations or Assertions cannot be in Page Object Classes/Files

		CheckoutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.selectCountry("india");

		confirmationPage = checkoutPage.submitOrder();

	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {

		String confirmMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));

		driver.quit();
	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) throws Throwable {

		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.quit();
	}

}
