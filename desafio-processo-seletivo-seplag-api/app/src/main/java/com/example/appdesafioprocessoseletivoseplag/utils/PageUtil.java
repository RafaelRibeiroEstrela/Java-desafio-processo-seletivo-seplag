package com.example.appdesafioprocessoseletivoseplag.utils;

import com.example.providers.data.CustomPageable;
import com.example.providers.data.impl.CustomPageRequest;
import com.example.providers.data.impl.CustomSort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PageUtil {

    public static CustomPageable toCustomPageable(Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable must not be null");
        }
        if (pageable.getSort().isEmpty()) {
            return CustomPageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        }
        // Converte o Sort do Pageable para CustomSort
        CustomSort customSort = pageable.getSort().isSorted()
                ? CustomSort.by(pageable.getSort().stream()
                .map(order -> new CustomSort.CustomOrder(
                        CustomSort.CustomDirection.valueOf(order.getDirection().name()),
                        order.getProperty()))
                .collect(Collectors.toList()))
                : CustomSort.by(new ArrayList<>());

        return CustomPageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), customSort);
    }

    public static Pageable toPageable(CustomPageable customPageable) {
        if (customPageable == null) {
            throw new IllegalArgumentException("CustomPageable must not be null");
        }
        if (customPageable.getSort().isEmpty()) {
            return PageRequest.of(customPageable.getPageNumber(), customPageable.getPageSize());
        }
        // Converte o CustomSort para Sort do Spring Data
        Sort sort = customPageable.getSort().isSorted()
                ? Sort.by(customPageable.getSort().getOrders().stream()
                .map(order -> new Sort.Order(
                        Sort.Direction.valueOf(order.getDirection().name()),
                        order.getProperty()))
                .collect(Collectors.toList()))
                : Sort.unsorted();

        return PageRequest.of(
                customPageable.getPageNumber(),
                customPageable.getPageSize(),
                sort);
    }
}
