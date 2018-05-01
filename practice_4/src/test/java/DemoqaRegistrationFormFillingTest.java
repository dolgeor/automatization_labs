import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Path;

public class DemoqaRegistrationFormFillingTest {
    WebDriver driver;

    @BeforeClass
    public void connectToWebSite() {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://demoqa.com/registration/");
    }

    @AfterClass
    public void closeWebBrowser() throws InterruptedException {
        Thread.sleep(5_000);
        driver.findElement(By.name("pie_submit")).click();
        Thread.sleep(5_000);
        driver.quit();
    }

    @DataProvider
    public Object[][] getNames() {
        return new Object[][]{{"Gheorghe", "Dolomanji"}};

    }

    @Test(dataProvider = "getNames")
    public void fillFirstNameAndLastNameInputs(String name, String surname) {
        WebElement firstName = driver.findElement(By.id("name_3_firstname"));
        firstName.sendKeys(name);
        WebElement lastName = driver.findElement(By.id("name_3_lastname"));
        lastName.sendKeys(surname);
    }

    @Test
    public void fillCheckBoxAndRadioButton() {
        WebElement danceCheckBox = driver.findElement(By.cssSelector("input[value='dance']"));
        danceCheckBox.click();
        WebElement readingCheckBox = driver.findElement(By.cssSelector("input[value='reading']"));
        readingCheckBox.click();
        WebElement singleRadioButton = driver.findElement(By.cssSelector("input[value='single']"));
        singleRadioButton.click();
    }

    @Test
    public void fillDropDown() {
        Select countryDropDown = new Select(driver.findElement(By.id("dropdown_7")));
        countryDropDown.selectByValue("Bulgaria");

        Select monthDropDown = new Select(driver.findElement(By.id("mm_date_8")));
        monthDropDown.selectByValue("4");

        Select dayDropDown = new Select(driver.findElement(By.id("dd_date_8")));
        dayDropDown.selectByValue("17");

        Select yearDropDown = new Select(driver.findElement(By.id("yy_date_8")));
        yearDropDown.selectByValue("1996");
    }

    @Test
    public void fillFileInput() {
        driver.findElement(By.id("profile_pic_10")).sendKeys("D:\\logo.jpg");
    }

    @Test
    public void fillInputs() {
        WebElement phoneNumber = driver.findElement(By.id("phone_9"));
        phoneNumber.sendKeys("37368851975");
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("dolgeor");
        WebElement eMail = driver.findElement(By.id("email_1"));
        eMail.sendKeys("dolgeor@foo.com");
        WebElement description = driver.findElement(By.id("description"));
        description.sendKeys("nea)))))))))))))))))))))))))))))))))");
        WebElement password = driver.findElement(By.id("password_2"));
        password.sendKeys("qazxswedcvfr54321tgbnhyu8765439bj");
        WebElement confirmPassword = driver.findElement(By.id("confirm_password_password_2"));
        confirmPassword.sendKeys("qazxswedcvfr54321tgbnhyu8765439bj");
    }

}
