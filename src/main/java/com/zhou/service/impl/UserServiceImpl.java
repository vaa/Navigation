package com.zhou.service.impl;

import com.zhou.exception.GlobalException;
import com.zhou.mapper.UserMapper;
import com.zhou.model.User;
import com.zhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectByUsername(String username){
        return userMapper.selectByUsername(username);
    }

    public int findAllCount() {
        return userMapper.findAllCount();
    }

    public List<User> getAll() {
        return userMapper.getAll();
    }

    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    public int insert(User user) {
        return userMapper.insert(user);
    }

    public int update(User user) {
        return userMapper.update(user);
    }

    public void delete(List<Integer> ids) {
        if (!ids.isEmpty()) {
            try {
                ids.forEach(id -> {
                    userMapper.delete(id);
                    //删除与该分类与文章关联的信息
                    //！！！！！！！！
                });
            } catch (Exception e) {
                throw new GlobalException(e.getMessage());
            }
        }
    }

    public List<User> findByPage(User user) {
        return userMapper.select(user);
    }
}