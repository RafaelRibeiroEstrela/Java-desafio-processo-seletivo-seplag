package com.example.desafioprocessoseletivoseplagapi.providers.services;

public interface UpdateService<T, ID> {

    T update(T t, ID id);
}
