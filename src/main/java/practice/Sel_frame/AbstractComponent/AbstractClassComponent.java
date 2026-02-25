package practice.Sel_frame.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractClassComponent {

	WebDriver driver;
	protected WebDriverWait wait;

	public AbstractClassComponent(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Wait for element to be visible (By)
	public void waitForElementToAppear(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Wait for element to be visible (WebElement)
	public void waitForElementToAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Wait for element to be clickable
	public void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	// wait for click in By locator
	public void click(By locator) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	// Wait for element to disappear (overlay, loader)
	public void waitForElementToDisappear(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	// ✅ reusable sendKeys in text
	public void sendKeys(WebElement element, String text) {
	WebElement ele =	wait.until(ExpectedConditions.elementToBeClickable(element));
	ele.clear();
	ele.sendKeys(text);
	}
	

	/*
	 * // sendkeys for numbers public void sendKeys(WebElement element, int number)
	 * {
	 * wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(String.valueOf(
	 * number)); }
	 */

}
