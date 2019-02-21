package com.lusiwei.service;

import com.lusiwei.common.ResponseResult;
import com.lusiwei.pojo.Student;

public interface IStudentService {
    Student queryById();

    ResponseResult insert(Student student);

    ResponseResult queryAll();

    ResponseResult selectUser(String name, Integer id);
}
