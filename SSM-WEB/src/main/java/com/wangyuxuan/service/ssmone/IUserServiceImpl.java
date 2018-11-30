package com.wangyuxuan.service.ssmone;

import com.wangyuxuan.dao.UserMapper;
import com.wangyuxuan.pojo.User;
import com.wangyuxuan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/11/28 16:04
 * @Description:
 */
@Service("iUserService")
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list(Map<String, Object> map) {
        return userMapper.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return userMapper.getTotal(map);
    }

    @Override
    public User getUserById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
