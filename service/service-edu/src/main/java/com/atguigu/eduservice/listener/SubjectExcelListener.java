package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excell.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService){
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null){
            throw new GuliException(20001,"文件内容为空");
        }
        EduSubject oneSubject = this.existOneSubject(subjectData.getOneSubjectName());
        if(oneSubject == null){
            oneSubject = new EduSubject();
            oneSubject.setTitle(subjectData.getOneSubjectName());
            oneSubject.setParentId("0");
            this.eduSubjectService.save(oneSubject);
        }
        EduSubject twoSubject = this.existTwoSubject(subjectData.getTwoSubjectName(), oneSubject.getId());
        if(twoSubject == null){
            twoSubject = new EduSubject();
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            twoSubject.setParentId(oneSubject.getId());
            this.eduSubjectService.save(twoSubject);
        }
    }

    /**
     * 判断此一级分类是否存在
     * @param name
     * @return
     */
    private EduSubject existOneSubject(String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject one = this.eduSubjectService.getOne(wrapper);
        return one;
    }

    /**
     * 判断是否存在二级分类
     * @param name
     * @param pId
     * @return
     */
    private EduSubject existTwoSubject(String name,String pId){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pId);
        EduSubject one = this.eduSubjectService.getOne(wrapper);
        return one;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
