/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iit._kalpaperera_5cosc022c.resources;

import com.iit._kalpaperera_5cosc022c.Repository.AuthorRepository;
import com.iit._kalpaperera_5cosc022c.models.Authors;
import com.iit._kalpaperera_5cosc022c.models.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
@Path("/authors")
public class AuthorResource {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAuthor(Authors author) {
        return AuthorRepository.addAuthor(author);
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getallAuthors() {
        return AuthorRepository.getallAuthors();
    }
    
    @GET
    @Path("/{id}") 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getbyAuthorId(@PathParam("id") int id) {
        return AuthorRepository.getbyAuthorId(id);
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAuthorbyId(@PathParam("id") int id, Authors author) {
        return AuthorRepository.updateAuthorbyId(id, author);
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAuthor(@PathParam("id") int id) {
        return AuthorRepository.deleteAuthor(id);
    }
    
    @GET
    @Path("/{id}/books")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getbooksbyAuthor(@PathParam("id") int id) {
        return AuthorRepository.getbooksbyAuthor(id);
    }
    
}
