package cn.hjgx.service;

import cn.hjgx.entity.UserAdministrator;
import cn.hjgx.entity.UserBusiness;
import cn.hjgx.entity.page.Pager;

/**
 * Created by alvin on 2017/7/22.
 */
public interface IUserBusinessService {

    /**
     * 验证用户是否存在，信息正确则返回用户信息，信息错误则没有匹配结果
     * @param user
     * @return
     */
    UserBusiness validateUser(UserBusiness user);

    int updateByPrimaryKeySelective(UserBusiness user);

    Pager<UserBusiness> getUserBusinessPaged(UserBusiness brand);

    int insertSelective(UserBusiness record);

    int deleteByPrimaryKey(Integer id);

    UserBusiness selectByPrimaryKey(Integer id);

    int updatePasswordByPrimary(Integer id,String newpassword);

}
