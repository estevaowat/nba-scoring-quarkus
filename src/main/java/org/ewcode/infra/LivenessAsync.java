package org.ewcode.infra;

import io.smallrye.health.api.AsyncHealthCheck;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import java.time.Duration;

@Liveness
@ApplicationScoped
@Default
public class LivenessAsync implements AsyncHealthCheck {

    @Override
    public Uni<HealthCheckResponse> call() {
        return Uni.createFrom()
                .item(HealthCheckResponse.up("liveness-reactive"))
                .onItem()
                .delayIt().by(Duration.ofSeconds(10));
    }
}