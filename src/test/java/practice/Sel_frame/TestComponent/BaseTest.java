package practice.Sel_frame.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import practice.Sel_frame.projectObject.CartPage;
import practice.Sel_frame.projectObject.LoginPage;

public class BaseTest {

    public WebDriver driver;
    public LoginPage loginPage;

    public WebDriver initilizeDriver() throws IOException {

        //property class
        Properties prop = new Properties();
        FileInputStream fis= new FileInputStream
            (System.getProperty("user.dir") +  "//src//main//java//practice//Sel_frame//resources//GlobalData.properties");

        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome"))
        {
            System.out.println("[BaseTest] Initializing Chrome WebDriver");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            
         
    			if(browserName.contains("headless")){
    			options.addArguments("headless");
    			}		
   
            driver = new ChromeDriver(options);
            System.out.println("[BaseTest] ChromeDriver started");

         }
         else if(browserName.equalsIgnoreCase("edge"))
         {
             //Edge
             System.setProperty("webD=driver.edge.driver" , "edge.exe");
              driver = new EdgeDriver();
         }
         else if(browserName.equalsIgnoreCase("firefox"))
         {
             //firefox
         }

          driver.manage().window().maximize();
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
          return driver;

 }

     // take Screenshot
         public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
             TakesScreenshot ts = (TakesScreenshot) driver;
             File source = ts.getScreenshotAs(OutputType.FILE);
             File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
             FileUtils.copyFile(source, file);
             return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

         }

     //data provider
     public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

         String jsonContent = FileUtils.readFileToString(
                 new File(filePath), StandardCharsets.UTF_8);

         ObjectMapper mapper = new ObjectMapper();

         List<HashMap<String, String>> data =
                 mapper.readValue(jsonContent,
                         new TypeReference<List<HashMap<String, String>>>() {});

         return data;
     }

     @BeforeMethod
     public LoginPage launchApplication() throws IOException
     {
          driver= initilizeDriver();
          loginPage = new LoginPage(driver);
          loginPage.goTo();
          // Optional debug pause so you can observe the browser before the test continues/teardown
          String pause = System.getProperty("debugPause");
          if (pause != null) {
              try {
                  int seconds = Integer.parseInt(pause);
                  System.out.println("[BaseTest] Pausing for " + seconds + " seconds for debugging (debugPause)");
                  Thread.sleep(seconds * 1000L);
              } catch (Exception e) {
                  System.out.println("[BaseTest] Invalid debugPause value: " + pause);
              }
          }
          return loginPage;
     }

     @AfterMethod
     public void closeApplication() {
         driver.quit();
     }
 }
