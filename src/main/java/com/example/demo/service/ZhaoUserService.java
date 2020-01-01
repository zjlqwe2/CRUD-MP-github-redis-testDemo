package com.example.demo.service;

import com.example.demo.domain.Zhao_UserDO;
import com.example.demo.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface ZhaoUserService {


    //根据id获得zhaoUser数据
	Zhao_UserDO getById(Long id);

	//查询所有zhaoUser表的数据,涉及到分页显示
	List<Zhao_UserDO> list(UserVo userVo);


	//
	int count(UserVo userVo);

	//保存
	int save(Zhao_UserDO zhao_UserDO);

	//更新
	int update(Zhao_UserDO user);

	//删除
	int remove(Long userId);


	//批量删除ZhaoUserMapper.xml
	int batchremove(Long[] userIds);

	boolean exit(Map<String, Object> params);

	//这是权限管理
	Set<String> listRoles(Long userId);

}
