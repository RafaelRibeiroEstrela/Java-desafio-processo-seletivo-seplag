package com.example.desafioprocessoseletivoseplagapi.dtos;

import com.example.desafioprocessoseletivoseplagapi.models.Role;
import com.example.desafioprocessoseletivoseplagapi.providers.dtos.ToModel;

public class RoleDTO implements ToModel<Role> {

    private Long id;
    private String authority;

    public RoleDTO() {}

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public Role toModel() {
        Role model = new Role();
        model.setId(id);
        model.setAuthority(authority);
        return model;
    }
}
