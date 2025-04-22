/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iit._kalpaperera_5cosc022c.models;

import com.iit._kalpaperera_5cosc022c.Exception.BookNotFoundException;
import com.iit._kalpaperera_5cosc022c.Repository.BookRepository;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author kalpa
 */
public class Cart {
    
    private List<Book> customerCart = new ArrayList<>();
    private List<Book> ordersBooks = new ArrayList<>();

    private static final Logger logger = Logger.getLogger(Cart.class.getName());

    public List<Book> getCustomerCart() {
        return customerCart;
    }

    public void setCustomerCart(List<Book> customerCart) {
        this.customerCart = customerCart;
    }

    public List<Book> getOrdersBooks() {
        return ordersBooks;
    }

    public void setOrdersBooks(List<Book> ordersBooks) {
        this.ordersBooks = ordersBooks;
    }
    

    public Response addtocart(Book book, int customerId, int bookId) {
        book.setStock_quantity(book.getStock_quantity() - 1);
        customerCart.add(book);
        logger.info("Book ID " + bookId + " added to cart for customer " + customerId);
        return Response.status(Response.Status.CREATED).entity(book).build();

    }
    
    public Response placeOrders(int customerId) {
        List<Book> books = new ArrayList<>(getCustomerCart());
        ordersBooks.addAll(books);
        customerCart.clear();
        logger.info("Order placed successfully by Customer ID: " + customerId);
        return Response.status(Response.Status.OK).entity(books).build();

    }
    
    public Response updateBookById(int bookId, int customerId) {
        for (int i = 0; i < customerCart.size(); i++) {
            if (customerCart.get(i).getId() == bookId) {
                Book updated = BookRepository.getbyBookId(bookId);
                customerCart.set(i, updated);
                logger.info("Book ID " + bookId + " updated in cart for customer ID " + customerId);
                return Response.status(Response.Status.OK).entity(updated).build();
            }
        }
        throw new BookNotFoundException("Book not found in cart");
    }
    
    public Response deleteBook(int bookId, int customerId) {
        for (Book itrBook : customerCart) {
            if (itrBook.getId() == bookId) {
                customerCart.remove(itrBook);
                logger.info("Book ID " + bookId + " removed from cart for customer ID " + customerId);
                return Response.status(Response.Status.NO_CONTENT).build(); // 204 No Content
            }
        }
        throw new BookNotFoundException("Book not found in cart");
    }

}
