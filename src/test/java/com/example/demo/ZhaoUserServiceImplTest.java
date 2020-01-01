package com.example.demo;

import com.example.demo.dao.ZhaoUserDao;
import com.example.demo.domain.Zhao_UserDO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ZhaoUserServiceImplTest {



    @Autowired
    ZhaoUserDao ZhaoUserMapper;


    @BeforeEach
    void befor(){
        System.out.println("测试开始");
    }

    @AfterEach
    void after(){System.out.println("测试结束");}

    @Test
    void getTest() {
        long a = 1;
        Zhao_UserDO zhao_UserDO=ZhaoUserMapper.get(a);

        assert zhao_UserDO.getUsername() != null:"zhao_UserDO为空";
        System.out.println(zhao_UserDO.getName());
    }

    @Test
    void saveTest(){
        Zhao_UserDO zhao_UserDO =new Zhao_UserDO();

        String str = "测试1";
        zhao_UserDO.setName(str);
        zhao_UserDO.setUsername("xxxx");
        int a = ZhaoUserMapper.save(zhao_UserDO);
        assert a == 1 : "插入未成功";

        System.out.println("插入数据的Name是:   "+str);

    }

    @Test
    void updateTest(){
        Zhao_UserDO zhao_UserDO =new Zhao_UserDO();
        long id = 135;    //被修改的数据的id
        zhao_UserDO.setUserId(id);


        zhao_UserDO.setUsername("这条数据修改了");
        int a = ZhaoUserMapper.update(zhao_UserDO);
        assert a == 1 : "数据修改成功";

    }

    @Test
    void removeTest(){
        long id = 140;    //被修改的数据的id

        int a =ZhaoUserMapper.remove(id);

        assert a == 1 : "数据删除失败";

        System.out.println("数据删除成功");
    }

}
