package com.ride_sharing.ridesharing.test;

import com.ride_sharing.ridesharing.Basic.oop.SingletonEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SingletonEnumTest {
    @Test
    void testEnumSingleton() {
        SingletonEnum instance1 = SingletonEnum.INSTANCE;
        SingletonEnum instance2 = SingletonEnum.INSTANCE;

        assertSame(instance1, instance2, "Enum Singleton instances should be the same");
    }
}
