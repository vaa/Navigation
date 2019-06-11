package com.zhou.service;

import com.zhou.model.User;

import java.util.List;


public interface UserService {

    User selectByUsername(String username);

    int findAllCount();

    List<User> getAll();

    User findById(Integer id);

    int insert(User user);

    int update(User user);

    void delete(List<Integer> ids);

    List<User> findByPage(User user);

}