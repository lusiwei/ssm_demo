package com.lusiwei.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lusiwei.common.ResponseResult;
import com.lusiwei.dao.StudentMapper;
import com.lusiwei.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentService implements IStudentService{
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student queryById(){
        Student student = studentMapper.selectByPrimaryKey(1);
        return student;
    }

    @Override
    public ResponseResult insert(Student student) {
        log.info("插入前学生的学号为：{}",student.getSid());
        int insert = studentMapper.insert(student);
        log.info("插入后学生的学号为：{}", student.getSid());
        if (insert < 0) {
            return ResponseResult.fail("添加学生失败");
        }
        return ResponseResult.success("添加学生成功");
    }

    /**
     * 分页
     * @return
     */
    @Override
    public ResponseResult queryAll() {
        PageHelper.startPage(0, 1);
        List<Student> students = studentMapper.queryAll();
        PageInfo<Student> studentPageInfo = new PageInfo<>(students);
        return ResponseResult.success(studentPageInfo.getList());
    }
    /**
     *
     */
    @Override
    public ResponseResult selectUser(String name,Integer id){
        Student student=studentMapper.selectUserByNameAndId(name,id);
        return ResponseResult.success(student);
    }
}
