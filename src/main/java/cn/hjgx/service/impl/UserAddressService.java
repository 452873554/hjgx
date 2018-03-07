package cn.hjgx.service.impl;

import cn.hjgx.entity.UserAddress;
import cn.hjgx.mapper.UserAddressMapper;
import cn.hjgx.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/3/6.
 */
@Service
public class UserAddressService implements IUserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public int insertSelective(UserAddress record) {

        if(record.getDefaultAddress()){
            //如果设置了最新记录为默认，那么其他记录都要取消默认
            userAddressMapper.unsetDefaultToAll(record.getUserName());
        }

        return userAddressMapper.insertSelective(record);
    }

    @Override
    public List<UserAddress> selectAllByUserName(String userName) {
        return userAddressMapper.selectAllByUserName(userName);
    }

    @Override
    public void setDefault(String userName,int id) {
        userAddressMapper.setDefault(userName,id);
    }

    @Override
    public UserAddress selectByPrimaryKey(Integer id) {
        return userAddressMapper.selectByPrimaryKey(id);
    }

}
