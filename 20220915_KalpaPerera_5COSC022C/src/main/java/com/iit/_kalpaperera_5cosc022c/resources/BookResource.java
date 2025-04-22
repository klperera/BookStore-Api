/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iit._kalpaperera_5cosc022c.resources;


import com.iit._kalpaperera_5cosc022c.Repository.BookRepository;
import com.iit._kalpaperera_5cosc022c.models.Book;
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
@Path("/books") 
public class BookResource {
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addbook(Book book) {
        return BookRepository.addbook(book);
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getallBooks() {
        return Response.status(Response.Status.OK).entity(BookRepository.getallBooks()).build();
        //return BookRepository.getallBooks();
    }
    
    @GET
    @Path("/{id}") 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getbyBookId(@PathParam("id") int id) {
        return Response.status(Response.Status.OK).entity(BookRepository.getbyBookId(id)).build();

    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBookbyId(@PathParam("id") int id, Book book) {
        return Response.status(Response.Status.OK).entity(BookRepository.updateBookbyId(id, book)).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("id") int id) {
        return BookRepository.deleteBook(id);
    }
}

