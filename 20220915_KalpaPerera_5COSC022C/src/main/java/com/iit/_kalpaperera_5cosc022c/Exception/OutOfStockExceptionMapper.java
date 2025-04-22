package com.iit._kalpaperera_5cosc022c.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class OutOfStockExceptionMapper implements ExceptionMapper<OutOfStockException> {

    private static final Logger logger = Logger.getLogger(OutOfStockExceptionMapper.class.getName());

    @Override
    public Response toResponse(OutOfStockException e) {
        logger.severe(e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
