package com.sam.atomic.reference;

import com.sam.atomic.UserInfo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Mr.xuewenming
 * @title: ReferenceTypeAtomic
 * @projectName concurrent
 * @description: 引用类型原子更新类
 * @date 2019/10/2920:01
 */
public class ReferenceTypeAtomic {

    public static void main(String[] args) {
        UserInfo userInfo1 = new UserInfo("博学谷", 17);
        UserInfo userInfo2 = new UserInfo("黑马",19);

        /**
         *   private volatile V value;
         *
         *   public final void set(V newValue) {
         *        // 通过set方法将对象赋值给引用变量，后续的CAS操作，也是赋值给变量，对原引用没有什么影响。
         *
         *         value = newValue;
         *     }
         */
        AtomicReference<UserInfo> atomicReference = new AtomicReference<>();
        atomicReference.set(userInfo1);

        atomicReference.compareAndSet(userInfo1, userInfo2);
        System.out.println(userInfo1.getName() + " - " + userInfo1.getAge());
        System.out.println(atomicReference.get().getName() + " - " + atomicReference.get().getAge());
    }

}
