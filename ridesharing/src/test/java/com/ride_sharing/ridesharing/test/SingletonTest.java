package com.ride_sharing.ridesharing.test;

import com.ride_sharing.ridesharing.Basic.oop.Singleton;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SingletonTest {
    @Test
    void testSingletonInstance() {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        assertSame(instance1, instance2, "Singleton instances should be the same");
    }
}
