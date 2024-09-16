import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.time.Duration;

public class Base {

    protected WebDriver driver;
    protected final String URL = "http://wordpresslearn.local/wp-admin/";
    protected final String PRECISE_TEXT_XPATH = "//*[text()='%s']";


    // Environment variables
    Dotenv dotenv;
    String UserName;
    String PassWord;

    @BeforeMethod
    public void setup()
    {
        // Load environment variables
        dotenv = Dotenv.load();
        UserName = dotenv.get("APP_USERNAME");
        PassWord = dotenv.get("APP_PASSWORD");

        // Print environment variables for debugging
        System.out.println("Username: " + UserName);
        System.out.println("Password: " + PassWord);

        // Set up WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // implicit wait
    }


    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}
