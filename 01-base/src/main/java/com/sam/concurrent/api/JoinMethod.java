package com.sam.concurrent.api;

/**
 * @author Mr.xuewenming
 * @title: JoinMethod
 * @projectName concurrent
 * @description: 将子线程插队到主线程
 * @date 2019/10/1619:52
 */
public class JoinMethod {

    /**
     * 模拟开发场景：本应用对第三开放公共接口，并且要求同步，实时返回
     * 1.使用main线程调用主业务
     * 2.由于业务比较复杂，当第三方应用访问接口，启动子线程去执行
     * 3.接口要求实时返回，将子线程插队到主线程中
     */
    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            // 接口业务
            System.out.println("接口任务开始");
            for (int i = 0; i < 1000; i++) {
                System.out.println("接口任务 " + i);
            }
            System.out.println("接口任务完成");
        };

        Thread thread = new Thread(runnable);

        // 主业务
        for (int i = 0; i < 100; i++) {
            System.out.println("主线程业务 ： " + i);
            if (i == 50) {  // 接口被访问
                thread.start();
                thread.join();  //子线程插队到主线程中， 让主线程进行自旋。
                System.out.println("主线程业务继续执行");
            }
        }
    }
}
// thread.join()
// 让主线程陷入等待，如何开始重新执行主线程的？JVM在线程退出时会设置一个终止状态。 thread.cpp 1762行.设置isAlive为false,并将join方法结束
// 通过Object.wait() 和Object.notifAll() 配合isAlive()实现被插队线程的等待和唤醒