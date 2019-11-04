package com.sam.util.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Mr.xuewenming
 * @title: TourBuddiesBarrier
 * @projectName concurrent
 * @description: 循环屏障
 * @date 2019/11/49:07
 */
public class TourBuddiesBarrier {

    /**
     * 需求：
     * 3个小伙伴一起出游。路线：故宫——奥林匹克公园——香山公园
     * 于某日某时某刻在第一站“故宫”集合，然后自由玩耍；
     * 出发去下一站之前再次集合，直到游玩结束
     *
     * CyclicBarrier:
     *   允许线程相互等待，已达到一个公共障碍点。
     *
     * 底层实现原理：
     *   1. 使用可重入锁ReentrantLock + Condition对象实现同步
     *
     *   2.每次执行await方法时，计数器 - 1
     *
     *   3. 当计数器为0时，重置计数器。唤醒所有的等待线程
     *
     *   4.超时，异常和中断线程。
     *      都会重置计数器，唤醒所有线程
     */

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()-> {
        System.out.println("》》》》》》游玩结束");
    });

    public static void main(String[] args) {
        String[] locatOne = {"天安门","故宫","延禧宫"};
        String[] locatTwo = {"鸟巢", "水立方", "森林公园"};
        String[] locatThree = {"香山东门", "香山寺", "见心斋", "碧云寺"};

        String[][] locats = {locatOne,locatTwo,locatThree};
        Runnable runnable = () -> {

            try {
                String name = Thread.currentThread().getName();
                Random random = new Random();
                for (int placeNum = 0; placeNum < locats.length; placeNum++) {
                    System.out.println(name + "前往第" + (placeNum + 1) + "个景点");

                    String[] locat = locats[placeNum];
                    for (int location = 0; location < locat.length; location++) {
                        int index = random.nextInt(4);
                        Thread.sleep(index * 1000);
                        System.out.println(name + "在" + locat[location] + "景点游玩了" + index +"秒");
                    }

                    System.err.println(name + "在景点集合准备前往》》》》》" + (placeNum + 1) );
                    cyclicBarrier.await();   // 所有的线程在此阻塞
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        System.out.println("小伙伴们出发前往集合点》》》》》》");
        for (int i = 1; i <= 3; i++) {
            new Thread(runnable,i + "").start();
        }
    }
}
