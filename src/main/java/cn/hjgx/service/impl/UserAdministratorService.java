package cn.hjgx.service.impl;


import cn.hjgx.entity.Menu;
import cn.hjgx.entity.UserAdministrator;
import cn.hjgx.mapper.UserAdministratorMapper;
import cn.hjgx.service.IUserAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdministratorService implements IUserAdministratorService {

    @Autowired
    private UserAdministratorMapper userAdministratorMapper;

    @Override
    public UserAdministrator validateUser(UserAdministrator user) {
        return userAdministratorMapper.validateUser(user);
    }

    @Override
    public int updateByPrimaryKeySelective(UserAdministrator user) {
        return userAdministratorMapper.updateByPrimaryKeySelective(user);
    }
}
