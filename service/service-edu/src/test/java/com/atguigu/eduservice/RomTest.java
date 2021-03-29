package com.atguigu.eduservice;

import java.util.HashSet;
import java.util.Set;

public class RomTest {
    public static void main(String[] args) {
        Set<Integer> red = new HashSet<>();
        while (red.size() < 6){
            Double x = Math.random() * 35 + 1;
            red.add(x.intValue());
        }
        System.out.println("红球："+red);
        Double random = Math.random();
        random = random * 15 + 1;
        System.out.println("蓝球："+random.intValue());
    }
}
