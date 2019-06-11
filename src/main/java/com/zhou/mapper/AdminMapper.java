package com.zhou.mapper;

import com.zhou.model.Admin;

import java.util.List;

public interface AdminMapper {

    Admin selectByUsername(String username);

    List<Admin> getAll();

    Admin findById(Integer id);

    int insert(Admin admin);

    int update(Admin admin);

    int delete(Integer id);
}
