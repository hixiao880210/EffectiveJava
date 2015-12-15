package com.effective.Final;

/**
 * Created by geekgao on 15-12-15.
 * 应该尽可能使类的可变性最小
 * 可变性小的类的所有可能状态可以完全分析出来
 * 不可变对象可以任意的进行共享,即使在多线程下也不会出现错误
 * (1)不可被继承
 * (2)不提供改变对象状态的方法
 * (3)所有域都应该是私有的,final的
 * (4)初始化时不直接使用外部提供的引用,必要时进行"保护性拷贝"
 */
public final class FinalTest {
    private final int a = 10;

    private FinalTest() {
    }

    public static void main(String[] args) {

    }
}