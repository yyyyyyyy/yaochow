package com.yaochow.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class NoteDTO implements Serializable {

    private String id;
    private String accountId;
    private String name;
    private String category;
    private String content;
}
