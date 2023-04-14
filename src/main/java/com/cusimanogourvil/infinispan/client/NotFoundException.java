package com.cusimanogourvil.infinispan.client;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class NotFoundException extends WebApplicationException {
    public NotFoundException(String message) {
        super(message, Response.Status.NOT_FOUND);
    }
}
