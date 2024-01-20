package org.main.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Generator {
    private final static long SEED = 1234;
    public static String generate(int initLengh){
        Random random = new Random((long) (SEED*Math.random()));
        int[] ch = new int[initLengh] ;
        return Arrays.stream(ch).map(x -> x = random.nextInt(9)).mapToObj(String::valueOf).reduce((x, y) -> x + "" + y).get();

    }

}
