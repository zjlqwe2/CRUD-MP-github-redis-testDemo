package com.example.demo.dao;

import com.example.demo.domain.Zhao_UserDO;
import com.example.demo.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author zhao
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
@Repository
@Mapper
public interface ZhaoUserDao {

    //根据id获得单个数据
	Zhao_UserDO get(Long userId);

	//获得表所有的数据
	List<Zhao_UserDO> list(UserVo userVo);

	//
	int count(UserVo userVo);

    //保存
	int save(Zhao_UserDO user);

	//更新
	int update(Zhao_UserDO user);


	//删除
	int remove(Long userId);


	//批量删除
	int batchRemove(Long[] userIds);


}
