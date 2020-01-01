package com.example.demo;

import com.example.demo.dao.ZhaoUserDao;
import com.example.demo.domain.Zhao_UserDO;
import com.example.demo.vo.UserVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

@SpringBootTest
public class RedisTest_2 {
    @Autowired
    private RedisTemplate<String, Object> redisTemplateObj;
    @Autowired
    ZhaoUserDao ZhaoUserMapper;
    @Test
    /**
     *测试一种分页缓存,录入方式
     * */
    void t5() {
        UserVo userVo = new UserVo();
        int Listpage = 2;

        userVo.setPage(Listpage);
        userVo.setOffset(userVo.getLimit() * (userVo.getPage() - 1));
        System.out.println("从第" + userVo.getOffset() + "位开始," + "查询" + userVo.getLimit() + "个数据");
        System.out.println(userVo.getOffset() + "到" + userVo.getLimit());

        List<Zhao_UserDO> list = ZhaoUserMapper.list(userVo);
        String UserList = "userList" + Listpage;
        redisTemplateObj.opsForValue().set(UserList, list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

    }
    @Test
    /**
     *测试一种分页查询数据缓存方式,判断缓存中是否有该页key字段,如果没有就添加,如果有就直接读取
     * 判断这种方式是否成立
     * voUser:封装user表数据,分页查询的参数
     * */
    void t6() {
        UserVo userVo = new UserVo();
        userVo.setPage(3);//分页的第几页
        userVo.setOffset(userVo.getLimit() * (userVo.getPage() - 1));
        System.out.println("从第" + userVo.getOffset() + "位开始," + "查询" + userVo.getLimit() + "个数据");
        String UserListKey = "userList" + userVo.getPage();
        if (redisTemplateObj.opsForValue().get(UserListKey) == null) {
            //为空的话向redis中存查出来的数据
            System.out.println("缓存为空,查询的页数为" + +userVo.getPage());
            redisTemplateObj.opsForValue().set(UserListKey, ZhaoUserMapper.list(userVo));
        } else {
            System.out.println("从缓存中取数据,查询的页数为" + +userVo.getPage());
            //返回redis缓存中的数据
            redisTemplateObj.opsForValue().get("UserListKey");
        }
    }
    /**
     * 测试:
     * 批量删除字段相同的key
     * 删除单个key的方法
     */
    @Test
    void testRedisDelete() {
        redisTemplateObj.delete("k2");
        Set<String> keys = redisTemplateObj.keys("userList" + "*");
        redisTemplateObj.delete(keys);
    }
    /**
     * sortSet的读写操作
     */
    @Test
    void sortSetTest() {
        //将sortSet存入redis中
        //
        String str = "sortSetKey1";
        Set<Object> set = new HashSet<>();
        set.add("setKey1");
        set.add("setKey2");
        set.add("setKey3");
        int i = 1;
        for (Object object : set) {
            i++;
            redisTemplateObj.opsForZSet().add(str, object, i);
        }
    }
    /**
     * 是list直接存入数据
     */
    @Test
    void sortSetTest_3() {
        String str = "User表的所有数据";
        UserVo userVo = new UserVo();
        List<Zhao_UserDO> Userlist = ZhaoUserMapper.list(userVo);
        for (int i = 0; i < Userlist.size(); i++) {
            redisTemplateObj.opsForZSet().add(str, Userlist.get(i), i);
        }
    }

    /**
     * 从redis里面分页取数据
     */
    @Test
    void sortSetTest_4(){
        String str = "User表的所有数据";
        //按照分页条件获取缓存
        Set<Object> UserSet=redisTemplateObj.opsForZSet().range(str,0,4);
        List <Zhao_UserDO> userList  = new ArrayList<Zhao_UserDO>();
        for (Object o : UserSet) {
            //使用ObjectMapper类将objec类转换为,我们需要用的实体类(Zhao_UserDO)
            ObjectMapper objectMapper = new ObjectMapper();
            userList.add(objectMapper.convertValue(o,Zhao_UserDO.class));
        }
        for (Zhao_UserDO zhao_userDO : userList) {
            System.out.println(zhao_userDO.toString());
        }
//        List<String> Zhao_UserDO = new ArrayList<Zhao_UserDO>(UserSet);
    }
/**
 * 未成功弄复杂了
 *  sortSet存入对象,用treeMap存入
 *
 */
    @Test
    void sortSetTest_2() {
//        String str = "userALllList";
//        UserVo userVo = new UserVo();
//        userVo.setPage(1);//分页的第几页
//        userVo.setOffset(userVo.getLimit() * (userVo.getPage() - 1));
//
//        List<Zhao_UserDO> Userlist = ZhaoUserMapper.list(userVo);
//
//        System.out.println("list集合中的数据");
//        for (Zhao_UserDO zhao_userDO : Userlist) {
//            System.out.println(zhao_userDO.getUsername());
//        }
//        Set<Zhao_UserDO> UserSet = new TreeSet<Zhao_UserDO>(new Comparator<Zhao_UserDO>() {
//            @Override
//            public int compare(Zhao_UserDO o1, Zhao_UserDO o2) {
//                int num = -1;
//                if (o2.getUserId() - o1.getUserId() > 0) {//
//                    num = 1;
//                }
//                return num;
//            }
//        });
//
//        int j = 0;
//        for (Zhao_UserDO zhao_userDO : UserSet) {
//            System.out.println("set集合的数据");
//            System.out.println(zhao_userDO.getUsername() + j);
//        }
//
//        int i = 0;
//        for (Object object : UserSet) {
//            i++;
//            redisTemplateObj.opsForZSet().add(str, object, i);
//        }
//    }

}
}