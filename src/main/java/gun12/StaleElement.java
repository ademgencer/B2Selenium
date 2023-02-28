package gun12;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.Utils;

import java.time.Duration;

public class StaleElement {

    WebDriver driver;
    WebDriverWait wait;

    public StaleElement(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testStaleElement(){
        driver.get("https://the-internet.herokuapp.com/frames");
        WebElement element = driver.findElement(By.linkText("iFrame"));

        driver.navigate().refresh();
        // webelementi refresh oncesi olusturursam,
        // refresh sonrasi StaleElementReferenceException
        element.click();
        Utils.bekle(3000);
        driver.quit();
    }

    @Test
    public void testStaleElement2(){
        driver.get("https://the-internet.herokuapp.com/frames");
        By link = By.linkText("iFrame");
        WebElement element = driver.findElement(link);
        wait.until(ExpectedConditions.visibilityOf(element));

        driver.navigate().refresh();

        element = driver.findElement(link);
        element.click();
        Utils.bekle(3000);
        driver.quit();
    }
}
