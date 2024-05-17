package org.ewcode;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@QuarkusTest
class MyReactiveMessagingApplicationTest {

    @Inject
    MyReactiveMessagingApplication application;

    @Test
    void test() {
        assertTrue(true);
    }
}
