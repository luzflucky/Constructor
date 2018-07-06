package com.maomi.coder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.maomi.coder.domain.Book;
import com.maomi.coder.service.BookService;
import com.maomi.coder.template.MgTemplate;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lucky on 2018/7/5.
 */
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private MgTemplate mgTemplate;

    @Override
    public int insertBook(Book book) {
        DBCollection db = mgTemplate.getCollection("bookList");
        DBObject document = new BasicDBObject();
        Map map = JSONObject.parseObject(JSONObject.toJSONString(book), Map.class);
        document.putAll(map);
        int result = db.save(document).getN();
        return result;
    }

    @Override
    public Book queryBookByBookName(String bookName) {
        DBCollection db = mgTemplate.getCollection("bookList");
        DBObject document = new BasicDBObject();
        document.put("bookName",bookName);
        DBObject object = db.findOne(document);
        Book result = JSONObject.parseObject(object.toString(),Book.class);
        return result;
    }

    @Override
    public List<Book> queryBookListByBasicDBObject(BasicDBObject obj) {
        DBCollection db = mgTemplate.getCollection("bookList");
        DBCursor objList = db.find(obj);
        List<Book> result = JSONArray.parseArray(JSONArray.toJSONString(objList),Book.class);
        return result;
    }

    @Override
    public int deleteBook(BasicDBObject obj) {
        DBCollection db = mgTemplate.getCollection("bookList");
        int result = db.remove(obj).getN();
        return result;
    }

    @Override
    public int updateBookByBasicDBObject(BasicDBObject obj) {
        DBCollection db = mgTemplate.getCollection("books");
        int result = db.update(new BasicDBObject("type", "玄幻"), obj).getN();
        return result;
    }

    @Override
    public int updateBook(BasicDBObject query, BasicDBObject update) {
        DBCollection db = mgTemplate.getCollection("bookList");
        int result = db.update(query, update).getN();
        return result;
    }

}
