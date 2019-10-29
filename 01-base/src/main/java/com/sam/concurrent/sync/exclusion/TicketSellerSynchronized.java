package com.sam.concurrent.sync.exclusion;

/**
 * @author Mr.xuewenming
 * @title: TicketSellerSynchronized
 * @projectName concurrent
 * @description: 监视器
 * @date 2019/10/2111:40
 */
public class TicketSellerSynchronized {

    /**
     * 对象锁：使用任意类型对象作为同步代码块/方法的监视器
     * 类锁：使用字节码对象作为同步块/方法的监视器
     *
     * 同步代码块:使用synchronized关键字修饰代码块，配合对象锁使用。
     * 同步方法：使用synchronized关键字修饰方法。
     *      普通方法：使用this对象作作为监视器
     *      静态方法：使用类的字节码对象作为监视器
     */
    private static int tickets = 100;
    public static void main(String[] args) {
        Thread[] threads = new Thread[4];
        Runnable runnable = () -> {
            sellTicket();
        };

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnable, "窗口" + (i + 1));
            threads[i].start();
        }

    }

    public synchronized static void sellTicket() {   // 同步方法的监视器是类的字节码对象，TicketSellerSynchronized.class
        //public synchronized void selleTicket();   // 同步方法的锁对象this
        while (true) {
            if (tickets <= 0) {
                break;
            }
            try {
                Thread.sleep(20);
                System.err.println(Thread.currentThread().getName() + "正在售卖" + tickets-- + "张票");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
