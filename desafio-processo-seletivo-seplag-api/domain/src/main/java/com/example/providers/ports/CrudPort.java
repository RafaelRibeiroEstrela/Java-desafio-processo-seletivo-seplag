package com.example.providers.ports;

public interface CrudPort<T, ID> extends SavePort<T>, DeletePort<ID>, FindPort<T, ID> {

}
