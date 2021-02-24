package com.atguigu.eduservice.excell;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.atguigu.eduservice.excell.listener.ExcelListener;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    @Test
    public void testWrite(){
        String fileName = "E:\\write.xlsx";
        ExcelWriterBuilder write = EasyExcel.write(fileName, DemoData.class);
        ExcelWriterSheetBuilder sheet = write.sheet();
        List<DemoData> list = new ArrayList<>();
        for (int i = 0;i < 10; i++) {
            DemoData d = new DemoData();
            d.setSno(i);
            d.setSname("lucy"+i);
            list.add(d);
        }
        sheet.doWrite(list);
    }
    @Test
    public void testRead(){
        String fileName = "E:\\write.xlsx";
        ExcelReaderBuilder read = EasyExcel.read(fileName, DemoData.class, new ExcelListener());
        ExcelReaderSheetBuilder sheet = read.sheet();
        sheet.doRead();
    }
}
