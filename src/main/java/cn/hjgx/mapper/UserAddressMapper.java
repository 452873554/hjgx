package cn.hjgx.mapper;

import cn.hjgx.entity.UserAddress;

import java.util.List;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    int unsetDefaultToAll(String userName);

    void setDefault(String userName,int id);

    List<UserAddress> selectAllByUserName(String userName);
}