package com.sam.util.countdownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Mr.xuewenming
 * @title: CountDownTest
 * @projectName concurrent
 * @description: 倒计时计数器
 * @date 2019/11/48:59
 */
public class CountDownTest {

    /**
     * 判断线程是否可以重复签到
     * 答案：可以重复性签到
     */

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            synchronized (CountDownTest.class) {
                System.err.println(name  + "开始签到");
                countDownLatch.countDown();
                if (1 == Integer.valueOf(name)) {
                    countDownLatch.countDown();
                    System.out.println( name + "又簽到一次");
                }
            }

        };

        for (int i = 1; i <= 2; i++) {
            new Thread(runnable, i + "").start();
        }

        countDownLatch.await();
        System.out.println("签到完成》》》》》");




    }
}
