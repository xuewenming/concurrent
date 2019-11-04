package com.sam.util.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author Mr.xuewenming
 * @title: ConnectExchanger
 * @projectName concurrent
 * @description: 交换机
 * @date 2019/11/412:20
 */
public class ConnectExchanger {

    /**
     *  需求：两个特务头子约定在某时某地交换身份
     *
     * 是什么：
     *  允许两个线程在要交换的对象准备好时，交换对象.
     *
     */


    public static void main(String[] args) {
        Exchanger<UserInfo> exchanger = new Exchanger<>();

        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println(name + "正在前往交易地点");

                UserInfo userInfo = new UserInfo(name, 19);
                System.out.println(name + "到达交易地点");

                UserInfo exchange = exchanger.exchange(userInfo);
                System.out.println(name + "交易之前我是：" + userInfo + ",交换信息之后："  + exchange);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };

        new Thread(runnable,"线程1").start();
        new Thread(runnable,"线程2").start();

    }



}
