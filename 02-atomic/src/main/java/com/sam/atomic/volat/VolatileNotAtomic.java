package com.sam.atomic.volat;

/**
 * @author Mr.xuewenming
 * @title: VolatileNotAtomic
 * @projectName concurrent
 * @description: Volatile不保证原子性
 * @date 2019/10/3013:39
 */
public class VolatileNotAtomic {

    /**
     * volatile关键字不能保证其原子性
     */

    public static volatile Integer num = 0;
    public static void main(String[] args) {

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                num++;  // 非原子性操作
            }
            System.out.println("num : " + num);
        };

        for (int i = 0; i < 15; i++) {
            new Thread(runnable).start();
        }
    }

}
