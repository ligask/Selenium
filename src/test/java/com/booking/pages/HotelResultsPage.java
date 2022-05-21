package com.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HotelResultsPage {

    @FindBy(xpath = "//div[contains(text(),'Marriot')]")
    private List<WebElement> marriottHotels;

    public HotelResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void assertMarriottHotels() {

        for (WebElement element : marriottHotels) {
            String mHotelsList = element.getText();
            Assert.assertTrue(mHotelsList.contains("Marriott"));
        }
    }
}
