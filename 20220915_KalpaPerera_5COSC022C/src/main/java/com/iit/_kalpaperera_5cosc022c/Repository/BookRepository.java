/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iit._kalpaperera_5cosc022c.Repository;

import com.iit._kalpaperera_5cosc022c.Exception.AuthorNotFoundException;
import com.iit._kalpaperera_5cosc022c.Exception.BadRequestException;
import com.iit._kalpaperera_5cosc022c.Exception.BookNotFoundException;
import com.iit._kalpaperera_5cosc022c.models.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.time.Year;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kalpa
 */
public class BookRepository {
    
    private static final Logger logger = Logger.getLogger(BookRepository.class.getName());
    
    private static List<Book> books = new ArrayList<>();


    public static Response addbook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty() || book.getAuthorId() <= 0 || book.getISBN() <= 0 || book.getPublication_year() <= 0 || book.getPublication_year() > Year.now().getValue()) {
            //return "Invalid values";
            throw new BadRequestException("All details are required and correct");
        }
        AuthorRepository.getbyAuthorId(book.getAuthorId());
        for (Book itrBook : books) {
            if (itrBook.getTitle().equalsIgnoreCase(book.getTitle())) {
                logger.info("Book is already exists.");
                int index = getBookIndexByTitle(book.getTitle());
                books.get(index).setStock_quantity(books.get(index).getStock_quantity() + 1);
                //return "Book is already exists." ;
                logger.info("Book added successfully.");
                return Response.status(Response.Status.CREATED).entity(books.get(index)).build();
            }  
        }
        book.setId(books.size() + 1);
        book.setStock_quantity(1);
        books.add(book);
        logger.info("Book added successfully.");
        //return "Book added successfully";
        return Response.status(Response.Status.CREATED).entity(book).build();
    }
    
    public static List<Book> getallBooks() {
        logger.info("All books fetched successfully.");
        return new ArrayList<>(books);
    }
    
    public static Book getbyBookId(int id) {
        if (id <= 0) {
            //return null;
            throw new BadRequestException("Id can't be zero or minus value");
        }
        int index = getBookIndexById(id);
        if (index == -1) {
            throw new BookNotFoundException("Book not found");
        }
        logger.info("Book retrieved: ID " + id);
        return books.get(index);
    }
    
    public static Book updateBookbyId(int id, Book book) {
        if (id <= 0) {
            throw new BadRequestException("Id can't be zero or minus value");
        }
        int index = getBookIndexById(id);
        if (index == -1) {
            throw new BookNotFoundException("Book not found");
        }
        Book listBook = books.get(index);
        book.setId(id);
        book.setStock_quantity(listBook.getStock_quantity());
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            book.setTitle(listBook.getTitle());
        }
        if (book.getAuthorId() <= 0) {
            book.setAuthorId(listBook.getAuthorId());
        }
        if (book.getISBN() <= 0) {
            book.setISBN(listBook.getISBN());
        }
        if (book.getPublication_year() <= 0 || book.getPublication_year() > Year.now().getValue() ) {
            book.setPublication_year(listBook.getPublication_year());
        }
        if (book.getPrice() <= 0) {
            book.setPrice(listBook.getPrice());
        }
        books.set(index, book);
        logger.info("Book updated: ID " + id);
        return books.get(index);
    }
    
    public static Response deleteBook(int id) {
        if (id <= 0) {
            throw new BadRequestException("Id can't be zero or minus value");
        }
        int index = getBookIndexById(id);
        if (index == -1) {
            throw new BookNotFoundException("Book not found");
        }
        books.remove(index);
        logger.info("Book deleted: ID " + id);
        return Response.noContent().build(); // 204 No Content
    }
    
    public static int getBookIndexByTitle(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title) ) {
                return i;
            }
        }
        return -1;
    }
    public static int getBookIndexById(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    
}
