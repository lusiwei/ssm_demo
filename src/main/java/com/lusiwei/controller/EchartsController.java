package com.lusiwei.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class EchartsController {
    @GetMapping("getEcharts")
    public Map<String, Object> getEcharts() {
        ArrayList<String> courseList = Lists.newArrayList("语文", "数学", "英语", "理综", "文综");
        ArrayList<Integer> scoreList = Lists.newArrayList(120, 145, 130, 230, 200);
        Map<String, Object> map = Maps.newHashMap();
        map.put("course", courseList);
        map.put("score", scoreList);
        return map;
    }

    @GetMapping("getEcharts2")
    public List getEcharts2() {
        ArrayList<String> courseList = Lists.newArrayList("语文", "数学", "英语", "理综", "文综");
        ArrayList<Integer> scoreList = Lists.newArrayList(120, 145, 130, 230, 200);
        ArrayList<Object> objects = Lists.newArrayList();
        for (int i = 0; i < courseList.size(); i++) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("name", courseList.get(i));
            map.put("value", scoreList.get(i));
            objects.add(map);
        }
        return objects;
    }
}
