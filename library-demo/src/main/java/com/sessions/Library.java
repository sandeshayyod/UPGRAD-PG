package com.sessions;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {


    private List<Book> books = new ArrayList<>();

    public Library() {

        books.add(new Book(1, "The God Of Small Things", "Arunthathi Roy", 432, 9.99));
    }

    Book addToCatalogue(String name, String author, Integer pages, Double price) {

        Integer newId = books.size() + 1;
        Book createdBook = new Book(newId, name, author, pages, price);
        books.add(createdBook);
        return createdBook;
    }

    Book findBookByName(String bookName) {


        for (Book book : books) {

            if (book.getName().equals(bookName)) {
                return book;

            }

        }

        return null;
    }

    void removeFromCatalogue(String bookName) {

        Book bookToBeRemoved = findBookByName(bookName);

        if (null == bookToBeRemoved)
            throw new BookNotFoundException(bookName + " does not exists");

        books.remove(bookToBeRemoved);
    }


    public RentedBook rent(String bookName) {

        Book bookToBeRented = findBookByName(bookName);

        removeFromCatalogue(bookName);

        RentedBook rentedBook = new RentedBook(bookToBeRented);
        return rentedBook;


    }


    //if number of Days is greater than 5 , Each day it will be 1 Dollar
    //else Each day it would be 50 cents
    public Double calculateBookRent(RentedBook rentedBook) {

        long numberOfDays = ChronoUnit.DAYS.between(rentedBook.getRentedDate(), LocalDate.now());

        System.out.println("Rented on " + rentedBook.getRentedDate());

        if (numberOfDays > 5)
            return numberOfDays * 1.0;
        else
            return numberOfDays * 0.5;


    }

    public List<Book> getBooks() {


        return Collections
                .unmodifiableList(books);
    }




    /*public static void main(String args[]) {
        String original = "Sandesh", reverse = "";
        int length = original.length();

        original.chars()
                .

        for (int i = length - 1 ; i >= 0 ; i--)
            reverse = reverse + original.charAt(i);

        System.out.println(reverse);
    }*/

    static void recursive_function(int n)
    {
        System.out.println(n);
        if(n == 0)
            return;
        System.out.println(n);
        recursive_function(n-1);
    }

    public static void main(String args[]) {
        recursive_function(10);
    }

}
