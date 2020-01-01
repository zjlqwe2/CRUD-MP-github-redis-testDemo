package com.example.demo;

import com.example.demo.dao.DeptMapper;
import com.example.demo.domain.Dept;
import com.example.demo.dao.ZhaoUserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MPTest {
    @Autowired
    ZhaoUserDao ZhaoUserMapper;

    @Autowired
    DeptMapper deptMapper;



    @Test
    void addUser(){
        Dept dept = new Dept();
        dept.setPid(1);
        dept.setTitle("老大哥");
        dept.setOpen(1);
        dept.setRemark("至高者");
        dept.setAddress("西安");

        deptMapper.insert(dept);

    }


}
