package com.sam.atomic.property;

/**
 * @author Mr.xuewenming
 * @title: UserInfo
 * @projectName concurrent
 * @description: TODO
 * @date 2019/10/2919:20
 */
public class UserInfo {
    private String name;
    private Integer age;
    // 原子更新操作
    volatile String hobby;
    volatile int num;

    public UserInfo() {
    }

    public UserInfo(String name, Integer age, String hobby) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }

    public UserInfo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public UserInfo(String name, Integer age, int num) {
        this.name = name;
        this.age = age;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                ", num=" + num +
                '}';
    }
}
