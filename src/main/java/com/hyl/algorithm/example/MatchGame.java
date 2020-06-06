package com.hyl.algorithm.example;

/**
 * 火柴游戏
 * <p>
 * <li>有24根火柴</li>
 * <li>组成 A + B = C 等式</li>
 * <li>总共有多少种适合方式？</li>
 * <br>
 * <h>分析:</h>
 * <li>除去"+"、"="四根，最多可用火柴根数20根。</li>
 * <li>全部用两根组合成"1"，最大数值为1111。使用枚举法，A和B范围在0～1111，C为A+B。判断</li>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-03 16:10
 */
public class MatchGame {

    private int getMatchCount(int x) {
        int sum = 0;
        // 组成0-9数字时，火柴的根数
        int[] numCount = new int[] {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};

        while (x / 10 != 0) {
            //取末尾
            sum += numCount[x % 10];
            x = x / 10;
        }
        //这里x一定是个位数了
        sum += numCount[x];
        return sum;
    }

    private void gameBegin(int matchCount) {

        int a, b, c, total = 0;

        for (a = 0; a <= 1111; a++) {
            for (b = 0; b <= 1111; b++) {

                c = a + b;

                if (getMatchCount(a) + getMatchCount(b) + getMatchCount(c) == matchCount - 4) {
                    total++;
                    System.out.println(a + "(A)+" + b + "(B)=" + c + "(C)");
                }
            }
        }
        System.out.println("total:+" + total);
    }

    public static void main(String[] args) {
        MatchGame matchGame = new MatchGame();

        matchGame.gameBegin(18);
    }

}
