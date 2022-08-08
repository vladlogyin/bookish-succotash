package com.sparta.spartafinalproject.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Document("comments")
public class Comment {

    @Id @Field(targetType = FieldType.OBJECT_ID)
    private String id;
    private Date date;
    private String email;
    @DBRef @Field(targetType = FieldType.OBJECT_ID)
    private Movie movie;
    private String text;

}
