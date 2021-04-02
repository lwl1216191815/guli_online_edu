package com.atguigu.eduservice;

import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 相关工具类测试
 */
public class UtilTestOne {
    @Test
    public void testOne(){
        String s = StrUtil.emptyToNull("    x        ".trim());
        System.out.println(s.length());
    }

    @Test
    public void testLong(){
        double a = 5l;
        double b = 2l;
        System.out.println(a/b);
    }
    @Test
    public void testDate(){
        //long x = 1617330929915l;
        long x =1617256678000l;
        Date date = new Date(x);
        DateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        DateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = df.format(date);
        String s2 = df1.format(date);
        System.out.println(s);
        System.out.println(s2);
    }
}
