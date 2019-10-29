package com.sam.concurrent.api;

/**
 * @author Mr.xuewenming
 * @title: ThreadPriority
 * @projectName concurrent
 * @description: 设置线程优先级
 * @date 2019/10/1619:05
 */
public class ThreadPriority {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            Thread thread = Thread.currentThread();
            for (int i = 0; i < 100; i++) {
                System.out.println("线程" + thread.getName() + "-" + i);
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        Thread t4 = new Thread(runnable);
        Thread t5 = new Thread(runnable);

        // 设置优先级,高度依赖于宿主机操作系统
        t1.setPriority(1);
        t2.setPriority(1);
        t4.setPriority(10);
        t5.setPriority(10);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}
