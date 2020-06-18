package com.hyl.algorithm.search;

/**
 * 深度优先
 * <p>
 *
 * <li>时间复杂度 O(n)</li>
 * <li>空间复杂度 O(n)</li>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-05 10:01
 */
public class DepthFirstSearch {

    private int[] a, book;
    private int n;
    private int total;

    public DepthFirstSearch(int n) {
        a = new int[n + 1];
        book = new int[n + 1];
        this.n = n;
        total = 0;
    }

    private void dfs(int step) {

        if (step == n + 1) {
            for (int i = 1; i < a.length; i++) {
                System.out.print(a[i] + "\t");
            }
            System.out.println();
            total++;
        }

        for (int i = 1; i <= n; i++) {
            if (book[i] == 0) {
                a[step] = i;
                book[i] = 1;
                dfs(step + 1);
                book[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(4);
        depthFirstSearch.dfs(1);
        System.out.println("total: " + depthFirstSearch.getTotal());
    }

    public int getTotal() {
        return total;
    }
}
