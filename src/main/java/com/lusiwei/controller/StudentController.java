package com.lusiwei.controller;

import com.lusiwei.common.ResponseResult;
import com.lusiwei.dao.StudentMapper;
import com.lusiwei.pojo.Student;
import com.lusiwei.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping("/hello")
    public Student test() {
        return studentService.queryById();
    }
    @RequestMapping("insert")
    public ResponseResult insert(Student student){
        student.setSbirthday(new Date());
        student.setSname("lusiwei");
        return studentService.insert(student);
    }

    @RequestMapping("queryAll")
    public ResponseResult query(){
        return studentService.queryAll();
    }
    @RequestMapping("query")
    public ResponseResult queryOne(String name,Integer id){
        System.out.println(name);
        return studentService.selectUser(name,id);
    }

}
