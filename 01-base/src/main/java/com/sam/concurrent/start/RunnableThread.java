package com.sam.concurrent.start;

/**
 * @author Mr.xuewenming
 * @title: RunnableThread
 * @projectName concurrent
 * @description: 线程启动-实现Runnable接口
 * @date 2019/10/2414:40
 */
public class RunnableThread implements Runnable {

    @Override
    public void run() {
        System.out.println("实现Runnable接口，重写run()方法");
    }
}
