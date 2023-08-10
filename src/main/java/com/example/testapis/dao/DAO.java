package com.example.testapis.dao;

import com.example.testapis.exception.UserNotFoundException;
import com.example.testapis.model.UserModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DAO <T>
{
    UserModel save(T t);
    UserModel delete(T t) throws UserNotFoundException;
    UserModel update(T t , Map<String , String > params) throws UserNotFoundException;

    List<T> getAll();

    Optional<T> get(T t) throws UserNotFoundException;

    void initData();
}
