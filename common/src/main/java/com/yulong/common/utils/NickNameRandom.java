package com.yulong.common.utils;

import java.util.Random;

public class NickNameRandom {
    private static Random ran = new Random();
    private final static int delta = 0x9fa5 - 0x4e00 + 1;

    public static char getRandomNickName() {
        return (char) (0x4e00 + ran.nextInt(delta));
    }

    public static void main(String[] args) {
        System.out.println(getRandomNickName() + "" + getRandomNickName() + "" +  getRandomNickName());
    }
}
