package com.bookmngsys.service.impl;

import com.bookmngsys.dao.UserDao;
import com.bookmngsys.po.User;
import com.bookmngsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    public User login(User user){
        return userDao.getUser(user.getUsername(),user.getPassword());
    }
    public boolean checkUserIsExist(String username){
        User result=userDao.getUserByName(username);
        if(result==null){
            return false;
        }
        return true;
    }
    public User getUser(String username){
        return userDao.getUserByName(username);
    }
    public boolean register(User user){

        try{
        userDao.insertUser(user);}catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public void updatePass(int id,String newPass){
        userDao.updatePass(id,newPass);
    }

    @Override
    public void enableUser(int id) {
        userDao.updateUserStatus(id,1);
    }
    @Override
    public void disableUser(int id) {
        userDao.updateUserStatus(id,0);
    }
}
