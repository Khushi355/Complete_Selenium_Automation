package practice.Sel_frame;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import practice.Sel_frame.TestComponent.BaseTest;
import practice.Sel_frame.TestComponent.BaseTestTwo;
import practice.Sel_frame.projectObject.LoginPage;

public class TileLogin extends BaseTest {

	@Test(dataProvider="getData", groups={"logged"})
	public void tileLogin(HashMap<String,String> input) throws IOException {

		// constructor object
		//LoginPage loginPage = launchApplication();
		
		//loginPage.goTo();
		System.out.println(driver.getTitle());

		//driver.findElement(By.xpath("//div/button[2]")).click();

		// email password data
		loginPage.loginApplication(input.get("email"), input.get("password"));
		driver.close();
		//closeApplication();
	
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List <HashMap<String, String>> data = getJsonDataToMap
				(System.getProperty("user.dir") + "\\src\\test\\java\\practice\\Sel_frame\\data\\loginData.json");
		
		return new Object[][] 
		{ 
			{ data.get(0) },
            { data.get(1) }
		};
		
		//return new Object[][] {{"dali12@gmail.com", "rHP5I944$ja2"},{"sellertestworker@gmail.com", "zo@ryK9QVq@Q"}};
	}

}
