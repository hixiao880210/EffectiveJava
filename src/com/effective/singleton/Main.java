package com.effective.singleton;

/**
 * Created by geekgao on 15-11-2.
 */
public class Main {
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.test();
    }
}
