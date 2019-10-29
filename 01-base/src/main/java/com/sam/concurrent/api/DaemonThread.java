package com.sam.concurrent.api;

/**
 * @author Mr.xuewenming
 * @title: DaemonThread
 * @projectName concurrent
 * @description: 设置守护线程
 * @date 2019/10/1619:14
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            // 守护线程执行的任务
            try {
                for (int i = 0; i < 1000; i++) {
                    System.out.println(Thread.currentThread().getName() + i);
                }
            }finally {
                System.out.println(".....................fianlly");  // 可能无法正常执行
            }
        };

        Thread thread = new Thread(runnable);
        thread.setDaemon(Boolean.TRUE); // 设置为主线程的守护线程
        thread.start();

        Thread.sleep(5);
        System.out.println("用户线程准备退出");
    }

}
