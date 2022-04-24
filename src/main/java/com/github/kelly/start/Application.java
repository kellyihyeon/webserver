package com.github.kelly.start;

import java.util.Random;

public class Application {

    public static void main(String[] args) {

        final Bank bank = new Bank();

        final Runnable task = () -> {
            final Random random = new Random();
            while (bank.getBalance() > 0) {
                final int amountToWithdraw = ((Math.abs(random.nextInt()) % 3) + 1) * 100;

                try {
                    bank.withdraw(amountToWithdraw);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        final Thread thread1 = new Thread(task);
        final Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
    }
}
