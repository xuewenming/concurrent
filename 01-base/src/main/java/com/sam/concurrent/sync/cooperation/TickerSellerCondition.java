package com.sam.concurrent.sync.cooperation;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.xuewenming
 * @title: TickerSellerCondition
 * @projectName concurrent
 * @description: 条件线程
 * @date 2019/10/2316:41
 */
public class TickerSellerCondition {

    /**
     * 需求:
     * 铁路售票系统，初始100张票，通过4个窗口售卖完
     * 补充系统放票100张
     * 1.首先，要为我们的锁对象创建一个条件对象
     * 2.然后,让线程在不满足的条件下，等待
     * 3.当条件满足重新激活在等待下的线程
     *
     * 注意事项：
     * 1.Condition对象一般需要与判断条件配合来使用
     * 2.等待集（wait set）： 将线程放到此条件对象的等待集中，该线程将释放锁
     *      await方法执行后，线程释放锁，但不能激活自身，可能导致死锁（dead lock）
     * 3.条件对象的使用，必须在它所属的监视器控制的监视区域内
     * 4.signAll方法仅仅是通知正在等待的线程，现在条件可能满足。
     *      所以需要再次检查业务条件,不建议使用signal方法
     */


    private static Integer tickets = 100;
    private static Lock lock = new ReentrantLock();
    // 创建一个条件对象
    private static  Condition condition = lock.newCondition();

    public static void main(String[] args) {

        Runnable runnable = () -> {
            while (true) {
                lock.lock();            // 加锁
                try {
                    Thread thread = Thread.currentThread();
                    if (tickets <= 0) {
                        System.out.println("线程" + thread.getName() + "正在等待》》》，线程状态" + thread.getState());
                        // 条件不满足，让线程在此等待
                        condition.await();  // 线程1，2，3，4等待
                        continue;
                    }
                    Thread.sleep(10);   // 不会释放锁
                    System.out.println(Thread.currentThread().getName() + "窗口售卖" + tickets--);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();           // 释放锁
                }
            }
        };

        // 4个售票窗口
        new Thread(runnable,"1").start();
        new Thread(runnable,"2").start();
        new Thread(runnable,"3").start();
        new Thread(runnable,"4").start();

        // 做一个定时触发器，当条件满足就进行放票 每月1号，10，15号放票
       Runnable publisher = () -> {
           lock.lock();
           try {
                   if (tickets <= 0) {
                       tickets = 100;
                       // 仅仅通知正在等待的线程，现在条件可能已经满足
                       condition.signalAll();  // 给别的在此条件上等待的线程发信号，必须用同一把锁，进行锁住。否则没有资格进行操作
                   }
           }finally {
               lock.unlock();
           }
       };

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(publisher,"5").start();
    }

}
