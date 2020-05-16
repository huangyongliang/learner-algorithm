package com.hyl.algorithm.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import com.hyl.algorithm.core.intf.Strategy;

/**
 * @author hyl
 * @version v1.0: StrategyTest.java, v 0.1 2020/5/17 3:53 $
 */
@SpringBootTest
public class StrategyTest {

    @Autowired
    private Strategy strategy;


    @Test
    public void test(){

        StopWatch stopWatch = new StopWatch("桶排序");

        stopWatch.start("初始化");
        strategy.init(100);
        stopWatch.stop();
        stopWatch.start("排序执行");
        strategy.strategy();
        stopWatch.stop();
        stopWatch.start("结果集打印");
        strategy.print();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
