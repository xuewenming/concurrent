package com.sam.concurrent.sync.cooperation;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.xuewenming
 * @title: ProducerAndConsumer
 * @projectName concurrent
 * @description: 生产者消费者模式
 * @date 2019/10/2318:00
 */
public class ProducerAndConsumer {

    /**
     * 需求：生产者，消费者模式
     * 1.生产线程生产面包
     * 2.消费线程消费面包
     *
     */


    // 食物
    private static Integer food = 0;

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Runnable producer = () -> {
            while (true) {
                lock.lock();
                try {
                    while (true) {
                        if (food <= 0) {
                            System.out.println("消费者线程等待》》》");
                            condition.await();
                            continue;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };






    }
}
