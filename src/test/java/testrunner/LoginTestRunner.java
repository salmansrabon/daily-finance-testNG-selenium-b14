package testrunner;

import config.Setup;
import org.testng.annotations.Test;
import page.LoginPage;

public class LoginTestRunner extends Setup {
    @Test(priority = 1, description = "Admin user login")
    public void adminLogin(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("admin@test.com","admin123");
    }
    @Test (priority = 2, description = "Admin logout")
    public void logout(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogout();
    }
}
