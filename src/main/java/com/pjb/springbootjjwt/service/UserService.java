package com.pjb.springbootjjwt.service;

import com.pjb.springbootjjwt.mapper.UserMapper;
import com.pjb.springbootjjwt.model.User;
import com.pjb.springbootjjwt.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jinbin
 * @date 2018-07-08 20:52
 */
@Service("UserService")
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User findByUsername(User user) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andUsernameEqualTo(user.getUsername());



        List<User> users = userMapper.selectByExample(example);

        if (users!=null&&users.size()>0) {

            return users.get(0);
        }

        return null;

    }

    public User findUserById(String userId) {


        UserExample example = new UserExample();
        example.createCriteria()
                .andIdEqualTo(Integer.valueOf(userId));


        List<User> users = userMapper.selectByExample(example);

        return users.get(0);
    }

}
