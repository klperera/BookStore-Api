/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iit._kalpaperera_5cosc022c.Repository;

import com.iit._kalpaperera_5cosc022c.Exception.BadRequestException;
import com.iit._kalpaperera_5cosc022c.Exception.NotFoundException;
import com.iit._kalpaperera_5cosc022c.models.Book;
import com.iit._kalpaperera_5cosc022c.models.Customer;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Kalpa
 */
public class OrderRepository {
    
    private static Logger logger = Logger.getLogger(OrderRepository.class.getName());

    public static Response placeOrder(int customerId) {
        Customer customer = CustomerRepository.getbyCustomerId(customerId);
        if (customer.getCart().getCustomerCart().isEmpty()) {
            throw new BadRequestException("Cart is Empty.");
        }
        else {
            return customer.getCart().placeOrders(customerId);
        }
    }

    public static List<Book> getOrders(int customerId) {
        Customer customer = CustomerRepository.getbyCustomerId(customerId);
        logger.info("Fetched all orders for Customer ID: " + customerId);
        return customer.getCart().getOrdersBooks();
    }

    public static Response getOrderById(int customerId, int orderId) {
        // orderId is treated as list index + 1
        Customer customer = CustomerRepository.getbyCustomerId(customerId);
        if (orderId <= 0) {
            throw new BadRequestException("OrderId can't be zero or minus value");
        } else if (orderId > customer.getCart().getOrdersBooks().size()) {
            throw new NotFoundException("Order with ID " + orderId + " not found.");
        }
        List<Book> orders = customer.getCart().getOrdersBooks();
        logger.info("Order fetched: Order ID " + orderId + " for Customer ID: " + customerId);
        return Response.status(Response.Status.OK).entity(orders.get(orderId-1)).build();
    }

    
}
