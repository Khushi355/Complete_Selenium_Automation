package practice.Sel_frame;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
//import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import practice.Sel_frame.AbstractComponent.AbstractClassComponent;
import practice.Sel_frame.TestComponent.BaseTest;
import practice.Sel_frame.projectObject.CartPage;

public class AddToCart extends BaseTest {

	@Test
	public void addToCart() throws IOException {

		// AbstractClassComponent abstractClassComponent = new
		// AbstractClassComponent(driver)
		driver = initilizeDriver();
		CartPage cartPage = new CartPage(driver);
		cartPage.url();

		System.out.println(driver.getTitle());
		cartPage.cartApplication();

		Map<String, Integer> products = new HashMap<>();
		products.put("Onion (Pyaz)", 3);
		products.put("Broccoli", 2);
		products.put("Green Chilli (Hari Mirch)", 4);

		cartPage.addProductsToCart(products);

		List<String> expectedProducts = Arrays.asList("Onion", "Broccoli", "Green Chilli");

		Assert.assertTrue(cartPage.isAllProductsDisplayedInCart(expectedProducts));
		System.out.println(expectedProducts);

		cartPage.checkOutProcess("8319198564");

	}

}
