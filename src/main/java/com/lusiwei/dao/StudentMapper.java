package com.lusiwei.dao;

import com.lusiwei.pojo.Student;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> queryAll();

    Student selectUserByNameAndId(String name, Integer id);
}