package com.example.demo;

import com.example.demo.dao.ZhaoUserDao;
import com.example.demo.domain.Zhao_UserDO;
import com.example.demo.vo.UserVo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplateObj;


    @Autowired
    ZhaoUserDao ZhaoUserMapper;

    @BeforeEach
    void befor() {
        System.out.println("测试开始");
    }

    @AfterEach
    void after() {
        System.out.println("测试结束");
    }


    @Test
    /**保存string*/
    void T1() {
        stringRedisTemplate.opsForValue().set("k1", "hellow redis");
        String stringValue = stringRedisTemplate.opsForValue().get("k1");
        System.out.println(stringValue);
    }

    /**
     * 保存对象
     */
    @Test
    void T2() {
        long a = 1;
        Zhao_UserDO zhao_UserDO = ZhaoUserMapper.get(a);
        redisTemplateObj.opsForValue().set("k2", zhao_UserDO);
        System.out.println(redisTemplateObj.opsForValue().get("k2").toString());
    }
    @Test
    /**
     * 保存list集合*/
    void t3() {
        UserVo userVo = new UserVo();
        userVo.setOffset(userVo.getLimit() * (userVo.getPage() - 1));
        System.out.println("从第"+userVo.getOffset()+"位开始,"  +"查询"+ userVo.getLimit()+"个数据");
        System.out.println(userVo.getOffset() + "到" + userVo.getLimit());
        List<Zhao_UserDO> list = ZhaoUserMapper.list(userVo);
        redisTemplateObj.opsForValue().set("UserList", list);
        List list1 = (List) redisTemplateObj.opsForValue().get("UserList");
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i).toString());
        }
    }
    @Test
    /**
     * 保存redis,保存对象,并且序列化*/
    void t4() {
        Zhao_UserDO zhao_UserDO = new Zhao_UserDO();
        redisTemplateObj.opsForValue().set("k3...。。。", zhao_UserDO);
    }
    @Test
    /**
     *测试一种分页缓存,录入方式
     * */

    void t5() {
        UserVo userVo = new UserVo();
        int Listpage = 2;

        userVo.setPage(Listpage);
        userVo.setOffset(userVo.getLimit() * (userVo.getPage() - 1));
        System.out.println("从第"+userVo.getOffset()+"位开始,"  +"查询"+ userVo.getLimit()+"个数据");
        System.out.println(userVo.getOffset() + "到" + userVo.getLimit());

        List<Zhao_UserDO> list = ZhaoUserMapper.list(userVo);
        String UserList = "userList" + Listpage;
        redisTemplateObj.opsForValue().set(UserList, list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

    }



}
