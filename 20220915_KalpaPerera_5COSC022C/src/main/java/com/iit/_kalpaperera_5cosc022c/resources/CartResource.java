/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iit._kalpaperera_5cosc022c.resources;

import com.iit._kalpaperera_5cosc022c.Repository.CartRepository;
import com.iit._kalpaperera_5cosc022c.models.Book;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kalpa
 */
@Path("/customers/{customerId}/cart")
public class CartResource {
    
    @POST
    @Path("/items")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addToCart(@PathParam("customerId") int customerId, Book book) {
        return CartRepository.addToCart(customerId,book.getId());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCustomerCart(@PathParam("customerId") int customerId) {
        return Response.status(Response.Status.OK).entity(CartRepository.getCustomerCart(customerId)).build();
    }
    
    @PUT
    @Path("/items/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBookInCart(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId) {
        return CartRepository.updateBookInCart(customerId, bookId);
    }
    
    @DELETE
    @Path("/items/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteBookInCart(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId) {
        return CartRepository.deleteBookInCart(customerId, bookId);
    }
    
}
