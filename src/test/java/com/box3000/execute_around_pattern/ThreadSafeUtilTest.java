package com.box3000.execute_around_pattern;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class ThreadSafeUtilTest {


    @Test
    public void simpleCase() {
        StringBuilder sb = new StringBuilder();
        int counter = 0;

        ThreadSafeUtil.withLock(() -> System.out.println("In Lock Util"));
    }

    @Test
    public void parallelCase() {

        final StringBuilder sBuild = new StringBuilder();
        final StringBuffer sBuff = new StringBuffer();

        IntStream.range(0, 100).parallel().forEach((a) -> ThreadSafeUtil.withLock(() -> {
            sBuff.append("<").append(a).append(">\n");
            sBuild.append("<");
            Thread.sleep(50);
            sBuild.append(a);
            Thread.sleep(50);
            sBuild.append(">\n");
        }));
        System.out.println(sBuild);

        assertEquals("Both Strings should be same", sBuff.toString(), sBuild.toString());
    }
}