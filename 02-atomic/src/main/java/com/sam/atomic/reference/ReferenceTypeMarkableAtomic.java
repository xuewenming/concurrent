package com.sam.atomic.reference;

import com.sam.atomic.UserInfo;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author Mr.xuewenming
 * @title: ReferenceTypeMarkableAtomic
 * @projectName concurrent
 * @description:  引用类型原子更新类 - 关注是否被更改过
 * @date 2019/10/2920:54
 */
public class ReferenceTypeMarkableAtomic {

    public static void main(String[] args) {
        UserInfo userA = new UserInfo("博小姑",18);
        AtomicMarkableReference<UserInfo> markableReference = new AtomicMarkableReference<>(userA,Boolean.FALSE);
        UserInfo userB= new UserInfo("博小姑博小姑",19);

        markableReference.compareAndSet(userA, userB, false, true);
        System.out.println(markableReference.getReference());
        System.out.println(markableReference.isMarked());


    }

}
