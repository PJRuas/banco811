package com.santander.banco811.service;

import com.santander.banco811.dto.UserRequest;
import com.santander.banco811.dto.UserResponse;
import com.santander.banco811.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll(String name);
    UserResponse create(UserRequest userRequest);
    User getById(Integer id);
    User update(UserRequest userRequest, Integer id);
    void delete(Integer id);

}
