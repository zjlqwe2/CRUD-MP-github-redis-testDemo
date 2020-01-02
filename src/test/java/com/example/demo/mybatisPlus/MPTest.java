package com.example.demo.mybatisPlus;

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
    void addDept(){
        Dept dept = new Dept();
        dept.setPid(1);
        dept.setTitle("老大哥");
        dept.setOpen(1);
        dept.setRemark("至高者");
        dept.setAddress("西安");
        int count = deptMapper.insert(dept);
        if (count>0){
            System.out.println("添加成功");
        }else {
            System.out.println("新增失败");
        }
    }

    @Test
    void addUser(){}




}
