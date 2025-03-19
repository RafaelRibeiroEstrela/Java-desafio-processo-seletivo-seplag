package com.example.providers.data.impl;


import com.example.providers.data.CustomPage;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomPageImpl<T> implements CustomPage<T> {

    private final List<T> content;
    private final long totalElements;
    private final int pageNumber;
    private final int pageSize;

    /**
     * Construtor para criar uma instância de PageImpl.
     *
     * @param content Lista de elementos da página.
     * @param totalElements Total de elementos disponíveis.
     * @param pageNumber Número da página atual (baseado em zero).
     * @param pageSize Tamanho da página.
     */
    public CustomPageImpl(List<T> content, long totalElements, int pageNumber, int pageSize) {
        this.content = content;
        this.totalElements = totalElements;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public long getTotalElements() {
        return totalElements;
    }

    @Override
    public int getTotalPages() {
        return (int) Math.ceil((double) totalElements / pageSize);
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public boolean isFirst() {
        return pageNumber == 0;
    }

    @Override
    public boolean isLast() {
        return pageNumber >= getTotalPages() - 1;
    }

    @Override
    public <U> CustomPage<U> map(Function<? super T, ? extends U> mapper) {
        List<U> mappedContent = content.stream()
                .map(mapper)
                .collect(Collectors.toList());
        return new CustomPageImpl<>(mappedContent, totalElements, pageNumber, pageSize);
    }

    @Override
    public String toString() {
        return "PageImpl{" +
                "content=" + content +
                ", totalElements=" + totalElements +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}