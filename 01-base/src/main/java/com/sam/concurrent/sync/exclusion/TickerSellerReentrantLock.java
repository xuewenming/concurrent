package com.sam.concurrent.sync.exclusion;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.xuewenming
 * @title: TickerSellerReentrantLock
 * @projectName concurrent
 * @description: ReentrantLock
 * @date 2019/10/2313:08
 */
public class TickerSellerReentrantLock {


    /**
     * 使用Lock框架实现同步机制
     * Lock lock = new 子类
     * lock.lock(); // 在监视器区域之前开始加锁
     * try{
     *     // 监视区,临界资源
     * }finally{
     *     lock.unlock();   //在监视区域结束时，释放锁
     * }
     * 锁是可重入的：Reetrant
     *  一个线程可以多次获取它所持有的锁
     *  直到该线程将锁释放之前，其他线程对象都不能对改对象进行加锁操作
     */
    private static Integer tickets = 100;
    private static Lock lock = new ReentrantLock();    // 创建锁对象

    public static void main(String[] args) {
        Runnable runnable = () -> {
            while (true) {
                lock.lock();            // 加锁
                try {
                    method();
                    if (tickets <= 0) {
                        break;
                    }
                    Thread.sleep(5);   // 不会释放锁
                    System.out.println(Thread.currentThread().getName() + "窗口售卖" + tickets--);
                }catch (InterruptedException e) {
                        e.printStackTrace();
                }finally {
                   lock.unlock();           // 释放锁
                }

            }
        };

        new Thread(runnable,"1").start();
        new Thread(runnable,"2").start();
        new Thread(runnable,"3").start();
        new Thread(runnable,"4").start();
    }

    // 将共享资源拿出来
    private static void method() {
        try {
            lock.lock();
            Thread.sleep(2000);
            System.out.println("调用了method对象...");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();   // 同一个线程 - 可重入锁 - 如果不释放，其他线程是不能获取到当先锁。
        }
    }


}
