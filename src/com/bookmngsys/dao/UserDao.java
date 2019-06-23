package com.bookmngsys.dao;

import com.bookmngsys.po.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User getUser(@Param("username") String username, @Param("password")String password);
    User getUserByName(String username);
    boolean insertUser(User user);
    void updatePass(int id, String newPass);
    void updateUserStatus(int id,int status);
}
