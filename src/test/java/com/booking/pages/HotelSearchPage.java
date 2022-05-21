package com.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HotelSearchPage {

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookie;

    @FindBy(id = "ss")
    private WebElement searchHotelLocation;

    @FindBy(xpath = "//span[@class='search_hl_name' and text()='London']")
    private WebElement hotelLocationMatch;

    @FindBy(xpath = "(//span[contains(@class,'icon-btn bk-svg-wrapper calendar-restructure-sb')])[1]")
    private WebElement calendar;

    @FindBy(xpath = "(//div[@class='bui-calendar__month'])[1]")
    private WebElement calendarMonth;

    @FindBy(xpath = "//div[@data-bui-ref='calendar-next']")
    private WebElement calendarNext;

    @FindBy(xpath = "(//tbody)[1]//tr//td//span")
    private List<WebElement> monthDays;

    @FindBy(id = "xp__guests__toggle")
    private WebElement guestsToggle;

    @FindBy(className = "bui-stepper__display")
    private WebElement stepperDisplay;

    @FindBy(xpath = "//button[@aria-label='Dorośli: zwiększ liczbę']")
    private WebElement adultPlus;

    @FindBy(xpath = "//button[@aria-label='Dorośli: zmniejsz liczbę']")
    private WebElement adultMinus;

    @FindBy(xpath = "//button[@aria-label='Pokoje: zwiększ liczbę']")
    private WebElement roomPlus;

    @FindBy(xpath = "//span[@class='js-sb-submit-text ']")
    private WebElement searchButton;

    @FindBy(id = "destination__error")
    private WebElement noLocationError;

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void acceptAllCookies() {
        acceptCookie.click();
    }

    public void setLocation(String locationName) {
        searchHotelLocation.click();
        searchHotelLocation.sendKeys(locationName);
        hotelLocationMatch.click();
    }

    public void setCheckInDate(String checkInMonth, String checkInDay) {

        while (true) {
            String month = calendarMonth.getText();

            if (month.equals(checkInMonth)) {
                break;
            } else {
                calendarNext.click();
                break;
            }
        }

        List<WebElement> inMonth = monthDays;

        for (WebElement element : inMonth) {
            String inDay = element.getText();

            if (inDay.equals(checkInDay)) {
                element.click();
                break;
            }
        }
    }

    public void setCheckOutDate(String checkOutMonth, String checkOutDay) {

        while (true) {
            String month = calendarMonth.getText();

            if (month.equals(checkOutMonth)) {
                break;
            } else {
                calendarNext.click();
            }
        }

        List<WebElement> outMonth = monthDays;

        for (WebElement element : outMonth) {
            String outDay = element.getText();

            if (outDay.equals(checkOutDay)) {
                element.click();
                break;
            }
        }
    }

    public void setAdultsNumber(int adultNumber) {

        guestsToggle.click();
        String nbr = stepperDisplay.getText();
        int defaultAdultNumber = Integer.parseInt(nbr);

        while (defaultAdultNumber < adultNumber) {
            adultPlus.click();
            defaultAdultNumber++;
        }

        while (defaultAdultNumber > adultNumber) {
            adultMinus.click();
            defaultAdultNumber--;
        }
    }

    public void setRoomsNumber(int roomsNumber) {
        int defaultRoomsNbr = 1;

        while (defaultRoomsNbr < roomsNumber) {
            roomPlus.click();
            defaultRoomsNbr++;
        }
    }

    public void openCalendar() {
        calendar.click();
    }

    public void assertIfNoLocationErrorPresent() {
        Assert.assertTrue(noLocationError.isDisplayed());
    }

    public void searchResults() {
        searchButton.click();
    }
}
