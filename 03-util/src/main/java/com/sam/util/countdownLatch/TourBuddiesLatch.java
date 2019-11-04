package com.sam.util.countdownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author Mr.xuewenming
 * @title: TourBuddiesLatch
 * @projectName concurrent
 * @description:
 * @date 2019/11/113:04
 */
public class TourBuddiesLatch {

    public static void main(String[] args) throws InterruptedException {
        String[] actions = {"吃东西","睡觉","听音乐","玩游戏","聊天"};
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Runnable runnable = () -> {

            try {
                String name = Thread.currentThread().getName();
                Random random = new Random();
                int i = random.nextInt(5);
                Thread.sleep(i * 1000);
                System.out.println(name + "從家到出发点，经过了" + i + "秒");

                countDownLatch.countDown();
                System.out.println(name + "上車開始：" + actions[i]);

                if (Integer.valueOf(name) == 2) {
                    countDownLatch.countDown();
                    System.err.println("小伙伴2又签到了一次");
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        System.out.println("小伙伴准备从家里出发");
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(runnable,i+"").start();
        }

        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.err.println("小伙伴集合完成准备出发》》》》,耗时"  + (end - start) / 1000.0 + "秒" );

    }




}
