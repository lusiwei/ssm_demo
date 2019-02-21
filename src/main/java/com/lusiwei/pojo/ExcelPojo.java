package com.lusiwei.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class ExcelPojo {
    private String name;
    private Integer age;
    private Date birthday;

}
