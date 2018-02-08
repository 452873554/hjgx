package cn.hjgx.service.impl;

import cn.hjgx.entity.UserBusiness;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.mapper.UserBusinessMapper;
import cn.hjgx.service.IUserBusinessService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/2/7.
 */
@Service
public class UserBusinessService implements IUserBusinessService {


    @Autowired
    private UserBusinessMapper userBusinessMapper;

    @Override
    public UserBusiness validateUser(UserBusiness user) {
        return userBusinessMapper.validateUser(user);
    }

    @Override
    public int updateByPrimaryKeySelective(UserBusiness user) {
        return userBusinessMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Pager<UserBusiness> getUserBusinessPaged(UserBusiness userBusiness) {
        PageHelper.startPage(userBusiness.getPageOffSet(), userBusiness.getPageSize());
        PageHelper.orderBy("create_time desc");
        List<UserBusiness> datas = userBusinessMapper.selectByPageParam(userBusiness);

        Pager<UserBusiness> pager = new Pager<>();
        pager.setDatas(datas);

        return pager;
    }

    @Override
    public int insertSelective(UserBusiness record) {
        return userBusinessMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userBusinessMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserBusiness selectByPrimaryKey(Integer id) {
        return userBusinessMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updatePasswordByPrimary(Integer id, String newpassword) {
        return userBusinessMapper.updatePasswordByPrimary(id,newpassword);
    }
}
