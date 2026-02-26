package practice.Sel_frame;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import practice.Sel_frame.TestComponent.BaseTest;
import practice.Sel_frame.projectObject.CartPage;

public class ErrorValidation extends BaseTest
{
	@Test
	public void tileLogin() throws IOException {

		loginPage.loginApplication("dali12@gmail.com", "rHP5I944$ja");
		
		Assert.assertTrue(
				loginPage.getErrorMsg().contains("Incorrect email or password")
			);
		
	}//Invalid email or password. Please check your credentials and try again.
	
	@Test(groups= {"ErrorHandling"})
	public  void addToCart()throws IOException
	{
		CartPage cartPage = new CartPage(driver);
		List<String> expectedProducts = Arrays.asList("Onion", "Broccoli", "Green Chilli");
	
		Assert.assertFalse(cartPage.isAllProductsDisplayedInCart(expectedProducts));
	System.out.println(expectedProducts);
}
}
