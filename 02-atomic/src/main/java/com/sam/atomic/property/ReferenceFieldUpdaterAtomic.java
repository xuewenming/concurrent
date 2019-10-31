package com.sam.atomic.property;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author Mr.xuewenming
 * @title: ReferenceFieldUpdaterAtomic
 * @projectName concurrent
 * @description: 对象属性原子更新类
 * @date 2019/10/309:09
 */
public class ReferenceFieldUpdaterAtomic {

    /**
     * 1.泛型：
     *  AtomicReferenceFieldUpdater<T,V>
     *  T - 要更新的对象类型
     *  V - 要更新的字段类型
     * 2.通过反射机制找到对象属性，然后进行操作
     * 3.检查访问权限：对象更新器与要操作的束胸的访问权限是否匹配
     * 4.必须是volatile关键字修饰的属性
     * 5.要操作的变量不能用static修饰
     * 6.要操作的变量不能用final修饰
     */
    public static void main(String[] args) {
        // 对象原子属性更新类
        AtomicReferenceFieldUpdater<UserInfo,String> fieldUpdater
                = AtomicReferenceFieldUpdater.newUpdater(UserInfo.class,String.class,"hobby");
        UserInfo userA = new UserInfo("小木", 19, "摄影");
        String s = fieldUpdater.get(userA);
        System.out.println(s);

        // 获取并更新，返回的是原来的值
        String andUpdate = fieldUpdater.getAndUpdate(userA, str -> "学习");
        String s1 = fieldUpdater.get(userA);
        System.out.println(s1);


    }




}
