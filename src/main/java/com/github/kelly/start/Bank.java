package com.github.kelly.start;

import java.util.concurrent.atomic.AtomicBoolean;

public class Bank {

    private int balance = 1000;
    private final AtomicBoolean lock = new AtomicBoolean(false);


    public void withdraw(int amountToWithdraw) throws InterruptedException {
        while (!lock.compareAndSet(false, true));

        if (balance >= amountToWithdraw) {
            Thread.sleep(500);
            balance = balance - amountToWithdraw;
            System.out.printf("%s - %d 만큼 출금 [%d]\n", Thread.currentThread().getName(), amountToWithdraw, balance);
        }
        lock.compareAndSet(true, false);
    }

    public int getBalance() {
        return balance;
    }
}
