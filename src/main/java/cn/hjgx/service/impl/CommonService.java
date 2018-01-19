package cn.hjgx.service.impl;


import cn.hjgx.entity.Menu;
import cn.hjgx.service.ICommonService;
import cn.hjgx.entity.User;
import cn.hjgx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonService implements ICommonService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User validateUser(User user) {
        return userMapper.validateUser(user);
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
