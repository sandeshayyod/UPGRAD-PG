package com.upgrad.cc.booking.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookingUtilTest {

    @Test
    public void test_getRandomNumbers_when_passed_count_5_should_return_list_of_5() {
        List<String> randList = BookingUtil.getRandomNumbers(5);
        Assertions.assertNotNull(randList);
        Assertions.assertEquals(5, randList.size());
    }

    @Test
    public void test_getRandomNumbers_when_passed_count_0_should_return_empty_list() {
        List<String> randList = BookingUtil.getRandomNumbers(0);
        Assertions.assertNotNull(randList);
        Assertions.assertEquals(0, randList.size());
    }

    @Test
    public void test_getNoOfDays_when_passed_2021_06_16_and_2021_07_25_should_return_10() {
        int days = BookingUtil.getNoOfDays("2021-06-16", "2021-07-25");
        Assertions.assertEquals(39, days);
    }

}