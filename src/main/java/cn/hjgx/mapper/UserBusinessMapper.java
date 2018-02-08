package cn.hjgx.mapper;

import cn.hjgx.entity.UserBusiness;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserBusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBusiness record);

    int insertSelective(UserBusiness record);

    UserBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBusiness record);

    int updateByPrimaryKey(UserBusiness record);

    /**
     * 验证用户是否存在，信息正确则返回用户信息，信息错误则没有匹配结果
     * @param user
     * @return
     */
    UserBusiness validateUser(UserBusiness user);

    List<UserBusiness> selectByPageParam(UserBusiness spp);

    int updatePasswordByPrimary(Integer id,String newpassword);
}