package mavenproject.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mavenproject.TestComponents.BaseTest;
import mavenproject.pageobjects.CartPage;
import mavenproject.pageobjects.CheckoutPage;
import mavenproject.pageobjects.ConfirmationPage;
import mavenproject.pageobjects.OrderPage;
import mavenproject.pageobjects.ProductCatalogue;

public class StandAloneTest2 extends BaseTest {

	String productName = "ZARA COAT 3";

// public static void main(String[] args) throws InterruptedException {

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// String countryName = "india";

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		// ProductCatalogue productCatalogue = new ProductCatalogue(driver);

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(input.get("product"));

		CartPage cartPage = productCatalogue.goToCartPage();

		// CartPage cartPage = new CartPage(driver);

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));

		Assert.assertTrue(match); // Validations or Assertions cannot be in Page Object Classes/Files

		CheckoutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.selectCountry("india");

		// checkoutPage.selectCountry(countryName);

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	// To verify ZARA COAT 3 is displaying in orders page using Method Dependency
	// mechanism of TestNG
	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {

		ProductCatalogue productCatalogue = landingPage.loginApplication("ecomuser@gmail.com", "Ecom@123");

		OrderPage ordersPage = productCatalogue.goToOrdersPage();

		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

	}


//**************** JSON data provider method ****************************
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDatatoMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\mavenproject\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

//**************** HashMap data provider method ****************************
// 
//	@DataProvider
//	public Object[][] getData() {

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "ecomuser@gmail.com");
//		map.put("password", "Ecom@123");
//		map.put("product", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "ecomuser2@gmail.com");
//		map1.put("password", "Ecom@321");
//		map1.put("product", "ADIDAS ORIGINAL");

//		return new Object[][] { { map }, { map1 } }; }

//	************ Multi-Dimensional data provider method *********************
//		  
//	  @DataProvider
//	 
//	  public Object [][] getData(){
//	  
//	  return new Object [][] {{"ecomuser@gmail.com", "Ecom@123", "ZARA COAT 3"} ,
//	  {"ecomuser2@gmail.com", "Ecom@321", "ADIDAS ORIGINAL"} }; }
//	 

}
