package com.hyl.algorithm.core.impl;

import org.springframework.stereotype.Component;

import com.hyl.algorithm.core.intf.Strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * <h>桶排序</h>
 * <li>时间复杂度：O(M+N)</li>
 * @author hyl
 * @version v1.0: BucketStrategy.java, v 0.1 2020/5/17 3:38 $
 */
@Component
@Slf4j
public class BucketStrategy implements Strategy {

    /**
     * 数据源
     */
    private int[] date;

    /**
     * 桶
     */
    private int[] bucket;

    @Override
    public void init(int size) {

        date = new int[size];
        bucket = new int[size];

        for (int i = 0; i < size; i++) {
            double random = Math.random() * size;
            date[i] = (int) random;
        }

    }

    @Override
    public void strategy() {

        for (int value :date){
            bucket[value]++;
        }

    }

    @Override
    public void print() {

        System.out.println("初始化数据：");
        for (int value : date) {
            System.out.print(value + "\t");
        }
        System.out.println();

        System.out.println("排序以后：");
        //m+n
        for (int index = 0; index < bucket.length; index++) {
            if (bucket[index] == 0) {
                continue;
            }
            for (int count = 0; count < bucket[index]; count++) {
                System.out.print(index + "\t");
            }
        }
        System.out.println();
    }
}
