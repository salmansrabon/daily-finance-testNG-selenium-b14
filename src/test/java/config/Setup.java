package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import page.LoginPage;

import java.time.Duration;

public class Setup {
    public WebDriver driver;
    @BeforeTest
    public void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("http://localhost:3000/");
    }
    //@AfterTest
    public void tearDown(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogout();
        //driver.quit();
    }
}
