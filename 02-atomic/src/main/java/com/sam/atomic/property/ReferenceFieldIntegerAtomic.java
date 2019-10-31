package com.sam.atomic.property;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author Mr.xuewenming
 * @title: ReferenceFieldIntegerAtomic
 * @projectName concurrent
 * @description: Integer类型 -
 * @date 2019/10/3013:05
 */
public class ReferenceFieldIntegerAtomic {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<UserInfo> fieldUpdater
                = AtomicIntegerFieldUpdater.newUpdater(UserInfo.class,"num");
        UserInfo userA = new UserInfo("xwm",18,19);
        int i = fieldUpdater.get(userA);
        System.out.println(i);
        System.out.println("--------------------------------");
        int andUpdate = fieldUpdater.getAndUpdate(userA, num -> 10);
        System.out.println(andUpdate);
        System.out.println(fieldUpdater.get(userA));
        System.out.println(userA);
        System.out.println("--------------------------------");
        int andSet = fieldUpdater.getAndSet(userA, 111);
        System.out.println(andSet);
        System.out.println(fieldUpdater.get(userA));
        System.out.println(userA);

    }

}
