package com.upgrad.hirewheels;

import com.upgrad.hirewheels.dao.FuelTypeDao;
import com.upgrad.hirewheels.dao.LocationDao;
import com.upgrad.hirewheels.dao.VehicleDao;
import com.upgrad.hirewheels.dao.VehicleSubCategoryDao;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class HireWheelsApplication implements CommandLineRunner {


    @Autowired
    InitService initService;

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    VehicleSubCategoryDao vehiclesubcategoryDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    FuelTypeDao fueltypeDao;

    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    BookingService bookingService;

    @Autowired
    VehicleService vehicleService;

    public static void main(String[] args) {
        SpringApplication.run(HireWheelsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initService.start();
        userService.createUser("Sandesh", "Ayyod", "sayyod123", "sandesh.ayyod@abc.com",
                "8105232606", 1000.00f, 2);
        User loggedInUser = userService.getUser("sandesh.ayyod@abc.com", "sayyod123");
        System.out.println("Logged in with "+ loggedInUser);

        User failUser1 = userService.getUser("nishanth.ayyod@abc.com", "nayyod123");
        System.out.println("Log in with invalid email "+ failUser1);

        User failUser2 = userService.getUser("sandesh.ayyod@abc.com", "nayyod123");
        System.out.println("Log in with invalid password "+ failUser2);

        Vehicle mercedes = new Vehicle("AMG", "IND 345", "Black", 1,
                "https://www.mercedes-amg.com/en/world-of-amg/news/press-information/mercedes-amg-gt-black-series-record-lap.html",
                vehiclesubcategoryDao.findById(2).get(), locationDao.findById(1).get(), fueltypeDao.findById(1).get());
        mercedes = adminService.registerVehicle(mercedes);
        System.out.println("Vehicle registered in hirewheels "+ mercedes);
        Vehicle bmw = new Vehicle("3-series", "IND 346", "White", 1,
                "https://www.bmw.in/en/all-models/3-series/sedan/2019/bmw-3-series-sedan-inspire.html",
                vehiclesubcategoryDao.findById(2).get(), locationDao.findById(1).get(), fueltypeDao.findById(1).get());
        bmw = adminService.registerVehicle(bmw);
        System.out.println("Vehicle registered in hirewheels "+ bmw);

        System.out.println("list of All vehicles");
        vehicleService.getAllVehicles()
                .forEach(System.out::println);

        System.out.println("list of Available vehicles");
        vehicleService.getAvailableVehicles()
                .forEach(System.out::println);

        Booking booking = new Booking();
        booking.setUser(loggedInUser);
        booking.setBookingDate(LocalDateTime.now());
        booking.setDropoffDate(LocalDateTime.now());
        booking.setLocation(locationDao.findById(1).get());
        booking.setAmount(200);
        booking.setPickupDate(LocalDateTime.now());
        booking.setVehicle(bmw);
        bookingService.addBooking(booking);

        bmw = adminService.changeAvailability(bmw.getVehicleId(), 0);
        System.out.println("Availability changed for "+ bmw);

        System.out.println("list of All vehicles");
        vehicleService.getAllVehicles()
                .forEach(System.out::println);

        System.out.println("list of Available vehicles");
        vehicleService.getAvailableVehicles()
                .forEach(System.out::println);


    }
}
