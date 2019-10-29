package com.sam.concurrent.stop;

/**
 * @author Mr.xuewenming
 * @title: InterruptThread
 * @projectName concurrent
 * @description: 中断线程
 * @date 2019/10/2415:31
 */
public class InterruptThread {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            Thread thread = Thread.currentThread();
            while (!thread.isInterrupted()) {
                System.out.println("Runnable-Thread");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(100);
        thread.interrupt();  // 让线程的中断状态设置为true，配合isInterrupted使用。

        //thread.stop(); 暴力终止，造成对象不完整。

    }
}
