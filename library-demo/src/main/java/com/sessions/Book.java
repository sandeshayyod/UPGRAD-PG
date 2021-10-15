package com.sessions;

public class Book {

    private Integer id;
    private String name;
    private String author;
    private Integer pages;
    private Double price;


    public Book(Integer id, String name, String author, Integer pages, Double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPages() {
        return pages;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                '}';
    }
}
