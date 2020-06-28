package com.hyl.algorithm.tree;

import com.hyl.algorithm.search.base.SearchIntf;
import com.hyl.algorithm.search.base.SearchIntfFactory;

/**
 * 并查集
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-29 02:59
 */
public class MergeSet implements SearchIntf {

    int[] a;

    Line[] lines;

    @Override
    public void init() {
        a = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        lines = new Line[9];
        lines[0] = new Line(1, 2);
        lines[1] = new Line(3, 4);
        lines[2] = new Line(5, 2);
        lines[3] = new Line(4, 6);
        lines[4] = new Line(2, 6);
        lines[5] = new Line(8, 7);
        lines[6] = new Line(9, 7);
        lines[7] = new Line(1, 6);
        lines[8] = new Line(2, 4);
    }

    @Override
    public void find() {
        for (Line line : lines) {
            merge(line);
        }
    }

    private int getBoss(int i) {
        if (a[i] == i) {
            return i;
        } else {
            return getBoss(a[i]);
        }
    }

    private void merge(Line line) {

        int xBoss = getBoss(line.x);
        int yBoss = getBoss(line.y);

        if (xBoss != yBoss) {
            a[yBoss] = a[xBoss];
        }
    }

    @Override
    public void print() {
        System.out.println("  \t1\t2\t3\t4\t5\t6\t7\t8\t9\t10");
        for (int i:a){
            System.out.print(i+"\t");
        }
        System.out.println();

        int total = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == i) {
                System.out.println("Boss：" + i);
                total++;
            }
        }
        System.out.println("总共有：" + total + "个boss");
    }

    public static void main(String[] args) {
        SearchIntfFactory.produce(MergeSet.class);
    }

    public static class Line {
        int x;
        int y;

        public Line(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
