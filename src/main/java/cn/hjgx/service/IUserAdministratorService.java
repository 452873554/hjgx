package cn.hjgx.service;

import cn.hjgx.entity.Menu;
import cn.hjgx.entity.UserAdministrator;

import java.util.List;

/**
 * Created by alvin on 2017/7/22.
 */
public interface IUserAdministratorService {

    /**
     * 验证用户是否存在，信息正确则返回用户信息，信息错误则没有匹配结果
     * @param user
     * @return
     */
    public UserAdministrator validateUser(UserAdministrator user);

    public int updateByPrimaryKeySelective(UserAdministrator user);

}
