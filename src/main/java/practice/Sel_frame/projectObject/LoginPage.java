package practice.Sel_frame.projectObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import practice.Sel_frame.AbstractComponent.AbstractClassComponent;

public class LoginPage extends AbstractClassComponent {

	WebDriver driver;
	WebDriverWait wait;

	// create constructor
	public LoginPage(WebDriver driver) {
		super(driver); // AbstractClassComponent constructor
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// PageFactory
	@FindBy(xpath = "//div/button[2]")
	WebElement signinBtn;

	@FindBy(id = "email-input")
	WebElement userEmail;

	@FindBy(id = "password-input")
	WebElement userPassword;

	@FindBy(css = "button.touch-manipulation.w-full")
	WebElement btn;
	
	@FindBy(xpath="//p[contains(@class,'text-red-700')]")
	WebElement errorMsg;

	// create a method for action
	public void loginApplication(String email, String password) {

		click(signinBtn); // wait + click

		waitForElementToAppear(userEmail);
		sendKeys(userEmail, email); // wait + sendKeys

		waitForElementToAppear(userPassword);
		sendKeys(userPassword, password); // wait + sendKeys

		click(btn);

	}
	
	public String getErrorMsg()
	{
		waitForElementToAppear(errorMsg);
		return errorMsg.getText().trim();
	}

	public void goTo() {
		driver.get("https://srijanx.com");
	}

}
