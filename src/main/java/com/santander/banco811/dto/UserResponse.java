package com.santander.banco811.dto;

import com.santander.banco811.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResponse {
    private Integer id;
    private String cpf;
    private String name;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private String login;

    public UserResponse(User user) {
        this.id = user.getId();
        this.cpf = user.getCpf();
        this.name = user.getName();
        this.creationDate = user.getCreationDate();
        this.updateDate = user.getUpdateDate();
        this.login = user.getLogin();
    }

    public static List<UserResponse> toResponse(List<User> users){
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }
}