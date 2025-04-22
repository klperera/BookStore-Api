/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iit._kalpaperera_5cosc022c.resources;

import com.iit._kalpaperera_5cosc022c.Repository.OrderRepository;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kalpa
 */
@Path("/customers/{customerId}/orders")
public class OrderResource {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeOrder(@PathParam("customerId") int customerId) {
        return OrderRepository.placeOrder(customerId);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getOrders(@PathParam("customerId") int customerId) {
        return Response.status(Response.Status.OK).entity(OrderRepository.getOrders(customerId)).build();
    }
    
    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("customerId") int customerId, @PathParam("orderId") int orderId ) {
        return OrderRepository.getOrderById(customerId, orderId);
    }
    
}
