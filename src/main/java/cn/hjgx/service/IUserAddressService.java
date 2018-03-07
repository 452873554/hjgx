package cn.hjgx.service;

import cn.hjgx.entity.Brand;
import cn.hjgx.entity.UserAddress;
import cn.hjgx.entity.page.Pager;

import java.util.List;

/**
 * Created by alvin on 2018/1/11.
 */
public interface IUserAddressService {

    int insertSelective(UserAddress record);

    List<UserAddress> selectAllByUserName(String userName);

    void setDefault(String userName,int id);

    UserAddress selectByPrimaryKey(Integer id);

}
