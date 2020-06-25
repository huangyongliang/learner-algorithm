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
            long start = System.nanoTime();
            searchIntf.init();
            long initEnd = System.nanoTime();
            searchIntf.find();
            long findEnd = System.nanoTime();
            searchIntf.print();
            long printEnd = System.nanoTime();
            System.out.println(
                "初始化时间:\t" + (initEnd -start ) + "ns." + "\n执行时间:\t" + (findEnd - initEnd) + "ns." + "\n打印时间:\t" + (printEnd
                    - findEnd) + "ns.");
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
