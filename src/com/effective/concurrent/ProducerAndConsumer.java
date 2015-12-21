package com.effective.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by geekgao on 15-12-20.
 */
public class ProducerAndConsumer {

    public static void main(String args[]) {
        List sharedQueue = new ArrayList();
        int size = 4;
        Thread prodThread = new Thread(new Producer(sharedQueue, size), "生产者");
        Thread consThread = new Thread(new Consumer(sharedQueue, size), "消费者");
        prodThread.start();
        consThread.start();
    }
}

class Producer implements Runnable {

    private final List sharedQueue;
    private final int SIZE;

    public Producer(List sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        this.SIZE = size;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("生产: " + i);
            try {
                produce(i);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void produce(int i) throws InterruptedException {

        //wait if queue is full

        synchronized (sharedQueue) {
            while (sharedQueue.size() == SIZE) {
                System.out.println("缓冲区满了 [" + Thread.currentThread().getName()
                        + "] 正在等待 , 大小: " + sharedQueue.size());

                sharedQueue.wait();
                System.out.println("生产者 被激活");
            }
        }

        //producing element and notify consumers
        synchronized (sharedQueue) {
            sharedQueue.add(i);
            sharedQueue.notifyAll();
        }
    }
}

class Consumer implements Runnable {

    private final List sharedQueue;

    public Consumer(List sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("消费: " + consume());
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private int consume() throws InterruptedException {
        //wait if queue is empty
        synchronized (sharedQueue) {
            while (sharedQueue.isEmpty()) {
                System.out.println("缓冲区空 [" + Thread.currentThread().getName()
                        + "] 正在等待 , 大小: " + sharedQueue.size());

                sharedQueue.wait();
                System.out.println("消费者 被激活");
            }
        }

        //Otherwise consume element and notify waiting producer
        synchronized (sharedQueue) {
            //消费消费

            //消费完了通知生产者我已经用完啦！空出了一个缓冲块，赶紧的再生产一个
            sharedQueue.notifyAll();
            return (Integer) sharedQueue.remove(0);
        }
    }
}

