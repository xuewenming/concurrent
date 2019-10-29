package com.sam.concurrent.start;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Mr.xuewenming
 * @title: NewThreadTest
 * @projectName concurrent
 * @description: TODO
 * @date 2019/10/2414:47
 */
public class NewThreadTest {

    public static void main(String[] args) {
        SubThread subThread = new SubThread();
        subThread.start();

        RunnableThread runnableThread = new RunnableThread();
        Thread thread = new Thread(runnableThread);
        thread.start();


        CallableThread callableThread = new CallableThread();
        FutureTask<String> futureTask = new FutureTask<>(callableThread);
        Thread fThread = new Thread(futureTask);
        fThread.start();

        try {
            futureTask.get();   //在结果没有返回之前，一直阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
