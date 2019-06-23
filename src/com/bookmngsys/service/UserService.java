package com.bookmngsys.service;

import com.bookmngsys.po.User;

public interface UserService {
    boolean checkUserIsExist(String username);
    boolean register(User user);
    User login(User user);
    User getUser(String username);
    void updatePass(int id,String newPass);
    void enableUser(int id);
    void disableUser(int id);
}
