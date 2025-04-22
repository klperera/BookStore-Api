/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iit._kalpaperera_5cosc022c.Repository;

import com.iit._kalpaperera_5cosc022c.Exception.AuthorNotFoundException;
import com.iit._kalpaperera_5cosc022c.Exception.BadRequestException;
import com.iit._kalpaperera_5cosc022c.Exception.ConflictException;
import com.iit._kalpaperera_5cosc022c.models.Authors;
import com.iit._kalpaperera_5cosc022c.models.Book;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Kalpa
 */
public class AuthorRepository {
    
    private static final Logger logger = Logger.getLogger(AuthorRepository.class.getName());
    
    private static List<Authors> authors = new ArrayList<>();
    
    public static Response addAuthor(Authors author) {
        if (author.getFirstName() == null || author.getFirstName().trim().isEmpty() || author.getLastName() == null || author.getLastName().trim().isEmpty() || author.getBiography() == null || author.getBiography().trim().isEmpty()) {
            //return Response.status(Response.Status.BAD_REQUEST).entity("Invalid values").build();
            //return "Invalid values";
            throw new BadRequestException("Author name or Biography cannot be empty");
        }
        for (Authors itrAuthor : authors) {
            if (itrAuthor.getFirstName().equals(author.getFirstName()) && itrAuthor.getLastName().equals(author.getLastName())) {
                //return "Author is already exists.";
                throw new ConflictException(itrAuthor.getFirstName() + " " + itrAuthor.getLastName());
            }
        }
        author.setId(authors.size() + 1);
        authors.add(author);
        logger.info("Author added successfully.");
        //return "Author added successfully";
        return Response.status(Response.Status.CREATED).entity(author).build();
    }
    
    public static Response getallAuthors() {
        //return authors;
        logger.info("Get all Authors");
        return Response.ok(new ArrayList<>(authors)).build();
    }
    
    public static Response getbyAuthorId(int id) {
        if (id <= 0) {
            throw new BadRequestException("Id can't be zero or minus value");
        }
        int index = getAuthorIndexById(id);
        if (index == -1) {
            //return null;
            throw new AuthorNotFoundException("Author not found");
        }
        //return authors.get(index);
        logger.info("Get Author by ID " + id);
        return Response.status(Response.Status.OK).entity(authors.get(index)).build();
    }
    
    public static Response updateAuthorbyId(int id, Authors author) {
        if (id <= 0) {
            //return null;
            throw new BadRequestException("Id can't be zero or minus value");
        }
        int index = getAuthorIndexById(id);
        if (index == -1) {
            // instead of this send author not found message
            //return null;
            throw new AuthorNotFoundException("Author not found");
        }
        Authors listAuthor = authors.get(index);
        author.setId(id);
        if (author.getFirstName() == null || author.getFirstName().trim().isEmpty()) {
            author.setFirstName(listAuthor.getFirstName());
        }
        if (author.getLastName() == null || author.getLastName().trim().isEmpty()) {
            author.setLastName(listAuthor.getLastName());
        }
        if (author.getBiography() == null || author.getBiography().trim().isEmpty()) {
            author.setBiography(listAuthor.getBiography());
        }
        authors.set(index, author);
        //return authors.get(index);
        logger.info("Author updated successfully.");
        return Response.status(Response.Status.OK).entity(authors.get(index)).build();
    }
    
    public static Response deleteAuthor(int id) {
        if (id <= 0) {
            //return null;
            throw new BadRequestException("Id can't be zero or minus value");
        }
        int index = getAuthorIndexById(id);
        if (index == -1) {
            //return "Invalid ID.";
            throw new AuthorNotFoundException("Author not found");
        }
        authors.remove(index);
        //return "Author removed";
        logger.info("Author deleted successfully.");
        return Response.noContent().build(); // 204 No Content
    }
    
    public static Response getbooksbyAuthor(int id) {
        if (id <= 0) {
            throw new BadRequestException("Invalid author ID");
        }
        int index = getAuthorIndexById(id);
        if (index == -1) {
            //return "Invalid ID.";
            throw new AuthorNotFoundException("Author not found");
        }
        ArrayList<Book> authorBooks = new ArrayList<>();
        for (int i = 0; i < BookRepository.getallBooks().size(); i++) {
            if (BookRepository.getallBooks().get(i).getAuthorId() == id) {
                authorBooks.add(BookRepository.getallBooks().get(i));
            }
        }
        //return authorBooks;
        logger.info("Books retrieved for author ID: " + id);
        return Response.status(Response.Status.OK).entity(authorBooks).build();
    }
    
    private static int getAuthorIndexById(int id) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    
}
