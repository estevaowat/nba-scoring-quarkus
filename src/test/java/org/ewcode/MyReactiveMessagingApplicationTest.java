package org.ewcode;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.quarkus.test.junit.QuarkusTest;

import org.eclipse.microprofile.reactive.messaging.Message;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class MyReactiveMessagingApplicationTest {

    @Inject
    MyReactiveMessagingApplication application;

    @Test
    void test() {
        assertTrue(true);
    }
}
