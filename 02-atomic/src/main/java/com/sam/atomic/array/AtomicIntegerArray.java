package com.sam.atomic.array;

/**
 * @author Mr.xuewenming
 * @title: AtomicIntegerArray
 * @projectName concurrent
 * @description: 数组类型原子更新类
 * @date 2019/10/2919:12
 */
public class AtomicIntegerArray {


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,6,8};

        //  array.clone(); 在底层进行原数组拷贝
        java.util.concurrent.atomic.AtomicIntegerArray atomicIntegerArray
                = new java.util.concurrent.atomic.AtomicIntegerArray(arr);

        atomicIntegerArray.compareAndSet(0, 2, 3);


        //atomicIntegerArray.set(0,100);
        int atomic = atomicIntegerArray.get(0);
        int i = arr[0];

        System.out.println(atomic);
        System.out.println(i);

    }

}
