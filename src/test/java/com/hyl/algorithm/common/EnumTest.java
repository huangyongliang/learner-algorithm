package com.hyl.algorithm.common;

import org.junit.jupiter.api.Test;

/**
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-03 14:47
 */
public class EnumTest {

    @Test
    public void test() {
        // EnumTest.testDFS(1);
        // System.out.println("total :"+EnumTest.total/2);

        // EnumTest.testBooks();
        EnumTest.testOld();
    }

    public static void main(String[] args) {
        EnumTest.testOld();
    }

    private static int[] a = new int[10];
    private static int[] books = new int[10];
    private static int total = 0;

    /**
     * 深度优先 209ms
     *
     * @param step
     */
    private static void testDFS(int step) {

        if (step == 10) {
            if (100 * a[1] + 10 * a[2] + a[3] + 100 * a[4] + 10 * a[5] + a[6] == 100 * a[7] + 10 * a[8] + a[9]) {
                System.out.println("" + a[1] + a[2] + a[3] + "+" + a[4] + a[5] + a[6] + "=" + a[7] + a[8] + a[9]);
                total++;
            }
        }

        for (int i = 1; i <= 9; i++) {

            if (books[i] == 0) {
                a[step] = i;
                books[i] = 1;
                testDFS(step + 1);
                books[i] = 0;
            }

        }
    }

    private static void testBooks() {

        int[] a = new int[10];
        int[] books = new int[10];
        int sum, total = 0;

        for (a[1] = 1; a[1] <= 9; a[1]++) {
            for (a[2] = 1; a[2] <= 9; a[2]++) {
                for (a[3] = 1; a[3] <= 9; a[3]++) {
                    for (a[4] = 1; a[4] <= 9; a[4]++) {
                        for (a[5] = 1; a[5] <= 9; a[5]++) {
                            for (a[6] = 1; a[6] <= 9; a[6]++) {
                                for (a[7] = 1; a[7] <= 9; a[7]++) {
                                    for (a[8] = 1; a[8] <= 9; a[8]++) {
                                        for (a[9] = 1; a[9] <= 9; a[9]++) {

                                            for (int i = 1; i <= 9; i++) {
                                                books[i] = 0;
                                            }
                                            for (int i = 1; i <= 9; i++) {
                                                books[a[i]] = 1;
                                            }
                                            sum = 0;
                                            for (int i = 1; i <= 9; i++) {
                                                sum += books[i];
                                            }

                                            if (sum == 9 && (
                                                a[1] * 100 + a[2] * 10 + a[3] + a[4] * 100 + a[5] * 10 + a[6]
                                                    == a[7] * 100 + a[8] * 10 + a[9])) {
                                                total++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("total:" + total / 2);

    }

    /**
     * 枚举法 2s289ms
     */
    private static void testOld() {
        int a, b, c, d, e, f, g, h, i, total = 0;

        for (a = 1; a <= 9; a++) {
            for (b = 1; b <= 9; b++) {
                for (c = 1; c <= 9; c++) {
                    for (d = 1; d <= 9; d++) {
                        for (e = 1; e <= 9; e++) {
                            for (f = 1; f <= 9; f++) {
                                for (g = 1; g <= 9; g++) {
                                    for (h = 1; h <= 9; h++) {
                                        for (i = 1; i <= 9; i++) {
                                            if (a != b && a != c && a != d && a != e && a != f && a != g && a != h && a != i &&
                                                b != c && b != d && b != e && b != f && b != g && b != h && b != i &&
                                                c != d && c != e && c != f && c != g && c != h && c != i &&
                                                d != e && d != f && d != g && d != h && d != i &&
                                                e != f && e != g && e != h && e != i &&
                                                f != g && f != h && f != i &&
                                                g != h && g != i &&
                                                h != i &&
                                                (a*100+b*10+c+d*100+e*10+f == g*100+h*10+i)
                                            ){
                                                total++;
                                                System.out.println("" + a + b + c + "+" + d + e + f + "=" + g + h + i);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("total:" + total / 2);
    }
}
