package com.sam.concurrent.sync.exclusion;

/**
 * @author Mr.xuewenming
 * @title: TicketSeller
 * @projectName concurrent
 * @description: 监视器
 * @date 2019/10/219:22
 */
public class TicketSeller {
    /**
     * 需求：铁路售票系统，一共100张票，通过四个窗口售卖
     *
     * 1.监视器锁的核心操作： 加锁和释放锁
     * 2.操作流程：在操作共享代码的片段开始和结束枷锁和解锁
     * 3.监视器的作用，确保在同一个时间点上，只有一个线程操作共享区域，即监视器。
     *
     * synchronized关键字，步骤：
     * 1.创建任意类型对象，作为监视器，Monitor
     * 2.使用synchronized代码块（或方法），将监视区域包裹起来
     */

    public static int tickets = 100;
    public static void main(String[] args) {
        //Object obj = new Object();      // 任意类型对象作为监视器
        Runnable runnable = () -> {
            while (true) {
                //synchronized (obj) {   // Monitor：加锁
                synchronized (TicketSeller.class){
                    if (tickets <= 0) {
                        break;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "正在售卖第" + tickets-- + "张票");
                }  // monitorexit:释放锁
            }
        };
        new Thread(runnable,"窗口1" ).start();
        new Thread(runnable,"窗口2" ).start();
        new Thread(runnable,"窗口3" ).start();
        new Thread(runnable,"窗口4" ).start();
    }

}
