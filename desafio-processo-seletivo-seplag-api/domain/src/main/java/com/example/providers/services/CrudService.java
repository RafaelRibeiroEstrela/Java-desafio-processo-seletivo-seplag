package com.example.providers.services;

public interface CrudService<T, ID> extends CreateService<T>, UpdateService<T, ID>, DeleteService<ID>, FindService<T, ID> {

}
