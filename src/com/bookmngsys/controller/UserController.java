package com.bookmngsys.controller;

import com.bookmngsys.po.Book;
import com.bookmngsys.po.Reader;
import com.bookmngsys.po.User;
import com.bookmngsys.service.ReaderService;
import com.bookmngsys.service.UserService;
import com.bookmngsys.tool.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ReaderService readerService;
    //登录
    @RequestMapping(value = "/api/user/login",method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestBody User user, HttpSession session){
        if(user==null){
            return new Response().failure("用户为空");
        }
        User result =userService.login(user);
        //登陆成功
        if(result!=null){
            if(result.getStatus()==1){
                session.setAttribute("USER_SESSION",result);
                return new Response().success();
            }else {
                return new Response().failure("账号已被封禁");
            }

        }
        return new Response().failure("账号或密码错误");
    }
    @RequestMapping(value = "/api/user/register",method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestBody User user, HttpSession session){
        //登陆成功
        if(!userService.checkUserIsExist(user.getUsername())){
            if(userService.register(user)){return new Response().success();}else{
                return new Response().failure("注册失败");
            }

        }
        return new Response().failure("账号已存在");
    }
    @RequestMapping(value = "/api/user/updatePass",method = RequestMethod.POST)
    @ResponseBody
    public Response updatePass(@RequestBody Map<String, String> map, HttpSession session){
        //登陆成功
        User user=(User)session.getAttribute("USER_SESSION");

        if(!user.getPassword().equals(map.get("oldPass"))){
            return new Response().failure("请输入正确的原密码");
        }else{
            userService.updatePass(user.getId(),map.get("newPass"));
            return new Response().success();
        }

    }
    @RequestMapping(value = "/api/user/logout",method = RequestMethod.GET)
    @ResponseBody
    public Response logout(HttpSession session){
        session.removeAttribute("USER_SESSION");
        return new Response().success();
    }
    @RequestMapping(value = "/api/user/checkUser",method = RequestMethod.GET)
    @ResponseBody
    public Response checkUser(HttpSession session){
        User user=(User)session.getAttribute("USER_SESSION");
        if(user==null){
            return new Response().failure("未登录");
        }else{
        User reslut=userService.getUser(user.getUsername());
        reslut.setPassword("");
        return new Response().success(reslut);}
    }


    @RequestMapping("/api/user/getAllUsers")
    @ResponseBody
    public Response getReaders(HttpServletRequest request, HttpSession session){
        //按页码返回书籍
        String curPage=request.getParameter("curPage");
        String pageSize=request.getParameter("pageSize");
        if(curPage==null){
            curPage="0";
        }
        if(pageSize==null){
            pageSize="24";
        }

        ArrayList<Reader> readers=readerService.getReaders(Integer.parseInt(curPage),Integer.parseInt(pageSize));
        return new Response().success(readers);
    }

    @RequestMapping("/api/user/enableUser")
    @ResponseBody
    public Response enableUser(HttpServletRequest request, HttpSession session){
        //按isbn借阅图书
        User user =(User) session.getAttribute("USER_SESSION");
        String id=request.getParameter("id");

        if(id==null||user.getType()!=0){
            return new Response().failure("未找到该条记录");
        }else{
            userService.enableUser(Integer.parseInt(id));
            return new Response().success();
        }
    }
    @RequestMapping("/api/user/disableUser")
    @ResponseBody
    public Response disableUser(HttpServletRequest request, HttpSession session){
        //按isbn借阅图书
        User user =(User) session.getAttribute("USER_SESSION");
        String id=request.getParameter("id");

        if(id==null||user.getType()!=0){
            return new Response().failure("未找到该条记录");
        }else{
            userService.disableUser(Integer.parseInt(id));
            return new Response().success();
        }
    }
}
