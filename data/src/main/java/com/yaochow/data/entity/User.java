package com.yaochow.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 用户信息
 */
@Document(collection = "user")
@Data
public class User {
    @Id
    private String id;
    @Field("account_id")
    private String accountId;
    @Field("name")
    private String name;
    @Field("certificate_type")
    private String certificateType;
    @Field("certificate_code")
    private String certificateCode;
    @Field("age")
    private String age;
    @Field("gender")
    private String gender;
    @Field("birthday")
    private String birthday;
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
