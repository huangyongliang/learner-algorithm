package com.hyl.algorithm.core.intf;

/**
 * @author hyl
 * @version v1.0: Strategy.java, v 0.1 2020/5/17 3:32 $
 */
public interface Strategy {

    /**
     * 初始化信息
     * @param size 初始化大小
     */
    void init(int size);

    /**
     * 实现策略
     */
    void strategy();


    /**
     * 打印信息
     */
    void print();

}
