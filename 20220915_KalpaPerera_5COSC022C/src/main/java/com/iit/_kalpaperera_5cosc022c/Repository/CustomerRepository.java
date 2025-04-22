/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iit._kalpaperera_5cosc022c.Repository;

import com.iit._kalpaperera_5cosc022c.Exception.BadRequestException;
import com.iit._kalpaperera_5cosc022c.Exception.ConflictException;
import com.iit._kalpaperera_5cosc022c.Exception.CustomerNotFoundException;
import com.iit._kalpaperera_5cosc022c.models.Customer;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 *
 * @author Kalpa
 */
public class CustomerRepository {
    
    private static final Logger logger = Logger.getLogger(CustomerRepository.class.getName());
    
    private static List<Customer> customers = new ArrayList<>();

    public static Response addCustomer(Customer customer) {
        if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty() || customer.getLastName() == null || customer.getLastName().trim().isEmpty() || customer.getEmail() == null || customer.getEmail().trim().isEmpty() || customer.getPassword() == null || customer.getPassword().trim().isEmpty()) {
            throw new BadRequestException("All details are required");
        }
        for (Customer itrCustomer : customers) {
            if (itrCustomer.getFirstName().equalsIgnoreCase(customer.getFirstName()) && itrCustomer.getLastName().equalsIgnoreCase(customer.getLastName())) {
                throw new ConflictException(itrCustomer.getFirstName() + " " + itrCustomer.getLastName());
            }
        }
        customer.setId(customers.size() + 1);
        customers.add(customer);
        logger.info("Customer added successfully.");
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    public static Response getAllCustomers() {
        logger.info("Get all customers.");
        return Response.ok(new ArrayList<>(customers)).build();
    }

    public static Customer getbyCustomerId(int id) {
        if (id <= 0) {
            throw new BadRequestException("Id can't be zero or minus value");
        }
        int index = getCustomerIndexById(id);
        if (index == -1) {
            throw new CustomerNotFoundException("Customer not found, Wrong ID");
        }
        return customers.get(index);
    }

    public static Customer updateCustomerbyId(int id, Customer customer) {
        if (id <= 0) {
            throw new BadRequestException("Id can't be zero or minus value");
        }
        int index = getCustomerIndexById(id);
        if (index == -1) {
            throw new CustomerNotFoundException("Customer not found, Wrong ID");
        }
        Customer listCustomer = customers.get(index);
        customer.setId(id);
        if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
            customer.setFirstName(listCustomer.getFirstName());
        }
        if (customer.getLastName() == null || customer.getLastName().trim().isEmpty()) {
            customer.setLastName(listCustomer.getLastName());
        }
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            customer.setEmail(listCustomer.getEmail());
        }
        if (customer.getPassword() == null || customer.getPassword().trim().isEmpty()) {
            customer.setPassword(listCustomer.getPassword());
        }
        if (customer.getCart() == null) {
            customer.setCart(listCustomer.getCart());
        }
        customers.set(index, customer);
        logger.info("Customer updated successfully.");
        return customers.get(index);
}

    public static Response deleteCustomer(int id) {
        if (id <= 0) {
            throw new BadRequestException("Id can't be zero or minus value");
        }
        int index = getCustomerIndexById(id);
        if (index == -1) {
            throw new CustomerNotFoundException("Customer not found, Wrong ID");
        }
        customers.remove(index);
        return Response.noContent().build(); // 204 No Content

    }
    
    private static int getCustomerIndexById(int id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    
}
