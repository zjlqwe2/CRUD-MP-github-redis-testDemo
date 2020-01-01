package com.example.demo.service.Impl;

import com.example.demo.dao.ZhaoUserDao;
import com.example.demo.domain.Zhao_UserDO;
import com.example.demo.service.ZhaoUserService;
import com.example.demo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Transactional
@Service
public class ZhaoUserServiceImpl implements ZhaoUserService {


    @Autowired
    ZhaoUserDao ZhaoUserMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplateObj;

    @Override
    public Zhao_UserDO getById(Long id) {



        Zhao_UserDO zhao_UserDO_redis = ZhaoUserMapper.get(id);


        return ZhaoUserMapper.get(id);
    }


    @Override
    public List<Zhao_UserDO> list(UserVo userVo) {


        return ZhaoUserMapper.list(userVo);
    }

    @Override
    public int count(UserVo userVo) {
        return ZhaoUserMapper.count(userVo);
    }

    @Override
    public int save(Zhao_UserDO zhao_UserDO) {
        System.out.println("service查询数据");
        System.out.println(zhao_UserDO.getName());
        return ZhaoUserMapper.save(zhao_UserDO);
    }

    @Override
    public int update(Zhao_UserDO user) {
        return ZhaoUserMapper.update(user);
    }

    @Override
    public int remove(Long userId) {
        return ZhaoUserMapper.remove(userId);
    }

    @Override
    public int batchremove(Long[] userIds) {
        return 0;
    }

    @Override
    public boolean exit(Map<String, Object> params) {
        return false;
    }

    @Override
    public Set<String> listRoles(Long userId) {
        return null;
    }
}
