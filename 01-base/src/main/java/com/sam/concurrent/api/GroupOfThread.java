package com.sam.concurrent.api;

/**
 * @author Mr.xuewenming
 * @title: GroupOfThread
 * @projectName concurrent
 * @description: 线程组
 * @date 2019/10/1619:29
 */
public class GroupOfThread {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        ThreadGroup threadGroup = thread.getThreadGroup();
        System.out.println(threadGroup.getName());  // main


        // 创建线程时，如果没有指定线程组，那么就属于当前线程组
        Thread t1 = new Thread("sub-Thread");
        ThreadGroup t1Group = t1.getThreadGroup();
        System.out.println(t1Group.getName()); // main

        ThreadGroup subGroup = new ThreadGroup("subGroup");
        Thread t2 = new Thread(subGroup, "subThread-2");
        System.out.println(t2.getThreadGroup().getName());  // subGroup


        // 当前线程组也必须属于其他线程组，在构造器中指定父线程组，如果没有指定则为当前线程所属组
        System.out.println(subGroup.getParent().getName());  //main

        // main线程组的父亲
        System.out.println(threadGroup.getParent().getName()); //system

        Thread.State state = thread.getState();

        //subGroup.setDaemon(Boolean.TRUE); 可以将整个线程组都设置成守护线程

    }

}
