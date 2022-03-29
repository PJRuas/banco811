package com.santander.banco811.controller;

import com.santander.banco811.dto.UserRequest;
import com.santander.banco811.dto.UserResponse;
import com.santander.banco811.model.User;
import com.santander.banco811.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserResponse> getAll(@RequestParam(required = false) String name) {
        return UserResponse.toResponse(userService.getAll(name));
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Integer id) {
        return new UserResponse(userService.getById(id));
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Integer id, @RequestBody UserRequest userRequest){
        return new UserResponse(userService.update(userRequest, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }
}
