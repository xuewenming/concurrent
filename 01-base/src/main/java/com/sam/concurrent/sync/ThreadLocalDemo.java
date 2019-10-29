package com.sam.concurrent.sync;

/**
 * @author Mr.xuewenming
 * @title: ThreadLocalDemo
 * @projectName concurrent
 * @description: ThreadLocal 本地线程变量
 * @date 2019/10/2318:29
 */
public class ThreadLocalDemo {

    /**
     * ThreadLocal：让变量在每一个线程中都有一个副本
     *
     *
     * 注意事项：
     * 1.线程中多个ThreadLocal的问题
     * 2.ThreadLocalMap的初始化问题：延时初始化，hash表存储
     * 3.ThreadLocal销毁问题：可能导致内存泄露
     *      内存泄露原因，ThreadLocal过大，ThreadLocal运行时间过长
     *      解决办法，WeakReference;手动删除ThreadLocal对象
     */

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "吃饭";
        }
    };

    public static void main(String[] args) {
        String[] strs = {"小红", "小米", "小白", "小黑"};
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            String index = name.substring(7);
            Integer i = Integer.parseInt(index);
            String str = strs[i];
            String local = threadLocal.get();
            str += local;
            threadLocal.set(str);
            System.out.println(threadLocal.get());
            threadLocal.remove();   // 使用完成之后，手动删除threadLocal，这是一个好的习惯
        };

        for (int i = 0; i < strs.length; i++) {
            new Thread(runnable).start();
        }

    }
}
