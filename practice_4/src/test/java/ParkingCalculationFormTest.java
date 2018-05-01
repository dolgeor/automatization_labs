import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

public class ParkingCalculationFormTest {
    WebDriver driver;

    @BeforeClass
    public void connectToWebSite() {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://adam.goucher.ca/parkcalc/index.php");
    }

    @AfterClass
    public void closeWebBrowser() throws InterruptedException {
        Thread.sleep(5_000);
        driver.findElement(By.name("Submit")).click();
        Thread.sleep(2_000);
        driver.quit();
    }

    @DataProvider
    public Object[][] getHours() {
        return new Object[][]{{"11:00","5/1/2018", "5:00","5/2/2018"}};
    }

    @Test(dataProvider = "getHours")
    public void fillTimeInputs(String from,String fromDate, String to, String toDate) {
        WebElement begin = driver.findElement(By.id("EntryTime"));
        begin.clear();
        begin.sendKeys(from);
        WebElement beginDate = driver.findElement(By.id("EntryDate"));
        beginDate.clear();
        beginDate.sendKeys(fromDate);
        WebElement end = driver.findElement(By.id("ExitTime"));
        end.clear();
        end.sendKeys(to);
        WebElement endDate = driver.findElement(By.id("ExitDate"));
        endDate.clear();
        endDate.sendKeys(toDate);
    }

    @Test
    public void fillCheckBoxAndRadioButton() {
        WebElement entryRadio = driver.findElement(By
                .cssSelector("input[name='EntryTimeAMPM'][value='PM']"));
        entryRadio.click();
    }

    @Test
    public void fillDropDown() {
        Select lotDropDown = new Select(driver.findElement(By.id("Lot")));
        lotDropDown.selectByValue("EP");
    }
}
