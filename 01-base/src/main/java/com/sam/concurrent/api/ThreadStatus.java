package com.sam.concurrent.api;

/**
 * @author Mr.xuewenming
 * @title: ThreadStatus
 * @projectName concurrent
 * @description: 线程状态
 * @date 2019/10/1712:44
 */
public class ThreadStatus {

    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Runnable runnable = ()->{
            for (int i = 0; i < 500; i++) {
                System.out.println("子线程运行：" + i);

                if (i == 400) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread subThread = new Thread(runnable);
        System.err.println("子线程状态：" + subThread.getState());

        subThread.start();
        System.err.println("子线程状态：" + subThread.getState());

        while (true) {
            Thread.State state = subThread.getState();
            System.err.println("子线程状:" + state);
            if (state == Thread.State.TIMED_WAITING) {
                subThread.join();
            }

            if (state == Thread.State.TERMINATED) {
                break;
            }

        }



    }

}
