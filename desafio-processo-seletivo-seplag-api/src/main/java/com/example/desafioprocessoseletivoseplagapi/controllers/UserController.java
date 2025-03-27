package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.RoleDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.UserDTO;
import com.example.desafioprocessoseletivoseplagapi.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserDTO save(@RequestBody UserDTO userDTO) {
        return service.create(userDTO);
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/update-password/{id}")
    public void updatePassword(@RequestBody String password, @PathVariable Long id, HttpServletRequest request) {
        service.updatePassword(password, id);
    }

    @PutMapping("/update-roles/{id}")
    public void updateRoles(@RequestBody Set<RoleDTO> roles, @PathVariable Long id, HttpServletRequest request) {
        service.updateRoles(roles, id);
    }
}
