import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class LoginAndFindingOfGoods {
    public String userNameField;
    public String passwordField;
    public String submitButton;
    public String allLogins;
    public String allPasswords;
    public String firstWood_sName;
    WebDriver driver = null;

    public void scanningFile () throws FileNotFoundException {
        File file = new File("src/test/java/SauceDemoLocators.txt");
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] locators = line.split(" ");
        System.out.println(Arrays.toString(locators));
        scanner.close();
        userNameField = locators[0];
        passwordField = locators[1];
        submitButton = locators[2];
        allLogins = locators[3];
        allPasswords = locators[4];
        firstWood_sName = locators[5];
        //creating and scanning file with id and xpathes
    }

    @BeforeMethod
    public void openChrome () throws FileNotFoundException {
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void isWoodsDisplayed() throws FileNotFoundException {
        scanningFile();
        WebElement getUserName = driver.findElement(By.id(allLogins));
        //find all users names
        String userNames = getUserName.getAttribute("innerText");
        String[] firstUserName = userNames.split("\n");
        System.out.println(Arrays.toString(firstUserName));
        //create string[] and split string[] with users names
        WebElement getUserNameField = driver.findElement(By.id(userNameField));
        //find user name field
        getUserNameField.click();
        getUserNameField.sendKeys(firstUserName[1]);
        //send first user name to user name field
        WebElement getPassword = driver.findElement(By.xpath(allPasswords));
        //find password field
        String passwordText = getPassword.getAttribute("innerText");
        String[] passwords = passwordText.split("\n");
        //find string[] with passwords and split by new line all passwords
        System.out.println(Arrays.toString(passwords));
        WebElement getPasswordField = driver.findElement(By.id(passwordField));
        //find password field
        getPasswordField.click();
        getPasswordField.sendKeys(passwords[1]);
        //send password to password field
        WebElement getSubmitButton = driver.findElement(By.id(submitButton));
        getSubmitButton.click();
        //find and click submit button
        WebElement firstWood = driver.findElement(By.xpath(firstWood_sName));
        //find of first wood's name
        String wood_sName = firstWood.getAttribute("innerText");
        //get first wood's name
        System.out.println("1) " + wood_sName);
        Assert.assertEquals(wood_sName, "Sauce Labs Backpack");
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}




