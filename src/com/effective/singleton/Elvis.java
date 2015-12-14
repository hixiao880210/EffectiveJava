package com.effective.singleton;

/**
 * Created by geekgao on 15-11-2.
 * 枚举实现单例
 */
public enum Elvis {
    INSTANCE;

    public void test() {
        System.out.println("你调用了test()!");
    }
}