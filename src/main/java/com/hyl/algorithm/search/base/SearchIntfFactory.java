package com.hyl.algorithm.search.base;

/**
 * SearchIntf工厂
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-25 05:43
 */
public abstract class SearchIntfFactory {

    public static void produce(Class<? extends SearchIntf> intfClass) {
        try {
            SearchIntf searchIntf = intfClass.newInstance();
            long start = System.currentTimeMillis();
            searchIntf.init();
            long initEnd = System.currentTimeMillis();
            searchIntf.find();
            long findEnd = System.currentTimeMillis();
            searchIntf.print();
            long printEnd = System.currentTimeMillis();
            System.out.println(
                "初始化时间:" + (initEnd -start ) + "ms," + "执行时间:" + (findEnd - initEnd) + "ms," + "打印时间" + (printEnd
                    - findEnd) + "ms.");
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
