package com.santander.banco811.service;

import com.santander.banco811.dto.UserRequest;
import com.santander.banco811.dto.UserResponse;
import com.santander.banco811.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    Page<User> getAll(String name, Integer page, Integer size);
    List<User> search(String search);
    List<User> getAllByCpf(String cpf, Integer page, Integer size);
    User create(UserRequest userRequest);
    User getById(Integer id);
    User getByLogin(String login);
    User update(UserRequest userRequest, Integer id);
    void delete(Integer id);
}