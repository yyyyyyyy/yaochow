package com.yaochow.parent.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {

    private int totalPages;

    private long totalCount;

    private List<T> result;

    private boolean success;

    private String errorCode;

    private String errorMsg;
}
