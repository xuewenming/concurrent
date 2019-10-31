package com.sam.atomic.basic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mr.xuewenming
 * @title: PrimitiveTypeAtomic
 * @projectName concurrent
 * @description: 基本类型原子更新类
 * @date 2019/10/2918:39
 */
public class PrimitiveTypeAtomic {

    //private static int num = 0;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable add = () -> {
            //synchronized (PrimitiveTypeAtomic.class) {
                for (int i = 0; i < 1000; i++) {
                    //num++;
                    //atomicInteger.getAndIncrement(); number++
                    atomicInteger.incrementAndGet();   // ++number
                }

            System.out.println(Thread.currentThread().getName() + ":" + atomicInteger.get());
            //System.out.println(Thread.currentThread().getName() + "：" + num);
            //}
        };

        for (int i = 0; i < 15; i++) {
            Thread thread = new Thread(add);
            thread.start();
        }
        Thread.sleep(3000);

        System.out.println("获取的最终结果：" + atomicInteger);
        //System.out.println("获取的最终结果：" + num);


    }
}
