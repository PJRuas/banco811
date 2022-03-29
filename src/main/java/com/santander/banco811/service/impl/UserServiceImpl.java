package com.santander.banco811.service.impl;

import com.santander.banco811.dto.UserRequest;
import com.santander.banco811.dto.UserResponse;
import com.santander.banco811.model.User;
import com.santander.banco811.repository.UserRepository;
import com.santander.banco811.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll(String name) {
        return name != null ? userRepository.findByName(name) : userRepository.findAll();
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        User userToSave = new User(userRequest);
        userRepository.save(userToSave);
        return new UserResponse(userToSave);
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User update(UserRequest userRequest, Integer id) {
        User userToUpdate = getById(id);
        userToUpdate.setName(userRequest.getName());
        userToUpdate.setCpf(userRequest.getCpf());
        userToUpdate.setPassword(userRequest.getPassword());
        return userRepository.save(userToUpdate);
    }

    @Override
    public void delete(Integer id) {
        var userToDelete = getById(id);
        userRepository.delete(userToDelete);
    }
}