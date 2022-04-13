package com.upgrad.cc.booking.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.upgrad.cc.booking.util.BookingConstants.DATE_FORMAT;

public class BookingUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * returns a random number list with upperbound of 100 and count number of entries in the number list
     *
     * @param count - no of rand numbers to be generated
     * @return list of random numbers as strings
     */
    public static List<String> getRandomNumbers(int count) {
        Random rand = new Random();
        int upperBound = 100;
        List<String> numberList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }
        return numberList;
    }

    /**
     * method to find the difference between the dates
     *
     * @param fromDate - from date of stay
     * @param toDate   - to date of stay
     * @return date difference
     */
    public static int getNoOfDays(String fromDate, String toDate) {
        LocalDateTime from = dateFromString(fromDate);
        LocalDateTime to = dateFromString(toDate);
        return (int) Duration.between(from.toLocalDate().atStartOfDay(), to.toLocalDate().atStartOfDay()).toDays();
    }

    /**
     * method to parse string into java.sql.Date
     *
     * @param dateString in yyyy-MM-dd
     * @return LocalDate - converted date
     */
    public static LocalDateTime dateFromString(String dateString) {
        return LocalDate.parse(dateString, formatter).atStartOfDay();

    }
}
