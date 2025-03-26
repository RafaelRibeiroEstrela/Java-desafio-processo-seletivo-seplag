package com.example.desafioprocessoseletivoseplagapi.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_tb")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private Long id;
    @Column(name = "us_username")
    private String username;
    @Column(name = "us_password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_tb",
            joinColumns = @JoinColumn(name = "us_id"),
            inverseJoinColumns = @JoinColumn(name = "ro_id"))
    private Set<Role> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implementar conforme a necessidade
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implementar conforme a necessidade
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implementar conforme a necessidade
    }

    @Override
    public boolean isEnabled() {
        return true; // Implementar conforme a necessidade
    }
}
