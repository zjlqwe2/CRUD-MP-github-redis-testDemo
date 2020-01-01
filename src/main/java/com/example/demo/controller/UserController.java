package com.example.demo.controller;

import com.example.demo.common.DataGridView;
import com.example.demo.common.ResultObj;
import com.example.demo.domain.Zhao_UserDO;
import com.example.demo.service.ZhaoUserService;
import com.example.demo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    ZhaoUserService zhaoUserService;

    /**
     * 查询用户根据ID
     */
    @RequestMapping("loadUserById")
    public DataGridView loadUserById( long id) {
        System.out.println(id);
        try {
            Zhao_UserDO zhaoUserDO = zhaoUserService.getById(id);
            System.out.println(zhaoUserDO.getName());
            DataGridView dataGridView = new DataGridView(zhaoUserDO);
            return dataGridView;
        } catch (Exception e) {
            e.printStackTrace();
            DataGridView dataGridView = new DataGridView();
            dataGridView.setMsg("查询错误");
            return dataGridView;
        }
    }

    /**
     * 查询用户列表,所有数据
     */
    @RequestMapping("getList")

    public DataGridView getList(UserVo userVo) {

        userVo.setOffset(userVo.getLimit() * (userVo.getPage() - 1));
        System.out.println("从第"+userVo.getOffset()+"位开始,"  +"查询"+ userVo.getLimit()+"个数据");

        List<Zhao_UserDO> userList = zhaoUserService.list(userVo);


//
//        //变量数据,用于测试用的
//        for (int i = 0; i < userList.size(); i++) {
//            System.out.println(userList.get(i).getUsername());
//        }
////        zhaoUserService.list(userVo);

        //获取总共的页数
        long count = zhaoUserService.count(userVo);

        return new DataGridView(count, userList);

    }


    /**
     * 添加用户
     */
    @RequestMapping("addUser")
    public ResultObj addUser(UserVo UserVo) {
        System.out.println("Controller查询数据");
        System.out.println(UserVo.getName());


        try {
            zhaoUserService.save(UserVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }

    /**
     * 删除用户
     */
    @RequestMapping("removeByid")
    public ResultObj removeByid(long id) {
        System.out.println(id);

        if (zhaoUserService.remove(id) == 1) {
            return ResultObj.DELETE_SUCCESS;
        } else {
            return ResultObj.DELETE_ERROR;

        }


    }

    /**
     * 修改用户
     */
    @RequestMapping("update")
    public ResultObj update(Zhao_UserDO zhao_UserDO) {
        System.out.println(zhao_UserDO.getUserId());
        if (zhao_UserDO.getUserId() == null) {
            return ResultObj.RESET_ERROR_null;
        }
        try {
            zhaoUserService.update(zhao_UserDO);
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

}
