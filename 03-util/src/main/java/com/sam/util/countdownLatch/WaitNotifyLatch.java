package com.sam.util.countdownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mr.xuewenming
 * @title: WaitNotifyLatch
 * @projectName concurrent
 * @description: CountDownLatch
 * @date 2019/11/112:36
 */
public class WaitNotifyLatch {
    /**
     * CountDownLatch概念：
     * （1）倒计时计数器
     * （2）允许一个或者多个线程等待，直到一组线程执行完成
     *
     * 需求：
     *  等待所有的子线程运行完成之后，主线程才能继续执行执行
     *
     *  步骤：
     *（1）定义计数器CountDownLatch对象
     *（2）让主线程等待
     *（3）子线程完成任务之后，计数器对象减 1
     */

    private static Integer tickets = 0;
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(15);
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 1000; i++) {
                atomicInteger.incrementAndGet();
                //tickets = tickets +    // 非线程安全
            }
            System.out.println(name + ": " + atomicInteger.get());
            //System.out.println(name + ": " + tickets);

            countDownLatch.countDown();
            System.out.println(name);  // 线程执行countDown以后可以继续执行
            //countDownLatch.countDown(); // 继续执行countDown以后，计数器继续 - 1
        };

        for (int i = 0; i < 15; i++) {
            new Thread(runnable).start();
        }

        // 相当于加强版的join
        try {
            countDownLatch.await();

            //countDownLatch.await(3, TimeUnit.MINUTES); // 线程超时后唤醒主线程

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("最终结果 ： " + tickets);

        System.out.println("最终结果 ： " + atomicInteger.get());


    }


}
