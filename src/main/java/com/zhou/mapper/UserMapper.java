package com.zhou.mapper;

import com.zhou.model.User;

import java.util.List;

public interface UserMapper {

    int findAllCount();

    List<User> getAll();

    User findById(Integer id);

    int insert(User user);

    int update(User user);

    int delete(Integer id);

    List<User> select(User user);

    User selectByUsername(String username);
}