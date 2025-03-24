package com.example.desafioprocessoseletivoseplagapi.providers.services;

import java.util.List;

public interface FindService<T, ID> {

    List<T> findAll();
    T findById(ID id);
}
