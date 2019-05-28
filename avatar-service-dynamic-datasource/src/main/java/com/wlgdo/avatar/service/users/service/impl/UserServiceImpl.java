package com.wlgdo.avatar.service.users.service.impl;



import com.baomidou.dynamic.datasource.annotation.DS;
import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.dubbo.rpc.Resp;
import com.wlgdo.avatar.dubbo.service.IUserService;
import com.wlgdo.avatar.service.users.mapper.UserMapper;
import com.wlgdo.avatar.service.users.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
@Component
public class UserServiceImpl implements IUserService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserMapper userMapper;

    @DS("master")
    @Override
    public String getUserName() {
        logger.info("start get user name");
        User user = userMapper.findUser();
        return user == null ? "My name is feify" : user.getName();
    }

    @DS("slave_1")
    @Override
    public PageInfo getList(int pageIndex, int pageSize) {
        logger.info("stat get user list");
        List lists = userMapper.getList();
        PageInfo pageInfo = new PageInfo(lists);

        return pageInfo;
    }

    @Override
    public Object addCsdnUserAccount() {

        logger.info("stat add csdn user account");

        return new Resp("ok");
    }


}