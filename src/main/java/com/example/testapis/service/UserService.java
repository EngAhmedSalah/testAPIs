package com.example.testapis.service;

import com.example.testapis.dao.DAO;
import com.example.testapis.exception.UserNotFoundException;
import com.example.testapis.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    DAO<UserModel> userDAO;
    public void save(UserModel userModel) {
        validate(userModel);
        userDAO.save(userModel);
    }

    public List<UserModel> getAllUsers() {
        return userDAO.getAll();
    }

    public UserModel deleteUser(UserModel userModel) throws UserNotFoundException {
        validate(userModel);
        return userDAO.delete(userModel);
    }

    private boolean validate(UserModel userModel) {
        return userModel.getFname()!= null && userModel.getLname()!= null;
    }

    public Optional<UserModel> getUser(UserModel userModel) throws UserNotFoundException {
        return userDAO.get(userModel);
    }

    public void initUserData()
    {
        userDAO.initData();
    }

    public void testCommunication()
    {
        System.out.println("Salah");
    }
}
