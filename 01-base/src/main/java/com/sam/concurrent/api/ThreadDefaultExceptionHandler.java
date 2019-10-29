package com.sam.concurrent.api;

/**
 * @author Mr.xuewenming
 * @title: ThreadDefaultExceptionHandler
 * @projectName concurrent
 * @description: 线程组自定义异常
 * @date 2019/10/1621:08
 */
public class ThreadDefaultExceptionHandler {

    /**
     * 线程异常处理器处理顺序：
     * 线程  -> 线程组 -> 默认异常处理器 -> 在控制台打印错误信息
     * 自定义异常处理器时，不要遵循线程组的处理方式，，
     * 建议继承ThreadGroup 重写uncaughtException
     */
    public static void main(String[] args) {
        Runnable runnable = () -> {
            Thread thread = Thread.currentThread();
            throw new NullPointerException(thread.getName() + "无敌空指针");
        };

        ThreadGroup group = new ThreadGroup("subGroup");

        Thread t1 = new Thread(runnable, "wtt");
        Thread t2 = new Thread(runnable, "xwm");

        // 给当前线程组安装异常处理器
        //Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler());

        // 给t1安装异常处理器
        t1.setUncaughtExceptionHandler(new ThreadExceptionHandler());


        t1.start();
        t2.start();

    }


    private static class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            // 异常，记录日志。
            System.out.println(t.getName() + "throws exception : " + e.getMessage());
        }
    }



}
