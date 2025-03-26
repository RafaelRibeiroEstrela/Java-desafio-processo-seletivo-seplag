package com.example.desafioprocessoseletivoseplagapi.dtos;

import com.example.desafioprocessoseletivoseplagapi.models.User;
import com.example.desafioprocessoseletivoseplagapi.providers.dtos.ToModel;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO implements ToModel<User> {

    private Long id;
    private String username;
    private String password;
    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles.addAll(user.getRoles().stream()
                .map(RoleDTO::new)
                .toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    @Override
    public User toModel() {
        User model = new User();
        model.setId(id);
        model.setUsername(username);
        model.setPassword(password);
        model.setRoles(roles.stream().map(RoleDTO::toModel).collect(Collectors.toSet()));
        return model;
    }
}
