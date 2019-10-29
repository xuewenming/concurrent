package com.sam.concurrent.sync;

/**
 * @author Mr.xuewenming
 * @title: DeadLock
 * @projectName concurrent
 * @description: 死锁
 * @date 2019/10/2318:14
 */
public class DeadLock {

    /**
     * 导致死锁的几种方式：
     * 1.线程相互响应对方持有的锁
     * 2.调用了wati/await方法，没有进行唤醒
     * 3.调用了signal/notfiy方法只能唤醒一个线程，但是该线程被激活后任然不能运行。
     */
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        Runnable runnable1 = ()->{
            synchronized (obj1) {
                System.out.println("获取obj1 》》》请求obj2");
                synchronized (obj2) {
                    System.out.println("获取obj2");
                }
            }
        };

        Runnable runnable2 = ()->{
            synchronized (obj2) {
                System.out.println("获取obj2 》》》请求obj1");
                synchronized (obj1) {
                    System.out.println("获取obj1");
                }
            }
        };

        new Thread(runnable1,"线程1").start();
        new Thread(runnable2,"线程2").start();

    }

}
