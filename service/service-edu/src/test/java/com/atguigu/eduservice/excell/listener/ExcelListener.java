package com.atguigu.eduservice.excell.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.excell.DemoData;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<DemoData> {
    /**
     * 逐行读取excel的内容，从第二行开始
     * @param demoData
     * @param analysisContext
     */
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("--------------"+demoData);
    }

    /**
     * 读取完成之后做的事情
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头"+headMap);
    }
}
