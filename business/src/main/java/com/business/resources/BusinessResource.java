package com.business.resources;

import com.business.model.Frill;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/detail_frill")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BusinessResource {

    @POST
    public Response determine(Frill frill) {
        return Response.status(200).entity(frill).build();
    }
}
