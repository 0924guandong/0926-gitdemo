package com.example.p_demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.PostConstruct;
import java.lang.annotation.Documented;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Document(value = "run7")
public class Books {

    @Id
    private String id;
    //价格
    private Integer price;
    //书名
    private String name;
    //简介
    private String info;
    //出版社
    private String publish;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;

    @PostConstruct
    public void test(){

    }

    @PostConstruct
    public void test2(){}
}
