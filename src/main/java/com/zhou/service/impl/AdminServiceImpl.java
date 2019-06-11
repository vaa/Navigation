package com.zhou.service.impl;

import com.zhou.mapper.AdminMapper;
import com.zhou.model.Admin;
import com.zhou.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin selectByUsername(String username){
        return adminMapper.selectByUsername(username);
    }

    public List<Admin> getAll(){
        return adminMapper.getAll();
    }

    public Admin findById(Integer id){
        return adminMapper.findById(id);
    }

    public int insert(Admin admin){
        return adminMapper.insert(admin);
    }

    public int update(Admin admin){
        return adminMapper.update(admin);

    }

    public int delete(Integer id){
        return adminMapper.delete(id);
    }


}
