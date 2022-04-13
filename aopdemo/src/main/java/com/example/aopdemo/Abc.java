package com.example.aopdemo;

import java.util.Arrays;

public class Abc {
    public static void main(String[] args) {
        Arrays.stream(args)
                .forEach(System.out::println);
    }
}
