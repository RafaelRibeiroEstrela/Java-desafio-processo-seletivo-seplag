package com.example.providers.data;

import com.example.providers.data.impl.CustomPageRequest;
import com.example.providers.data.impl.CustomSort;

import java.util.Optional;

public interface CustomPageable {

    static CustomPageable ofSize(int pageSize) {
        return CustomPageRequest.of(0, pageSize);
    }

    default boolean isPaged() {
        return true;
    }

    default boolean isUnpaged() {
        return !this.isPaged();
    }

    int getPageNumber();

    int getPageSize();

    long getOffset();

    CustomSort getSort();

    default CustomSort getSortOr(CustomSort sort) {
        if (sort == null) {
            throw new IllegalArgumentException("Fallback Sort must not be null");
        }
        return this.getSort().isSorted() ? this.getSort() : sort;
    }

    CustomPageable next();

    CustomPageable previousOrFirst();

    CustomPageable first();

    CustomPageable withPage(int pageNumber);

    boolean hasPrevious();

    default Optional<CustomPageable> toOptional() {
        return this.isUnpaged() ? Optional.empty() : Optional.of(this);
    }
}
