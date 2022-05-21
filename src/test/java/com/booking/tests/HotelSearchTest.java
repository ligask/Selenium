package com.booking.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.booking.pages.HotelResultsPage;
import com.booking.pages.HotelSearchPage;
import com.booking.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {

        ExtentTest test = extentReports.createTest("Search Hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        HotelResultsPage hotelResultsPage = new HotelResultsPage(driver);
        hotelSearchPage.acceptAllCookies();
        hotelSearchPage.setLocation("London");
        test.log(Status.PASS, "Location set");
        hotelSearchPage.setCheckInDate("maj 2022", "25");
        hotelSearchPage.setCheckOutDate("maj 2022", "26");
        test.log(Status.PASS, "Checkin and checkout dates set");
        hotelSearchPage.setAdultsNumber(4);
        test.log(Status.PASS, "Adults number set");
        hotelSearchPage.setRoomsNumber(2);
        test.log(Status.PASS, "Rooms number set");
        hotelSearchPage.searchResults();
        test.log(Status.PASS, "Hotels displayed");
        hotelResultsPage.assertMarriottHotels();
        test.log(Status.PASS, "Assertion passed");
    }

    @Test
    public void searchHotelWithoutLocationTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.acceptAllCookies();
        hotelSearchPage.openCalendar();
        hotelSearchPage.setCheckInDate("maj 2022", "27");
        hotelSearchPage.setCheckOutDate("maj 2022", "28");
        hotelSearchPage.setAdultsNumber(5);
        hotelSearchPage.setRoomsNumber(3);
        hotelSearchPage.searchResults();
        hotelSearchPage.assertIfNoLocationErrorPresent();
    }
}
