package com.sam.atomic.array;

import com.sam.atomic.UserInfo;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author Mr.xuewenming
 * @title: RefernceAtomicArray
 * @projectName concurrent
 * @description: 数组类型原子更新类
 * @date 2019/10/2919:25
 */
public class RefernceAtomicArray {

    public static void main(String[] args) {
        UserInfo userInfo1 = new UserInfo("小红", 19, "篮球");
        UserInfo userInfo2 = new UserInfo("小黑", 20, "足球");
        UserInfo[] userInfos = {userInfo1,userInfo2};

        //  this.array = Arrays.copyOf(array, array.length, Object[].class);
        AtomicReferenceArray<UserInfo> referenceArray = new AtomicReferenceArray(userInfos);
        UserInfo userInfo3 = new UserInfo("齐天大圣", 50000, "大闹天宫");
        referenceArray.compareAndSet(0, userInfo1, userInfo3);

        System.out.println(referenceArray.get(0));

        System.out.println(userInfos[0]);


    }

}
