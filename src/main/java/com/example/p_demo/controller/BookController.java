package com.example.p_demo.controller;

import com.example.p_demo.pojo.Author;
import com.example.p_demo.pojo.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @GetMapping("test")
    public Book test() throws JsonProcessingException {
        Book book = new Book();
        book.setId(1);
//        book.setIsbn("demo");
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Network");
        System.out.println(list);
        book.setTags(list);
        Author author = new Author();
        author.setFirstName("Abc");
        author.setLastName("Xyz");
        book.setAuthor(author);
        Map<String,Author> map = new HashMap<>();
        map.put("demo",author);
        book.setMap(map);
        String[] str = {"abc","def","uio"};
        book.setStr(str);
        ObjectMapper objectMapper = new ObjectMapper();
        String book2 = objectMapper.writeValueAsString(book);
        System.out.println(book2);
        Book book1 = objectMapper.readValue(book2, Book.class);
        System.out.println(book1.id);
        System.out.println(book1.name);
        System.out.println(book1.author);
        System.out.println(book1.isbn);
        System.out.println(book1.tags);
        System.out.println(book1.pubDate);
        return book;
    }
}
