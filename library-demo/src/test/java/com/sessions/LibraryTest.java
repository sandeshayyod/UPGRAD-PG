package com.sessions;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LibraryTest {

    Library library;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("BeforeAll");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("AfterAll");
    }

    @AfterEach
    public void AfterEach() {
        System.out.println("AfterEach");
    }

    @BeforeEach
    public void beforeEach() {
        library = new Library();
        System.out.println("BeforeEach");
    }

    @Test
    public void the_default_number_of_books_in_library_class_should_be_1_initially() {
        int totalNoOfBooks = library.getBooks().size();
        assertEquals(1, totalNoOfBooks);
    }

    @Test
    public void when_addToCatalogue_is_called_list_should_increment_by_one_and_should_return_new_book() {
        Book newlyCreatedBook = library.addToCatalogue("Discovery of India", "J Nehru", 100, 10d);
        int totalBooks = library.getBooks().size();
        List<Book> availableBooks = library.getBooks();
        assertEquals(2, newlyCreatedBook.getId());
        assertThat(totalBooks, equalTo(2));
        assertThat(availableBooks, hasItem(newlyCreatedBook));
    }

    @Test
    public void findBookByName_called_with_bookname_available_in_library_should_return_book() {
        Book book = library.findBookByName("The God Of Small Things");
        assertNotNull(book);
    }

    @Test
    public void findBookByName_called_with_bookname_not_available_in_library_should_return_null() {
        Book book = library.findBookByName("Invalid Book Name");
        assertNull(book);
    }

}