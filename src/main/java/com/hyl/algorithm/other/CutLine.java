package com.hyl.algorithm.other;

import java.util.Arrays;

import com.hyl.algorithm.search.base.SearchIntf;
import com.hyl.algorithm.search.base.SearchIntfFactory;

/**
 * 图的割边问题
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-07-01 12:07
 */
public class CutLine implements SearchIntf {

    private LinkedGraph graph;
    private int n, m, start, root, timestamp;
    private int[] num, low;
    private int[][] lines;
    private int size;

    @Override
    public void init() {
        //        图
        //         1
        //      /    \
        //     4      3
        //      \    /
        //        2
        //      /   \
        //     5  -  6
        n = 6;
        m = 7;
        graph = new LinkedGraph(n, m);
        graph.addUndirected(1, 3, 1);
        graph.addUndirected(1, 4, 1);
        graph.addUndirected(2, 3, 1);
        graph.addUndirected(2, 4, 1);
        graph.addUndirected(2, 5, 1);
        graph.addUndirected(5, 6, 1);
        num = new int[n + 1];
        low = new int[n + 1];

        lines = new int[m][2];
        size = 0;
        root = start = 1;
        timestamp = 0;
    }

    @Override
    public void find() {
        dfs(start, root);
    }

    private void dfs(int cur, int father) {

        timestamp++;
        num[cur] = timestamp;
        low[cur] = timestamp;

        int index = graph.first[cur];
        while (index != -1) {
            // 没有被访问过
            if (num[graph.v[index]] == 0) {
                dfs(graph.v[index], cur);
                low[cur] = Math.min(low[cur], low[graph.v[index]]);
                // 相对割点问题，>= 改为 >，表示连父节点也无法返回
                if (low[graph.v[index]] > num[cur]) {
                    lines[size][0] = cur;
                    lines[size][1] = graph.v[index];
                    size++;
                }
            } else if (graph.v[index] != father) {
                // 可以被访问到，并不是父结点
                low[cur] = Math.min(low[cur], num[graph.v[index]]);
            }

            index = graph.next[index];
        }

    }

    @Override
    public void print() {
        graph.printLines();
        System.out.println(Arrays.toString(num));
        System.out.println(Arrays.toString(low));
        for (int i = 0; i < size; i++) {
            System.out.print("(" + lines[i][0] + "," + lines[i][1] + ")\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SearchIntfFactory.produce(CutLine.class);
    }

}
