package practice.Sel_frame.projectObject;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import practice.Sel_frame.AbstractComponent.AbstractClassComponent;

public class CartPage extends AbstractClassComponent {
	WebDriver driver;
	WebDriverWait wait;

	// create constructor
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void url() {
		driver.get("https://blinkit.com/");
	}

//PageFactory
	@FindBy(xpath = "//button[text() = 'Detect my location']")
	WebElement locationBtn;

	@FindBy(css = ".LocationDropDown__LocationOverlay-sc-bx29pc-1")
	WebElement overlay;

	@FindBy(xpath = "//img[@alt = '3 - Fruits & Vegetables']")
	WebElement fruitBoxClick;

	@FindBy(css="div.CartButton__Button-sc-1fuy2nj-5")
	WebElement goTocart;
	
	@FindBy(css = "div.DefaultProductCard__ProductTitle-sc-18qk0hu-6")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//div[text()='Login to Proceed']")
	WebElement checkoutClick;
	
	@FindBy(css="input[placeholder='Enter mobile number']")
	WebElement enterNumber;
	
	@FindBy(xpath="//button[text()='Continue']")
	WebElement signinForCheckout;

	public void cartApplication() {

		click(locationBtn);
		waitForElementToDisappear(overlay);
		click(fruitBoxClick);
		
	}

	public void addProductsToCart(Map<String, Integer> products) {
		for (Map.Entry<String, Integer> entry : products.entrySet()) {

			String itemName = entry.getKey();
			int qty = entry.getValue();

			// ADD button
			By addBtn = By.xpath("//div[contains(normalize-space(),'" + itemName + "')]"
					+ "/ancestor::div[contains(@class,'tw-flex-col')]//div[normalize-space()='ADD']");
			click(addBtn);

			// PLUS button
			By plusBtn = By.xpath("//div[text()='" + itemName + "']" + "/ancestor::div[contains(@class,'tw-flex-col')]"
					+ "//button[.//span[contains(@class,'icon-plus')]]");

			// increase quantity
			for (int i = 1; i < qty; i++) {
				click(plusBtn);
			}
		}
		
		click(goTocart);
	}
	
	
	
	public boolean isAllProductsDisplayedInCart(List<String> expectedProducts) {
		

		List<String> cartProductNames = cartProducts.stream()
				.map(p -> p.getText().trim().toLowerCase()).collect(Collectors.toList());
		cartProducts.forEach(p ->
	    System.out.println("Cart product: " + p.getText())
	    
	);
		return expectedProducts.stream().map(String::toLowerCase)
				.allMatch(expected -> cartProductNames.stream().anyMatch(cart -> cart.contains(expected)));
		
	}
	

	public void checkOutProcess(String mobileNumber)
	{
		click(checkoutClick);
		sendKeys(enterNumber, mobileNumber);
		click(signinForCheckout);
	}
	
	
	
	
	
	
	
	
}
