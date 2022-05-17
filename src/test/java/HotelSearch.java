import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
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

        String checkInMonth = "marzec 2023";
        String checkOutMonth = "marzec 2023";
        String checkInDay = "23";
        String checkOutDay = "37";


        while(true) {
            String month = driver.findElement(By.xpath("(//div[@class='bui-calendar__month'])[1]")).getText();

            if(month.equals(checkInMonth)) {
                break;
            } else {
                driver.findElement(By.xpath("//div[@data-bui-ref='calendar-next']")).click();
                break;
            }
        }

        List<WebElement> inMonth = driver.findElements(By.xpath("(//tbody)[1]//tr//td//span"));

        for(WebElement element:inMonth) {
            String day = element.getText();

            if(day.equals(checkInDay)) {
                element.click();
                break;
            }
        }

        while(true) {
            String month = driver.findElement(By.xpath("(//div[@class='bui-calendar__month'])[1]")).getText();

            if(month.equals(checkOutMonth)) {
                break;
            } else {
                driver.findElement(By.xpath("//div[@data-bui-ref='calendar-next']")).click();
            }
        }

        List<WebElement> outMonth = driver.findElements(By.xpath("(//tbody)[1]//tr//td//span"));

        for(WebElement element:outMonth) {
            String day = element.getText();

            if(day.equals(checkOutDay)) {
                element.click();
                break;
            }
        }






    }
}
