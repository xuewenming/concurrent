package com.sam.concurrent.start;

/**
 * @author Mr.xuewenming
 * @title: SubThread
 * @projectName concurrent
 * @description: 继承Thread
 * @date 2019/10/2414:39
 */
public class SubThread extends Thread {
    @Override
    public void run() {
        System.out.println("继承Thread，重写run()方法");
    }
}
