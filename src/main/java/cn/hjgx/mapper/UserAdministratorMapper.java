package cn.hjgx.mapper;

import cn.hjgx.entity.UserAdministrator;
import org.springframework.data.repository.query.Param;

public interface UserAdministratorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAdministrator record);

    int insertSelective(UserAdministrator record);

    UserAdministrator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAdministrator record);

    int updateByPrimaryKey(UserAdministrator record);

    /**
     * 验证用户是否存在，信息正确则返回用户信息，信息错误则没有匹配结果
     * @param user
     * @return
     */
    UserAdministrator validateUser(UserAdministrator user);
}