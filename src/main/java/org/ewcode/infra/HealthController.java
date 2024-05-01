package org.ewcode.infra;

import io.smallrye.health.api.AsyncHealthCheck;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("")
public class HealthController {

    @Inject
    private AsyncHealthCheck asyncHealthCheck;

    @GET
    public void ok() {
        asyncHealthCheck.call();
    }
}