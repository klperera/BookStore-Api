/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iit._kalpaperera_5cosc022c.models;


/**
 *
 * @author Kalpa
 */
public class Book {
    
    private int id;
    private String title;
    private int authorId;
    private int ISBN;
    private int publication_year;
    private double price;
    private int stock_quantity;
    
    public Book() {}

    public Book(String title, int authorId, int ISBN, int publication_year, double price) {
        this.title = title;
        this.authorId = authorId;
        this.ISBN = ISBN;
        this.publication_year = publication_year;
        this.price = price;
        this.stock_quantity = 0;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }
    
    
}
