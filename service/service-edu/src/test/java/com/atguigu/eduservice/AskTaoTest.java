package com.atguigu.eduservice;


public class AskTaoTest {
    public static void main(String[] args) {
        String[] profession = {"金","木","水","火","土"};
        String[] temp = {"新","旧"};
        String[] outPut = {"力","法"};
        Double random = Math.random();
        int i = random.intValue();
        System.out.println(profession[i*5]);
        System.out.println(temp[i*2]);
        System.out.println(outPut[i*2]);
    }
}
