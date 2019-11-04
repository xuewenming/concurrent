package com.sam.util.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author Mr.xuewenming
 * @title: ParkingSpaceSemaphore
 * @projectName concurrent
 * @description: 信号量
 * @date 2019/11/410:57
 */
public class ParkingSpaceSemaphore {

    /**
     * 并发工具类：Semaphore的使用
     *
     * 需求：假设停车场有3个车位，且都没有停车。先后到来5辆车，
     * 管理员只能允许3辆车停入车位。直到车辆陆陆续续从车场离开，
     * 有了空余车位，后来的车才能停入车位。
     *
     * 是什么：信号量，允许一组集程等待，直到获取许可
     *
     * 做什么：资源控制，连接池
     *
     * 注意事项:
     *  1.锁是由其它的线程释放，而不是主线程
     *  2.获取许可和释放许可，可能不是同一个线程
     *      第一个释放许可，第二个获取许可
     *  3.线程可以申请多个许可，也可以释放多个许可
     *
     *  4.许可初始化线程数量为1，可以当做互斥锁来使用
     *
     *
     */

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                // 入场，请求许可
                semaphore.acquire();
               /* semaphore.acquire();
                semaphore.acquire();*/


                Random random = new Random();
                int second = random.nextInt(5) + 1;
                Thread.sleep(second * 1000);
                System.out.println(name + "车辆在停车场，停留》》》" + second);

                int twoSecond = random.nextInt(10);
                Thread.sleep(twoSecond * 1000);
                // 释放许可
                semaphore.release();
               /* semaphore.release();
                semaphore.release();*/
                System.out.println(name + "车辆离开，停留" + second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 1; i <= 5; i++) {
            new Thread(runnable, i + "").start();
        }

    }
}
