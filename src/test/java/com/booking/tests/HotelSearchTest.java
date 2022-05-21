package com.booking.tests;

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

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        HotelResultsPage hotelResultsPage = new HotelResultsPage(driver);
        hotelSearchPage.acceptAllCookies();
        hotelSearchPage.setLocation("London");
        hotelSearchPage.setCheckInDate("maj 2022", "25");
        hotelSearchPage.setCheckOutDate("maj 2022", "26");
        hotelSearchPage.setAdultsNumber(4);
        hotelSearchPage.setRoomsNumber(2);
        hotelSearchPage.searchResults();
        hotelResultsPage.assertMarriottHotels();
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
