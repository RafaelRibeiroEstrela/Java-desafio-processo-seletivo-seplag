package com.example.providers.data.impl;


import com.example.providers.data.CustomPageable;

public class CustomPageRequest implements CustomPageable {

    private final int pageNumber;
    private final int pageSize;
    private final CustomSort sort;

    protected CustomPageRequest(int pageNumber, int pageSize, CustomSort sort) {
        if (sort == null) {
            throw new IllegalArgumentException("sort must not be null");
        }
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public static CustomPageRequest of(int pageNumber, int pageSize) {
        return of(pageNumber, pageSize, CustomSort.unsorted());
    }

    public static CustomPageRequest of(int pageNumber, int pageSize, CustomSort sort) {
        return new CustomPageRequest(pageNumber, pageSize, sort);
    }

    public static CustomPageRequest of(int pageNumber, int pageSize, CustomSort.CustomDirection direction, String... properties) {
        return of(pageNumber, pageSize, CustomSort.by(direction, properties));
    }

    public static CustomPageRequest ofSize(int pageSize) {
        return of(0, pageSize);
    }

    @Override
    public int getPageNumber() {
        return this.pageNumber;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public long getOffset() {
        return (long)this.pageNumber * (long)this.pageSize;
    }

    public CustomSort getSort() {
        return this.sort;
    }

    public CustomPageRequest next() {
        return new CustomPageRequest(this.getPageNumber() + 1, this.getPageSize(), this.getSort());
    }

    @Override
    public CustomPageable previousOrFirst() {
        return this.hasPrevious() ? this.previous() : this.first();
    }

    public CustomPageRequest previous() {
        return this.getPageNumber() == 0 ? this : new CustomPageRequest(this.getPageNumber() - 1, this.getPageSize(), this.getSort());
    }

    public CustomPageRequest first() {
        return new CustomPageRequest(0, this.getPageSize(), this.getSort());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof CustomPageRequest)) {
            return false;
        } else {
            CustomPageRequest that = (CustomPageRequest)obj;
            return super.equals(that) && this.sort.equals(that.sort);
        }
    }

    public CustomPageRequest withPage(int pageNumber) {
        return new CustomPageRequest(pageNumber, this.getPageSize(), this.getSort());
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    public CustomPageRequest withSort(CustomSort.CustomDirection direction, String... properties) {
        return new CustomPageRequest(this.getPageNumber(), this.getPageSize(), CustomSort.by(direction, properties));
    }

    public CustomPageRequest withSort(CustomSort sort) {
        return new CustomPageRequest(this.getPageNumber(), this.getPageSize(), sort);
    }

    public int hashCode() {
        return 31 * super.hashCode() + this.sort.hashCode();
    }

    public String toString() {
        return String.format("Page request [number: %d, size %d, sort: %s]", this.getPageNumber(), this.getPageSize(), this.sort);
    }
}
