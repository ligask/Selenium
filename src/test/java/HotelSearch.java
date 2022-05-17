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

        String checkInMonth = "maj 2022";
        String checkOutMonth = "maj 2022";
        String checkInDay = "23";
        String checkOutDay = "27";


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
            String inDay = element.getText();

            if(inDay.equals(checkInDay)) {
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
            String outDay = element.getText();

            if(outDay.equals(checkOutDay)) {
                element.click();
                break;
            }
        }

        int adultNumber = 4;

        driver.findElement(By.id("xp__guests__toggle")).click();
        String nbr = driver.findElement(By.className("bui-stepper__display")).getText();
        int defaultAdultNumber = Integer.parseInt(nbr);

        while(defaultAdultNumber < adultNumber) {
            driver.findElement(By.xpath("//button[@aria-label='Dorośli: zwiększ liczbę']")).click();
            defaultAdultNumber++;
        }

        while(defaultAdultNumber > adultNumber) {
            driver.findElement(By.xpath("//button[@aria-label='Dorośli: zmniejsz liczbę']")).click();
            defaultAdultNumber--;
        }

        int roomsNbr = 2;
        int defaultRoomsNbr = 1;

        while(defaultRoomsNbr < roomsNbr) {
            driver.findElement(By.xpath("//button[@aria-label='Pokoje: zwiększ liczbę']")).click();
            defaultRoomsNbr++;
        }

        driver.findElement(By.xpath("//span[@class='js-sb-submit-text ']")).click();

    }
}
