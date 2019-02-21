package com.lusiwei.controller;

import com.lusiwei.pojo.Teacher;
import com.lusiwei.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dataSource")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @RequestMapping("query")
    public Teacher query(){
        return teacherService.query1();
    }
}
