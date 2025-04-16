package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.UserRegistrationPage;
import utils.Utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {
    @Test(description = "User registration")
    public void userRegistration() throws InterruptedException, IOException, ParseException {
        driver.findElement(By.partialLinkText("Register")).click();
        UserRegistrationPage user=new UserRegistrationPage(driver);
        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String email=(firstName+lastName+ Utils.generateRandomNumber(1000,9999)+"@gmail.com");
        String password="1234";
        String phoneNumber="0160"+ Utils.generateRandomNumber(1000000,9999999);
        String address=faker.address().fullAddress();

        UserModel userModel=new UserModel();
        userModel.setFirstname(firstName);
        //userModel.setLastname(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phoneNumber);
        //userModel.setAddress(address);
        user.userRegistration(userModel);

        Thread.sleep(2000);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50)); //explicit wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        String successfulMessageActual= driver.findElement(By.className("Toastify__toast")).getText();
        String successfulMessageExpected="registered successfully!";
        System.out.println(successfulMessageActual);

        JSONObject userObj=new JSONObject();
        userObj.put("firstName",firstName);
        //userObj.put("lastName",lastName);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phoneNumber);
        //userObj.put("address",address);
        Utils.saveUserData("./src/test/resources/users.json",userObj);

        Assert.assertTrue(successfulMessageActual.contains(successfulMessageExpected));

    }
}
