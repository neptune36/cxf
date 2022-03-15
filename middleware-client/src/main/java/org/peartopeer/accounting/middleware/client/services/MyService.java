package org.peartopeer.accounting.middleware.client.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.peartopeer.accounting.middleware.client.dto.User;

@Produces(MediaType.APPLICATION_JSON)
public interface MyService {

    @Path("users/{userId}")
    @GET
    User getUserById(@PathParam("userId") Long userId);

    @Path("users/create")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    User createUser(@FormParam("name") String name);

}
