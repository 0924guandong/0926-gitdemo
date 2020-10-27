package com.example.p_demo.pojo;

import com.example.p_demo.common.IsbnDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    public long id;
    public String name;
    public Author author;

    @JsonDeserialize(using = IsbnDeserializer.class)
    public BigInteger isbn;

    public List<String> tags;
    public String pubDate;
    public Map<String,Author> map;
    private String[] str;
}
