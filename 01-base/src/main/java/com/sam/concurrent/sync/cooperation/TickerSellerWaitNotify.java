package com.sam.concurrent.sync.cooperation;

/**
 * @author Mr.xuewenming
 * @title: TickerSellerWaitNotify
 * @projectName concurrent
 * @description: 同步线程 - wait/notifyAll
 * @date 2019/10/2317:43
 */
public class TickerSellerWaitNotify {

    /**
     * 等待/唤醒机制的标准格式：
     *  等待方：
     *      1.持有锁
     *      2.判断条件是否满足，若不满足线程等待（wait,await）
     *      3.若条件满足，则继续执行业务逻辑代码，直到退出监视区域（释放锁）
     *  唤醒方：
     *      1.持有锁
     *      2.根据业务需要尝试改变条件
     *      3.执行唤醒命令（notifyAll,signalAll等） 唤醒此监视器上所有线程
     *
     */

    private static Integer tickets = 100;
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        Runnable runnable = () -> {
            while (true) {
                Thread thread = Thread.currentThread();
                synchronized (obj) {
                    if (tickets <= 0) {
                        try {
                            System.out.println(thread.getName() + "正在等待》》》》》");
                            obj.wait();
                            continue;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(thread.getName() + "号窗口正在售卖" + tickets--);
                }
            }
        };

        new Thread(runnable, "1").start();
        new Thread(runnable, "2").start();
        new Thread(runnable, "3").start();
        new Thread(runnable, "4").start();

        Runnable publish = () -> {
            synchronized (obj) {
                if (tickets <= 0) {
                    tickets = 100;
                    obj.notifyAll();
                }
            }
        };

        Thread.sleep(1000);

        new Thread(publish, "5").start();

    }
}
