package com.example.p_demo.service.impl;

import com.example.p_demo.pojo.Book;
import com.example.p_demo.pojo.Books;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@Service
public class MongoService<T> {
    private Logger logger = LoggerFactory.getLogger(MongoService.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 反射获取泛型类型
     *
     * @return
     */



    /**
     * 保存对象
     * @return
     */
    public String save(){
        logger.info("------mongodb start-------");
        Books books = new Books();
//        books.setId("5f903dda0e766875e6f0db1e");
        books.setName("dada");
        books.setUpdateTime(new Date());
        books.setCreateTime(new Date());
        try {
            mongoTemplate.save(books,"run7");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "添加成功";
    }

    /**
     * 获取全部数据
     * @return
     */
    public List<Books> findAll(){
        List<Books> lists = mongoTemplate.findAll(Books.class,"run7");
        return lists;
    }

    /**
     * 根据id查询
     * @return
     */
    public Books findId(){
        Query query = new Query(Criteria.where("_id").is("5f903f3fe0a84d5c9fd66422"));
        return mongoTemplate.findOne(query, Books.class);
    }

    /**
     * 根据名称查询
     * @param books
     * @return
     */
    public List<Books> getBooksName(Books books){
        Query query = new Query(Criteria.where("name").is("dada"));
        return mongoTemplate.find(query,Books.class);
    }

    /**
     * 修改单条数据
     * @return
     */
    public String updateBooks(){
        Query query = new Query(Criteria.where("_id").is("5f903f2795c9934c70045d4c"));
        Update update = new Update().set("name", "测试修改")
                .set("createTime", new Date())
                .set("updateTime", new Date());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Books.class);
        logger.warn(updateResult+"");
        return "修改成功";
    }

    /**
     * 删除数据
     */
    public void deleteBooks(){
        Query query = new Query(Criteria.where("name").is("测试修改"));
        mongoTemplate.remove(query,Books.class);
    }

    /**
     * 将查询条件对象转换为query
     *
     * @param object
     * @return
     * @author Jason
     */
    private Query getQueryByObject(T object) {
        Query query = new Query();
        String[] fileds = getFiledName(object);
        Criteria criteria = new Criteria();
        for (int i = 0; i < fileds.length; i++) {
            String filedName = (String) fileds[i];
            Object filedValue = getFieldValueByName(filedName, object);
            if (filedValue != null) {
                criteria.and(filedName).is(filedValue);
            }
        }
        query.addCriteria(criteria);
        return query;
    }

    /***
     * 修改匹配到的记录，若不存在该记录则进行添加
     * @param srcObj
     * @param targetObj
     */
    public void updateInsert(T srcObj, T targetObj){
        Query query = getQueryByObject(srcObj);
        Update update = getUpdateByObject(targetObj);
        logger.info("-------------->MongoDB updateInsert start");
        this.mongoTemplate.upsert(query,update,Books.class);
    }


    private  Update getUpdateByObject(T object) {
        Update update = new Update();
        String[] fileds = getFiledName(object);
        for (int i = 0; i < fileds.length; i++) {
            String filedName = (String) fileds[i];
            Object filedValue =getFieldValueByName(filedName, object);
            if (filedValue != null) {
                update.set(filedName, filedValue);
            }
        }
        return update;
    }

    /***
     * 获取对象属性返回字符串数组
     * @param o
     * @return
     */
    private static String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];

        for (int i = 0; i < fields.length; ++i) {
            fieldNames[i] = fields[i].getName();
        }

        return fieldNames;
    }

    /***
     * 根据属性获取对象属性值
     * @param fieldName
     * @param o
     * @return
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String e = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + e + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[0]);
            return method.invoke(o, new Object[0]);
        } catch (Exception var6) {
            return null;
        }
    }
}
