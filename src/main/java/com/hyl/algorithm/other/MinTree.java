package com.hyl.algorithm.other;

import com.hyl.algorithm.search.base.SearchIntf;
import com.hyl.algorithm.search.base.SearchIntfFactory;

/**
 * 图的最小生成树
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-29 04:16
 */
public class MinTree implements SearchIntf {

    private MyMap myMap;

    private MyMap.Line[] result;

    @Override
    public void init() {
        myMap = new MyMap(6);
        myMap.addLine(1, 2, 1);
        myMap.addLine(2, 3, 6);
        myMap.addLine(1, 3, 2);
        myMap.addLine(2, 4, 11);
        myMap.addLine(3, 4, 9);
        myMap.addLine(3, 5, 13);
        myMap.addLine(4, 5, 7);
        myMap.addLine(4, 6, 3);
        myMap.addLine(5, 6, 4);
        result = new MyMap.Line[6];
    }

    @Override
    public void find() {
        myMap.setOrderLine();

        // Kruskal算法核心部分
        int count = 0;
        for (int i = 0; i < myMap.size; i++) {
            MyMap.Line line = myMap.lines[i];
            if (myMap.merge(line.x, line.y)) {
                result[count] = line;
                count++;
            }
            if (count == myMap.size - 1) {
                break;
            }

        }

    }

    @Override
    public void print() {
        for (int i = 0; i < 5; i++) {
            System.out.println(result[i]);
        }
    }

    public static void main(String[] args) {
        SearchIntfFactory.produce(MinTree.class);
    }
}
