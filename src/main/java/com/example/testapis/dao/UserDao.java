package com.example.testapis.dao;

import com.example.testapis.exception.UserNotFoundException;
import com.example.testapis.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserDao implements DAO<UserModel>
{

    List<UserModel> users = new ArrayList<>();
    @Override
    public UserModel save(UserModel userModel) {
        System.out.println("user saved to database");
        users.add(userModel);
        return userModel;
    }

    @Override
    public UserModel delete(UserModel user) throws UserNotFoundException {
        System.out.println("user deleted from database");
        Optional<UserModel> userResult = get(user);
        if (userResult.isPresent()) {
            users.remove(userResult.get());
            return userResult.get();
        }
        throw new UserNotFoundException("user not found");
    }

    @Override
    public UserModel update(UserModel userModel, Map<String, String> params) throws UserNotFoundException {
        if(get(userModel).isEmpty())
            throw new UserNotFoundException("user not found");
        System.out.println("user updated from database");
        users.stream()
                .filter(user -> user.getFname().equals(userModel.getFname())  && user.getLname().equals(userModel.getLname()))
                .forEach(d -> {
                    d.setFname(params.get("fname"));
                    d.setLname(params.get("lname"));
                });
        return userModel;
    }

    @Override
    public List<UserModel> getAll() {
        return users;
    }

    @Override
    public Optional<UserModel> get(UserModel userModel) throws UserNotFoundException {
        Optional<UserModel> userResult = Optional.ofNullable(users.stream().filter(user -> user.getFname().equals(userModel.getFname()) &&
                user.getLname().equals(userModel.getLname())).findAny().orElse(null));
        if(userResult.isPresent())
            return userResult;
        throw new UserNotFoundException("user not found");
    }

    public void initData()
    {
        Collections.addAll(users,
                new UserModel("ahmed" , "salah"),
                new UserModel("mohamed" , "salah"),
                new UserModel("Ahmed" , "eldeb"));
    }
    public void testCommunication()
    {
        System.out.println("Salah");
    }
}
