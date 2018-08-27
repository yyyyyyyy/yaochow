package com.yaochow.data.entity;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class PageEntity implements Pageable {
    private int pageNumber;

    private int pageSize;

    private Sort sort;

    @Override
    public int getPageNumber() {
        if (pageNumber > 1) {
            return pageNumber;
        } else {
            return 1;
        }
    }

    @Override
    public int getPageSize() {
        if (pageSize > 0) {
            return pageSize;
        } else {
            return 10;
        }
    }

    @Override
    public long getOffset() {
        return (getPageNumber() - 1) * getPageSize();
    }

    @Override
    public Sort getSort() {
        if (sort == null) {
            sort = new Sort(Sort.Direction.DESC, "gmt_created");
        }
        return sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
