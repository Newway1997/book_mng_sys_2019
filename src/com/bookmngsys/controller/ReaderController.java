package com.bookmngsys.controller;

import com.bookmngsys.po.Reader;
import com.bookmngsys.po.User;
import com.bookmngsys.service.ReaderService;
import com.bookmngsys.tool.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ReaderController {
    @Autowired
    ReaderService readerService;
    @RequestMapping(value = "/api/reader/getReaderInfo",method = RequestMethod.GET)
    @ResponseBody
    public Response getReaderInfo(HttpSession session){
        User user=(User)session.getAttribute("USER_SESSION");
        Reader reader=readerService.getReader(user.getId());
        if(reader!=null){
            return new Response().success(reader);
        }else{
            return new Response().failure("未找到该用户");
        }

    }

    @RequestMapping(value = "/api/reader/updateReaderInfo",method = RequestMethod.POST)
    @ResponseBody
    public Response updateReaderInfo(@RequestBody Reader reader, HttpSession session){
        User user=(User)session.getAttribute("USER_SESSION");
        reader.setId(user.getId());
        readerService.updateReader(reader);
        return new Response().success();

    }
}
