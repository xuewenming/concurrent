package com.sam.atomic.reference;

import com.sam.atomic.UserInfo;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Mr.xuewenming
 * @title: ReferenceStampAtomic
 * @projectName concurrent
 * @description: 引用类型原子更新类 - 记录被更新次数
 * @date 2019/10/2920:08
 */
public class ReferenceStampAtomic {

    public static void main(String[] args) throws InterruptedException {

        // 1.首先演示基本操作
        UserInfo userA = new UserInfo("小红", 19);
        AtomicStampedReference<UserInfo> stampedReference = new AtomicStampedReference<>(userA,0);
        System.out.println("初始版本对象 ：" + stampedReference.getReference());
        System.out.println("初始版本号 ： " + stampedReference.getStamp());

        // 2.演示版本戳效果
        Runnable exchangeUserRunner = () -> {
            UserInfo lastUser = userA;
            for (int i = 0; i < 3; i++) {
                UserInfo userB = new UserInfo("小白" + i, i);
                stampedReference.compareAndSet(lastUser, userB, stampedReference.getStamp(), stampedReference.getStamp() + 1);
                lastUser = userB;
            }
        };
        Thread thread1 = new Thread(exchangeUserRunner);
        thread1.start();
        thread1.join();
        System.out.println("线程1修改后的对象 ：" + stampedReference.getReference());
        System.out.println("线程1修改后的版本号 ：" + stampedReference.getStamp());


        // 3.演示改回最初效果
        Runnable changeToRunner = () -> {
            stampedReference.compareAndSet(stampedReference.getReference()
                                                , userA, 3
                                                , 0);
        };

        Thread thread2 = new Thread(changeToRunner);
        thread2.start();
        thread2.join();

        System.out.println("线程2修改回最初的对象 ：" + stampedReference.getReference());
        System.out.println("线程2修改回最初的版本号 ： " + stampedReference.getStamp());


        // 判断是否相等
        System.out.println(stampedReference.getReference() == userA);


    }
}
