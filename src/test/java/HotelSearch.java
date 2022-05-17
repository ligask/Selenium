import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HotelSearch {

    @Test
    public void searchHotel() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.booking.com");

        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        driver.findElement(By.id("ss")).click();
        driver.findElement(By.id("ss")).sendKeys("London");
        //driver.findElement(By.id("ss")).sendKeys(Keys.RETURN);
        driver.findElement(By.xpath("//span[@class='search_hl_name' and text()='London']")).click();





    }
}
