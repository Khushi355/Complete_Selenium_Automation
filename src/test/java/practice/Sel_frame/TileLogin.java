package practice.Sel_frame;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import practice.Sel_frame.TestComponent.BaseTest;
import practice.Sel_frame.TestComponent.BaseTestTwo;
import practice.Sel_frame.projectObject.LoginPage;

public class TileLogin extends BaseTest {

	@Test(dataProvider = "getData", groups = { "logged" })
	public void tileLogin(HashMap<String, String> input) throws IOException {

		// loginPage.goTo();
		System.out.println(driver.getTitle());

		// email password data
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
