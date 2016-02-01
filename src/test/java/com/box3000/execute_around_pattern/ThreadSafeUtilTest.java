package com.box3000.execute_around_pattern;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class ThreadSafeUtilTest {

    @Test
    public void simpleCase() {
        StringBuilder sb = new StringBuilder();

        ThreadSafeUtil.withLock(() -> System.out.println("In Lock Util"));
    }

    @Test
    public void parallelCase() {
        final StringBuffer expected = new StringBuffer();
        final StringBuilder actual = new StringBuilder();

        IntStream.range(0, 100).parallel().forEach(i
                -> ThreadSafeUtil.withLock(() -> {
                    expected.append("<").append(i).append(">\n");

                    actual.append("<");
                    Thread.sleep(50);
                    actual.append(i);
                    Thread.sleep(50);
                    actual.append(">\n");
                }));

        assertEquals(expected.toString(), actual.toString());
    }
}
