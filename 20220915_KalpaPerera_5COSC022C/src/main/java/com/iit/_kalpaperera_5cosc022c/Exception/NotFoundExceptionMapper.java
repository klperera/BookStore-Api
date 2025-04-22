package com.iit._kalpaperera_5cosc022c.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    private static final Logger logger = Logger.getLogger(NotFoundExceptionMapper.class.getName());

    @Override
    public Response toResponse(NotFoundException e) {
        logger.severe(e.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}
