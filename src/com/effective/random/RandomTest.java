package com.effective.random;

import java.util.Random;

/**
 * Created by geekgao on 15-12-14.
 * 如果使用random的方式生成一个随机数，那么生成的随机数有2/3会分布于正整数的前半部分
 * 举着个例子想说明最好使用现有的库，那都是专家写好的
 */
public class RandomTest {
    public static final Random rnd = new Random();

    public static void main(String[] args) {
        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 0;
        for (int i = 0;i < 1000000;i++) {
            if (random(n) < n/2) {
                low++;
            }
        }
        System.out.println("不正确的使用：" + low);

        low = 0;
        for (int i = 0;i < 1000000;i++) {
            if (random2(n) < n/2) {
                low++;
            }
        }
        System.out.println("正确的使用：" + low);
    }

    public static int random(int n) {
        return Math.abs(rnd.nextInt()) % n;
    }

    public static int random2(int n) {
        return Math.abs(rnd.nextInt(n));
    }
}
