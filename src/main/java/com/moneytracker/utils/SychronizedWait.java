package com.moneytracker.utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SychronizedWait {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void waitHere(long waitTime) {
        lock.lock();
        try {
            condition.await(waitTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }
}
