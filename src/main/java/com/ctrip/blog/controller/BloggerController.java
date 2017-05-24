package com.ctrip.blog.controller;

import com.ctrip.blog.pojo.Blogger;
import com.ctrip.blog.service.BloggerService;
import com.ctrip.blog.util.CryptographyUtil;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by qiuhl on 2017/5/24.
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {
    @Resource
    private BloggerService bloggerService;
    @RequestMapping("/login")
    public String login(Blogger blogger, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        String newPassword = CryptographyUtil.md5(blogger.getPassword(), "ctrip");
        //将用户信息封装到token中，跟数据库中的结果进行匹配
        UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUsername(), newPassword);
        try {
            subject.login(token);
            return "redirect:/admin/main.jsp";
        }catch (AuthorizationException e){
            e.printStackTrace();
            request.setAttribute("blogger",blogger);
            request.setAttribute("errorInfo","用户名或密码错误");
            //如果认证失败，会跳转到登录页面并进行错误提示
            return "login";
        }
    }
}
