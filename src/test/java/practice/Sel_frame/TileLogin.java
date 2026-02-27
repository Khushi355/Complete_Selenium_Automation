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
		System.out.println("Current Page Title: " + driver.getTitle());

		// Verify we're on the login page
		Assert.assertNotNull(driver.getTitle(), "Page title should not be null");

		// Perform login
		loginPage.loginApplication(input.get("email"), input.get("password"));
		driver.close();

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
