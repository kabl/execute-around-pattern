package com.box3000.execute_around_pattern;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Infrastructure Code
 */
public final class ThreadSafeUtil {

    private ThreadSafeUtil() {
    }

    private static final Lock lock = new ReentrantLock();

    public static void withLock(ThreadSafeRegion tsr) {

        lock.lock();
        try {
            tsr.apply();
        }catch(InterruptedException ex){
            throw new RuntimeException(ex);
        } finally {
            lock.unlock();
        }
    }
}
