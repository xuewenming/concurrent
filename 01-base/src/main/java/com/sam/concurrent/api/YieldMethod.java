package com.sam.concurrent.api;

/**
 * @author Mr.xuewenming
 * @title: YieldMethod
 * @projectName concurrent
 * @description: 程序调度，让出CPU的执行权。防止某些线程长时间占用CPU
 * @date 2019/10/1619:44
 */
public class YieldMethod {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            // 子线程任务
            for (int i = 0; i < 1000; i++) {
                // 达到某种条件让出执行权
                if (i % 50 == 0) {
                    System.out.println(Thread.currentThread().getName() + "： 让出执行权" + i);
                    Thread.yield();
                }
            }
        };

        Thread t1 = new Thread(runnable);
        t1.start();

        // 主线程任务
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " :" + i);
        }



    }

}
