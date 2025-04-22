
package com.iit._kalpaperera_5cosc022c;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Kalpa
 */
@ApplicationPath("bookstore")
public class BookStoreApplicationConfig extends ResourceConfig {
    public BookStoreApplicationConfig() {
        packages("com.iit._kalpaperera_5cosc022c.resources");
        packages("com.iit._kalpaperera_5cosc022c.Exception");
    }
}
