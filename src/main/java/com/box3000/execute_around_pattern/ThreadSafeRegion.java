package com.box3000.execute_around_pattern;

/**
 * Infrastructure Code<br/>
 * Functional interface
 */
@FunctionalInterface
public interface ThreadSafeRegion {
    void apply() throws InterruptedException;
}
