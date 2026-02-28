package practice.Sel_frame;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import practice.Sel_frame.TestComponent.BaseTest;

public class TileLogin extends BaseTest {

	@Test(dataProvider = "getData", groups = { "logged" })
	public void tileLogin(HashMap<String, String> input) throws IOException {

		System.out.println("Test Case: Login with email: " + input.get("email"));
		String beforeTitle = driver.getTitle();
		System.out.println("Current Page Title: " + beforeTitle);

		// Verify we're on the login page
		Assert.assertFalse(beforeTitle.isEmpty(), "Initial page title should not be empty");

		// Perform login
		loginPage.loginApplication(input.get("email"), input.get("password"));

		// After login, assert that URL or title changed (basic smoke assertion)
		String afterUrl = driver.getCurrentUrl();
		String afterTitle = driver.getTitle();
		System.out.println("After Login URL: " + afterUrl);
		System.out.println("After Login Title: " + afterTitle);

		// Expect either the URL or title to change after a successful login
		boolean urlChanged = !afterUrl.equals("https://srijanx.com");
		boolean titleChanged = !afterTitle.equals(beforeTitle);

		Assert.assertTrue(urlChanged || titleChanged, "Expect page to navigate or title to change after login");

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\practice\\Sel_frame\\data\\loginData.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

		// return new Object[][] {{"dali12@gmail.com",
		// "rHP5I944$ja2"},{"sellertestworker@gmail.com", "zo@ryK9QVq@Q"}};
	}

}
