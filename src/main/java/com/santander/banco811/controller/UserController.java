package com.santander.banco811.controller;

import com.querydsl.core.types.Predicate;
import com.santander.banco811.dto.UserRequest;
import com.santander.banco811.dto.UserResponse;
import com.santander.banco811.model.User;
import com.santander.banco811.repository.UserRepository;
import com.santander.banco811.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public Page<User> getAll(@RequestParam(required = false) String name,
                             @RequestParam(required = false, defaultValue = "0") Integer page,
                             @RequestParam(required = false, defaultValue = "5") Integer size) {
        return userService.getAll(name, page, size);
    }

    @GetMapping("/cpf")
    public List<UserResponse> getAllByCpf(@RequestParam String cpf,
                                          @RequestParam(required = false, defaultValue = "0") Integer page,
                                          @RequestParam(required = false, defaultValue = "5") Integer size) {
        return UserResponse.toResponse(userService.getAllByCpf(cpf, page, size));
    }

    @GetMapping("/search")
    public List<User> search(@RequestParam String search) {
        return userService.search(search);
    }

    @GetMapping("/search/dsl")
    public Iterable<User> searchDsl(@QuerydslPredicate(root = User.class) Predicate predicate){
        return userRepository.findAll(predicate);
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest) {
        return new UserResponse(userService.create(userRequest));
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
