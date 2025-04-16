package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import page.LoginPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {
    @Test(priority = 1, description = "Admin user login")
    public void adminLogin(){
        LoginPage loginPage=new LoginPage(driver);
        if(System.getProperty("email")!=null && System.getProperty("password")!=null){
            loginPage.doLogin(System.getProperty("email"),System.getProperty("password"));
        }
        else{
            loginPage.doLogin("admin@test.com","admin123");
        }

    }
    @Test (priority = 2, description = "Admin logout")
    public void logout(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogout();
    }
    @Test (priority = 3, description = "User Login")
    public void userLogin() throws IOException, ParseException {
        JSONParser parser=new JSONParser();
        JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email=userObj.get("email").toString();
        String password=userObj.get("password").toString();

        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin(email,password);
    }
}
