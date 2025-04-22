package com.iit._kalpaperera_5cosc022c.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class ConflictExceptionMapper implements ExceptionMapper<ConflictException> {

    private static final Logger logger = Logger.getLogger(ConflictExceptionMapper.class.getName());


    @Override
    public Response toResponse(ConflictException e) {
        logger.severe(e.getMessage() + " already exists.");
        return Response.status(Response.Status.CONFLICT).entity(e.getMessage() + " already exists.").build();
    }
}
