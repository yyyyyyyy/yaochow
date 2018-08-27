package com.yaochow.parent.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageParam<T> implements Serializable {

    private int pageNumber = 1;

    private int pageSize = 10;

    private T param;
}
