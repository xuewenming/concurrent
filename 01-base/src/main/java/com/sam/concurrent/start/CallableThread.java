package com.sam.concurrent.start;

import java.util.concurrent.Callable;

/**
 * @author Mr.xuewenming
 * @title: CallableThread
 * @projectName concurrent
 * @description: 线程启动-实现Callable接口
 * @date 2019/10/2414:41
 */
public class CallableThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "实现Callable接口，重写call()方法";
    }
}
