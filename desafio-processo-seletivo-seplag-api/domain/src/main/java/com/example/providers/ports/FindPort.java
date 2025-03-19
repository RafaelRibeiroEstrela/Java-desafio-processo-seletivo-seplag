package com.example.providers.ports;

import java.util.List;
import java.util.Optional;

public interface FindPort<T, ID> {

    List<T> findAll();
    Optional<T> findById(ID id);
}
