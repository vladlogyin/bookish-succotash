package com.sparta.spartafinalproject.documents;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="theaters")
public class Theater {
    @Id
    private String id;
    @Field("theaterId")
    private int theaterId;
    private Location location;

}
