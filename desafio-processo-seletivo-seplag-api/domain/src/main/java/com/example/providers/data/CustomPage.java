package com.example.providers.data;

import java.util.List;
import java.util.function.Function;

public interface CustomPage<T> {

    /**
     * Obtém os elementos da página.
     *
     * @return Lista de elementos.
     */
    List<T> getContent();

    /**
     * Obtém o total de elementos.
     *
     * @return O número total de elementos.
     */
    long getTotalElements();

    /**
     * Obtém o total de páginas.
     *
     * @return O número total de páginas.
     */
    int getTotalPages();

    /**
     * Obtém o tamanho da página.
     *
     * @return O tamanho da página.
     */
    int getPageSize();

    /**
     * Obtém o número da página atual.
     *
     * @return O número da página atual (baseado em zero).
     */
    int getPageNumber();

    /**
     * Indica se esta é a primeira página.
     *
     * @return {@code true} se for a primeira página, caso contrário {@code false}.
     */
    boolean isFirst();

    /**
     * Indica se esta é a última página.
     *
     * @return {@code true} se for a última página, caso contrário {@code false}.
     */
    boolean isLast();

    /**
     * Transforma os elementos da página em outro tipo usando uma função de mapeamento.
     *
     * @param mapper Função para transformar elementos.
     * @param <U> O tipo dos elementos transformados.
     * @return Uma nova página com os elementos transformados.
     */
    <U> CustomPage<U> map(Function<? super T, ? extends U> mapper);
}