package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excell.SubjectData;
import com.atguigu.eduservice.entity.tree.SubjectTreeNode;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author liuwenlong
 * @since 2021-02-23
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    /**
     * 此处监听器我使用了匿名内部类的形式，将service方法作为构造函数传递过去的形式我个人觉得
     * 很不合适，而且，我并没有使用老师写的那种方式，而是直接new SubjectExcelListener(this)的方式
     * 但是监听器并没有执行，至于为啥我没找到原因，再加上我不喜欢这种构造方法的形式，因此我选择了匿名内部类
     * @param file
     */
    @Override
    public void importSubject(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            ExcelReaderBuilder read = EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(this));
            ExcelReaderSheetBuilder sheet = read.sheet();
            sheet.doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SubjectTreeNode> getSubjectTree() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("parent_id","0");
        List<EduSubject> oneList = baseMapper.selectList(wrapper);
        wrapper = new QueryWrapper();
        wrapper.ne("parent_id", "0");
        List<EduSubject> twoList = baseMapper.selectList(wrapper);
        List<SubjectTreeNode> result = new ArrayList<>(oneList.size());
        for (EduSubject subject : oneList) {
            SubjectTreeNode node = new SubjectTreeNode();
            node.setId(subject.getId());
            node.setLabel(subject.getTitle());
            List<SubjectTreeNode> children = new ArrayList<>();
            //使用迭代器遍历二级列表，这样，每当找出来一个对应的二级对象，就可以把他从
            //二级列表中删除，减少二级列表遍历消耗
            Iterator<EduSubject> iterator = twoList.iterator();
            while (iterator.hasNext()){
                EduSubject t = iterator.next();
                if(subject.getId().equals(t.getParentId())){
                    SubjectTreeNode tNode = new SubjectTreeNode();
                    tNode.setId(t.getId());
                    tNode.setLabel(t.getTitle());
                    children.add(tNode);
                    iterator.remove();//找到之后，在集合中移除，减少遍历
                }
            }
            node.setChildren(children);
            result.add(node);
        }
        return result;
    }
}
