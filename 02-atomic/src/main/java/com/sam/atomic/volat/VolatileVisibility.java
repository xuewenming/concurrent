package com.sam.atomic.volat;

/**
 * @author Mr.xuewenming
 * @title: VolatileVisibility
 * @projectName concurrent
 * @description: 保证其看见性
 * @date 2019/10/3013:31
 */
public class VolatileVisibility {

    /**
     *Volatile:
     * 作用：
     *  1.修饰变量
     *  2.禁止编译器优化，让线程从内存中读/写数据。修改完后立即写回。
     * 使用场景：
     *  1.一个线程写，多个线程读的操作。
     * 注意事项：
     *  volatile是非线程安全的，并不能用来实现线程同步。
     *  保证变量可见性：线程每次读取到的都是最新的值。（都是从内存中读取到的，而不是缓存中）
     *  不能保证原子性。
     *
     */

    //public static Integer num = 10;
    public static volatile Integer num = 10;
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            while (num == 10) {
                // TODO
            }
            System.out.println(Thread.currentThread().getName() + "读取的数字是：" + num);
        };

        for (int i = 0; i < 3; i++) {
            new Thread(runnable).start();
        }

        System.out.println("主线程休眠3秒");
        Thread.sleep(3000);
        System.out.println("主线程休眠结束");
        new Thread(() -> {
            num = 15;
            System.out.println("线程写入完成");
        }).start();

    }
}
