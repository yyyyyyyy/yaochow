package com.yaochow.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 笔记
 */
@Document(collection = "note")
@Data
public class Note {

    @Id
    private String id;
    @Field("account_id")
    private String accountId;
    @Field("note_name")
    private String name;
    @Field("category")
    private String category;
    @Field("note_content")
    private String content;
    @Field("gmt_created")
    protected Date gmtCreated;
    @Field("creator")
    protected String creator;
    @Field("gmt_modified")
    protected Date gmtModified;
    @Field("modifier")
    protected String modifier;
    @Field("is_delete")
    protected String delete;

}
