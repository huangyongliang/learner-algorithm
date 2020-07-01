package com.hyl.algorithm.other;

import java.util.Arrays;

import com.hyl.algorithm.search.base.SearchIntf;
import com.hyl.algorithm.search.base.SearchIntfFactory;

/**
 * 寻找图的割点
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-07-01 05:15
 */
public class CutPoint implements SearchIntf {

    private ArrayGraph graph;
    private int n, m, root, start, timestamp;

    private int[] num;
    private int[] low;
    private int[] book;

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
        graph = new ArrayGraph(n);
        graph.addUndirected(1, 3, 1);
        graph.addUndirected(1, 4, 1);
        graph.addUndirected(2, 3, 1);
        graph.addUndirected(2, 4, 1);
        graph.addUndirected(2, 5, 1);
        graph.addUndirected(2, 6, 1);
        graph.addUndirected(5, 6, 1);
        num = new int[n + 1];
        low = new int[n + 1];
        book = new int[n + 1];

        root = start = 1;
        timestamp = 0;

    }

    @Override
    public void find() {
        dfs(start, root);
    }

    /**
     * 深度优先--割点算法
     * @param cur 当前结点
     * @param father 父结点
     */
    private void dfs(int cur, int father) {

        // 儿子数
        int child = 0;
        // 时间戳累加
        timestamp++;
        // 登记时间戳
        num[cur] = timestamp;
        low[cur] = timestamp;
        // 遍历连通结点
        for (int i = 1; i <= 6; i++) {
            // 判断连通
            if (graph.getPath(cur, i) == 1) {
                // 时间戳等于0表示没有被访问过
                if (num[i] == 0) {
                    // 确定父子关系，儿子数加一
                    child++;
                    // 递归
                    dfs(i, cur);
                    // 递归完成，树已经形成，子结点和当前结点中使用能访问的较小时间戳
                    low[cur] = Math.min(low[cur], low[i]);
                    // 判定是否是割点，如果不是根结点，子结点能访问最小时间戳大于等于当前时间戳
                    if (cur != root && low[i] >= num[cur]) {
                        book[cur] = 1;
                    }
                    // 如果是根结点有两个儿子，就是可以确定是割点
                    if (cur == root && child == 2) {
                        book[cur] = 1;
                    }
                } else if (i != father) {
                    // 没有搜索到就已经有时间戳了，还不是父结点，说明在没有经过父结点就已经访问过，使用最小时间戳
                    low[cur] = Math.min(low[cur], num[i]);
                }
            }
        }

    }

    @Override
    public void print() {
        graph.print();
        System.out.println(Arrays.toString(num));
        System.out.println(Arrays.toString(low));
        System.out.println(Arrays.toString(book));
    }

    public static void main(String[] args) {
        SearchIntfFactory.produce(CutPoint.class);
    }

}
