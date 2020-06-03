package com.hyl.algorithm.base;

import org.junit.jupiter.api.Test;

/**
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-05-25 16:33
 */
public class JustTest {

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            if ((i * 10 + 3) * 6528 == (30 + i) * 8256){
                System.out.println(i);
                System.out.println("("+i+" * 10 + 3) * 6528 == (30 + "+i+") * 8256");
                System.out.println((i * 10 + 3) * 6528);
            }
        }
    }

}
