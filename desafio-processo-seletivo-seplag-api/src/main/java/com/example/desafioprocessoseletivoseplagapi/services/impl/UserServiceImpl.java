package com.example.desafioprocessoseletivoseplagapi.services.impl;

import com.example.desafioprocessoseletivoseplagapi.dtos.RoleDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.UserDTO;
import com.example.desafioprocessoseletivoseplagapi.models.User;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.BusinessException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.ResourceNotFoundException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.repositories.UserRepository;
import com.example.desafioprocessoseletivoseplagapi.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService, LayerDefinition {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }

    @Override
    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(UserDTO::new).toList();
    }

    @Override
    public UserDTO findById(Long id) {
        return new UserDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado", this)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDTO create(UserDTO dto) {
        validarCamposObrigatorios(dto);
        if (repository.findByUsername(dto.getUsername()).isPresent()) {
            throw new BusinessException("Já existe um usuário com username = " + dto.getUsername(), this);
        }
        User model = dto.toModel();
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        model = repository.save(model);
        return new UserDTO(model);
    }

    private void validarCamposObrigatorios(UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty()) {
            throw new BusinessException("O username é obrigatório", this);
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new BusinessException("A senha é obrigatório", this);
        }
        if (userDTO.getRoles().isEmpty()) {
            throw new BusinessException("O perfil de usuário é obrigatório", this);
        }
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }


    @Override
    public void updatePassword(String password, long id) {
        User model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado", this));
        model.setPassword(passwordEncoder.encode(password));
        repository.save(model);
    }

    @Override
    public void updateRoles(Set<RoleDTO> roles, long id) {
        User model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado", this));
        model.setRoles(roles.stream().map(RoleDTO::toModel).collect(Collectors.toSet()));
        repository.save(model);
    }
}
