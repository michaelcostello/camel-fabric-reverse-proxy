package com.redhat.httpgateway;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by ceposta on 5/15/14.
 */
@Path("/some")
public class SomeCxfRsResource {

    @POST
    public String testMe(String payload) {
        return null;
    }

    @GET
    public String getMe(String payload) {
        return null;
    }
}
