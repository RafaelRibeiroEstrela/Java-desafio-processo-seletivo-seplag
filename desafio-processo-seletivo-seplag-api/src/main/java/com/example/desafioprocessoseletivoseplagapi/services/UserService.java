package com.example.desafioprocessoseletivoseplagapi.services;

import com.example.desafioprocessoseletivoseplagapi.dtos.RoleDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.UserDTO;
import com.example.desafioprocessoseletivoseplagapi.providers.services.CreateService;
import com.example.desafioprocessoseletivoseplagapi.providers.services.CrudService;
import com.example.desafioprocessoseletivoseplagapi.providers.services.DeleteService;
import com.example.desafioprocessoseletivoseplagapi.providers.services.FindService;

import java.util.Set;

public interface UserService extends FindService<UserDTO, Long>, CreateService<UserDTO>, DeleteService<Long> {

    void updatePassword(String password, long id);
    void updateRoles(Set<RoleDTO> roles, long id);
}
