package com.effective.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by geekgao on 15-12-14.
 * 没有同步的变量在不同线程中是不可见的
 */
public class StopThread {
    private static boolean stopRequested;
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!stopRequested) {
                    i++;
                }
                System.out.println(i);
            }
        });

        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(i);
        stopRequested = true;
    }
}
