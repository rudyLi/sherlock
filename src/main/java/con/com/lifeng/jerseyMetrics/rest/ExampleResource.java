package con.com.lifeng.jerseyMetrics.rest;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by lifeng on 15-1-6.
 */
@Path("/example")
@Produces(MediaType.TEXT_PLAIN)
public class ExampleResource {
    @GET
    @Timed
    public String show() {
        return "yay";
    }
}