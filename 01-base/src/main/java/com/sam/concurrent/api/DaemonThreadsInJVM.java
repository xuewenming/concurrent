package com.sam.concurrent.api;

import java.util.Set;

/**
 * @author Mr.xuewenming
 * @title: DaemonThreadsInJVM
 * @projectName concurrent
 * @description: main线程的守护线程
 * @date 2019/10/1619:22
 */
public class DaemonThreadsInJVM {

    /**
     * main线程的守护线程
     * Monitor Ctrl-Break: 5 - true     Ctrl+C
     * Attach Listener: 5 - true        获取当前运行程序的信息，线程栈，内存映像，系统属性
     * main: 5 - false
     * Finalizer: 8 - true              在垃圾回收之前调用finallize()方法进行清理
     * Signal Dispatcher: 9 - true      信号分发器，给虚拟机发送信号
     * Reference Handler: 10 - true     引用控制器，用于清除引用
     */
    public static void main(String[] args) {
        Set<Thread> threads = Thread.getAllStackTraces().keySet();
        for (Thread thread : threads) {
            System.out.println(thread.getName() + ": " + thread.getPriority() + " - " + thread.isDaemon());
        }
    }

}
