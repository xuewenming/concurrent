package com.sam.demo;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author Mr.xuewenming
 * @title: DemoServiceImpl
 * @projectName concurrent
 * @description: TODO
 * @date 2019/10/2414:45
 */
public class DemoServiceImpl implements DemoService<String> {
    @Override
    public String call() {
        AtomicReferenceFieldUpdater fieldUpdater = AtomicReferenceFieldUpdater
                .newUpdater(UserInfo.class, String.class, "hobby");
        return null;
    }
}
