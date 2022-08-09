package com.sparta.spartafinalproject.documents;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="theaters")
public class Theater {
    @Id
    private String id;
    private int theaterid;
    private Location location;

}
