package com.sam.atomic.basic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Mr.xuewenming
 * @title: AtomicLong
 * @projectName concurrent
 * @description: 基本类型原子更新类 - Long/Boolean
 * @date 2019/10/2919:05
 */
public class AtomicBasic{

    public static void main(String[] args) {
        java.util.concurrent.atomic.AtomicLong atomicLong = new java.util.concurrent.atomic.AtomicLong(100);
        atomicLong.getAndIncrement();
        atomicLong.getAndIncrement();


        AtomicBoolean atomicBoolean = new AtomicBoolean(Boolean.FALSE);
        atomicBoolean.compareAndSet(Boolean.FALSE, Boolean.TRUE);
        boolean b = atomicBoolean.get();
        System.out.println(b);


    }

}
