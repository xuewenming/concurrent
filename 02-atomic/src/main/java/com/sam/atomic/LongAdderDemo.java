package com.sam.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Mr.xuewenming
 * @title: LongAdderDemo
 * @projectName concurrent
 * @description: TODO
 * @date 2019/10/3013:52
 */
public class LongAdderDemo {

    /**
     * LongAdder:
     * 适用于超高并发量的的运算场景。
     *
     * 将一个变量拆分成多个变量，多个线程操作自己的数据，减少多线程竞争CAS的操作情况，降低发生自旋的概率。
     */

    public static void main(String[] args) throws InterruptedException {
        AtomicLong atomicLong = new AtomicLong();
        LongAdder longAdder = new LongAdder();

        long start = System.currentTimeMillis();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000000; i++) {
                //atomicLong.getAndIncrement();
                longAdder.increment();
            }
        };

        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
        while (Thread.activeCount() > 2) {

        }

        //System.out.println("运算结果是：" + atomicLong.get());
        System.out.println("运算结果是：" + longAdder.sum());
        System.out.println("耗时 ： " + (System.currentTimeMillis() - start));

    }

}
