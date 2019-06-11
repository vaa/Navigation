package com.zhou.controller;

import com.zhou.dto.QueryPage;
import com.zhou.dto.ResponseCode;
import com.zhou.enums.StatusEnums;
import com.zhou.exception.GlobalException;
import com.zhou.model.User;
import com.zhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public ResponseCode login(@RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "password", required = false) String password,
                              HttpSession session) {
        if (username != null && password != null) {
            User user = userService.selectByUsername(username);
            if (user != null && user.getPassword().trim().equals(password.trim())) {
                session.setAttribute("user", user);
                return ResponseCode.success();
            } else {
                return new ResponseCode(StatusEnums.ACCOUNT_ERROR);
            }
        } else {
            return new ResponseCode(StatusEnums.ACCOUNT_ERROR);
        }
    }

    @RequestMapping("/register")
    public ResponseCode register(@RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "password", required = false) String password,
                              HttpSession session) {
        if (username != null && password != null) {
            if(userService.selectByUsername(username) == null){
                User user =new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setNickName(username);
                user.setAvatar("/img/avatar/default.jpg");
                user.setRegisterDate(new Date());
                userService.insert(user);
                session.setAttribute("user", user);
                return ResponseCode.success();
            } else {
                return new ResponseCode(StatusEnums.ACCOUNT_EXIST);
            }
        } else {
            return new ResponseCode(StatusEnums.ACCOUNT_ERROR);
        }
    }

    @GetMapping("/info")
    public ResponseCode getInfo(HttpSession session) {
//        User user = (User)session.getAttribute("user");
////        if (user == null) {
////            return new ResponseCode(StatusEnums.ACCOUNT_ERROR);
////        } else {
////            return ResponseCode.success(user);
////        }
            return ResponseCode.success(session.getAttribute("user"));
    }

    @RequestMapping("/findAllCount")
    public ResponseCode findAllCount(){
        return ResponseCode.success(userService.findAllCount());
    }
    

    @GetMapping(value = "/findAll")
    public ResponseCode findAll() {
        return ResponseCode.success(userService.findAllCount());
    }

    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, User user) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> userService.findByPage(user)));
    }

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Integer id) {
        return ResponseCode.success(userService.findById(id));
    }

    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody User user) {
        try {
            userService.insert(user);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping(value = "/update")
    public ResponseCode update(@RequestBody User user) {
        try {
            userService.update(user);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody List<Integer> ids) {
        try {
            userService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

}
