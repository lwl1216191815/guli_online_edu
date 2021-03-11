package com.atguigu.eduservice;

import cn.hutool.core.util.StrUtil;
import org.junit.Test;

/**
 * 相关工具类测试
 */
public class UtilTestOne {
    @Test
    public void testOne(){
        String s = StrUtil.emptyToNull("    x        ".trim());
        System.out.println(s.length());
    }

}
