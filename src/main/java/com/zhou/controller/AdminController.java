package com.zhou.controller;

import com.zhou.dto.ResponseCode;
import com.zhou.enums.StatusEnums;
import com.zhou.exception.GlobalException;
import com.zhou.model.Admin;
import com.zhou.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 跳转到后台首页
     *
     * @return
     */
    @GetMapping("/index")
    public String admin(Model model) {
        model.addAttribute("user", "");
        return "admin/index";
    }

    /**
     * 跳转到登录页
     *
     * @return
     */
    @GetMapping()
    public String login() {
        return "admin/login";
    }

    @PostMapping("/login1")
    public String login1(String username, String password, HttpSession session) {
        if (username==null||password==null)
            return "redirect:/admin";
        Admin admin=adminService.selectByUsername(username);
        if(admin!=null && admin.getPassword().trim().equals(password.trim())){
            session.setAttribute("admin",admin);
            return "redirect:/admin/index.html";
        }else{
            return "redirect:/admin";
        }
    }

    @ResponseBody
    @RequestMapping("/login")
    public ResponseCode login(@RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "password", required = false) String password,
                              HttpSession session) {
        if (username != null && password != null) {
            Admin admin = adminService.selectByUsername(username);
            if (admin != null && admin.getPassword().trim().equals(password.trim())) {
                session.setAttribute("admin", admin);
                return ResponseCode.success();
            } else {
                return new ResponseCode(StatusEnums.ACCOUNT_ERROR);
            }
        } else {
            return new ResponseCode(StatusEnums.ACCOUNT_ERROR);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin";
    }

    @GetMapping("/info")
    @ResponseBody
    public ResponseCode getInfo(HttpSession session) {
        return ResponseCode.success(session.getAttribute("admin"));
    }


    @ResponseBody
    @PostMapping("/update")
    public ResponseCode update(@RequestBody Admin admin) {
        try {
            adminService.update(admin);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 用户管理
     *
     * @return
     */
    @GetMapping("/user")
    public String user() {
        return "admin/page/user";
    }

    /**
     * 网站管理页
     *
     * @return
     */
    @GetMapping("/site")
    public String site() {
        return "admin/page/site";
    }

    /**
     * 分类标签页
     *
     * @return
     */
    @GetMapping("/category")
    public String category() {
        return "admin/page/category";
    }

    /**
     * 数据分析页
     *
     * @return
     */
    @GetMapping("/data")
    public String links() {
        return "admin/page/data";
    }

    /**
     * 个人中心管理页
     *
     * @return
     */
    @GetMapping("/me")
    public String users() {
        return "admin/page/me";
    }

}
