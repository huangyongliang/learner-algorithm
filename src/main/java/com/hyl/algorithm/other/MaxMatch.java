package com.hyl.algorithm.other;

import java.util.Arrays;

import com.hyl.algorithm.search.base.SearchIntf;
import com.hyl.algorithm.search.base.SearchIntfFactory;

/**
 * 二分图最大匹配
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-07-01 13:22
 */
public class MaxMatch implements SearchIntf {

    private LinkedGraph graph;

    private int n, m, count;

    private int[] boy, girl;

    @Override
    public void init() {
        n = 3;
        m = 5;
        graph = new LinkedGraph(n, m);
        graph.addDirected(1, 1, 1);
        graph.addDirected(1, 2, 1);
        graph.addDirected(2, 2, 1);
        graph.addDirected(2, 3, 1);
        graph.addDirected(3, 1, 1);

        boy = new int[n + 1];
        girl = new int[n + 1];
    }

    @Override
    public void find() {
        Arrays.fill(girl, 0);
        for (int i = 1; i <= n; i++) {
            findGirlMatchBoy(i);
        }
    }

    private void findGirlMatchBoy(int girl) {
        Arrays.fill(boy, 0);
        if (dfs(girl)) {
            count++;
        }
    }

    private boolean dfs(int u) {
        int index = graph.first[u];

        while (index != -1) {

            if (boy[graph.v[index]] == 0) {
                boy[graph.v[index]] = 1;

                if (girl[graph.v[index]] == 0 || dfs(girl[graph.v[index]])) {
                    girl[graph.v[index]] = u;
                    return true;
                }
            }

            index = graph.next[index];
        }

        return false;
    }

    @Override
    public void print() {
        graph.printLines();
        System.out.println("最大匹配：" + count);
        System.out.println(Arrays.toString(boy));
        System.out.println(Arrays.toString(girl));
    }

    public static void main(String[] args) {
        SearchIntfFactory.produce(MaxMatch.class);
    }

}
