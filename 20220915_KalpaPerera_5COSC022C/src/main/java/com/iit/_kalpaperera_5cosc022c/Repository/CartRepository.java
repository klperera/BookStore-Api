/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iit._kalpaperera_5cosc022c.Repository;

import com.iit._kalpaperera_5cosc022c.Exception.OutOfStockException;
import com.iit._kalpaperera_5cosc022c.models.Book;
import com.iit._kalpaperera_5cosc022c.models.Customer;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Kalpa
 */
public class CartRepository {
    
    private static final Logger logger = Logger.getLogger(CartRepository.class.getName());

    public static Response addToCart(int customerId, int bookId) {
        Book book = BookRepository.getbyBookId(bookId);
        Customer customer = CustomerRepository.getbyCustomerId(customerId);
        if (book.getStock_quantity() <= 0) {
            throw new OutOfStockException("Can't purchase, Book is out of stock");
        }
        return customer.getCart().addtocart(book, customerId, bookId);
    }

    public static List<Book> getCustomerCart(int customerId) {
        Customer customer = CustomerRepository.getbyCustomerId(customerId);
        logger.info("Cart retrieved for customer ID: " + customerId);
        return customer.getCart().getCustomerCart();
    }

    public static Response updateBookInCart(int customerId, int bookId) {
        Customer customer = CustomerRepository.getbyCustomerId(customerId);
        return customer.getCart().updateBookById(bookId, customerId);
    }

    public static Response deleteBookInCart(int customerId, int bookId) {
        Customer customer = CustomerRepository.getbyCustomerId(customerId);
        return customer.getCart().deleteBook(bookId, customerId);
    }
}
