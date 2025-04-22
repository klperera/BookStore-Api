/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iit._kalpaperera_5cosc022c.resources;

import com.iit._kalpaperera_5cosc022c.Repository.CustomerRepository;
import com.iit._kalpaperera_5cosc022c.models.Customer;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author Kalpa
 */
@Path("customers")
public class CustomerResource {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) {
        return CustomerRepository.addCustomer(customer);
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        return CustomerRepository.getAllCustomers();
    }
    
    @GET
    @Path("/{id}") 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getbyCustomerId(@PathParam("id") int id) {
        return Response.status(Response.Status.OK).entity(CustomerRepository.getbyCustomerId(id)).build();
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomerbyId(@PathParam("id") int id, Customer customer) {
        return Response.status(Response.Status.OK).entity(CustomerRepository.updateCustomerbyId(id, customer)).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("id") int id) {
        return CustomerRepository.deleteCustomer(id);
    }
    
}
