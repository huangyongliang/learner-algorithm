package com.hyl.algorithm.tree;

import java.util.TreeMap;

/**
 * 红黑树
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-07-01 04:54
 */
public class RedBlackTree {

    public static void main(String[] args) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        treeMap.put("first", 1);
        treeMap.put("second", 2);
        treeMap.put("third", 3);
        System.out.println(treeMap.toString());
        treeMap.clear();
    }
}
