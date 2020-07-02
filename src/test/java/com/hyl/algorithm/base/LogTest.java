package com.hyl.algorithm.base;

import java.text.NumberFormat;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * log计算
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-07-02 06:23
 */
@Slf4j
public class LogTest {

    @Test
    public void test(){

        double n = 10000000;

        System.out.println(formatDouble(n*n));
        System.out.println(formatDouble(this.log(n,2)*n));

    }

    public double log(double d,double n ){

        return Math.log(d)/Math.log(n);
    }

    private static String formatDouble(double d) {
        NumberFormat nf = NumberFormat.getInstance();
        //设置保留多少位小数
        nf.setMaximumFractionDigits(20);
        // 取消科学计数法
        nf.setGroupingUsed(false);
        //返回结果
        return nf.format(d);
    }
}
