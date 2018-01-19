package cn.hjgx.service;

import cn.hjgx.entity.Menu;
import cn.hjgx.entity.User;

import java.util.List;

/**
 * Created by alvin on 2017/7/22.
 */
public interface ICommonService {

    /**
     * 验证用户是否存在，信息正确则返回用户信息，信息错误则没有匹配结果
     * @param user
     * @return
     */
    public User validateUser(User user);

    public int updateByPrimaryKeySelective(User user);

}
