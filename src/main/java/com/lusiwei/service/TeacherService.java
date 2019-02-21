package com.lusiwei.service;

import com.lusiwei.dao.TeacherMapper;
import com.lusiwei.dao.TeacherMapper2;
import com.lusiwei.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private TeacherMapper2 teacherMapper2;
    public Teacher query1() {
        System.out.println("数据源1");
        return teacherMapper.selectByPrimaryKey(1);
    }
    public Teacher query2(){
        System.out.println("数据源2");
        return teacherMapper2.selectByPrimaryKey(1);
    }
}
