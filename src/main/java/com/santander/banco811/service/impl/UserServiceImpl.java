package com.santander.banco811.service.impl;

import com.santander.banco811.dto.UserRequest;
import com.santander.banco811.dto.UserResponse;
import com.santander.banco811.model.User;
import com.santander.banco811.repository.UserRepository;
import com.santander.banco811.service.UserService;
import com.santander.banco811.specification.UserSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private PageRequest generateDefaultPageable(Integer page, Integer size, int defaultPage, int defaultSize){
        page = page != null ? page : defaultPage;
        size = size != null ? size : defaultSize;
        return PageRequest.of(page, size, Sort.Direction.ASC);
    }

    @Override
    public Page<User> getAll(String name, Integer page, Integer size) {
        PageRequest pageRequest = generateDefaultPageable(page, size, 0, 5);
        return name != null ? userRepository.findByName(name, pageRequest) : userRepository.findAll(pageRequest);
    }

    @Override
    public List<User> getAllByCpf(String cpf, Integer page, Integer size){
        PageRequest pageRequest = generateDefaultPageable(page, size, 0, 5);
        return userRepository.findByCpf(cpf, pageRequest);
    }

    @Override
    public User create(UserRequest userRequest) {
        var encryptedPassword = passwordEncoder.encode(userRequest.getPassword());
        User userToSave = new User(userRequest, encryptedPassword);
        userRepository.save(userToSave);
        return userToSave;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login).get();
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

    public List<User> search(String search){
        UserSpecificationBuilder builder = new UserSpecificationBuilder();

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find()){
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<User> specification = builder.build();
        return userRepository.findAll(specification);
    }
}