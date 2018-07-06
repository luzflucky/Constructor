package com.maomi.coder.test;
import com.maomi.coder.domain.Book;
import com.maomi.coder.service.BookService;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lucky on 2018/7/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookTest {

    @Autowired
    private BookService bookService;

    private Book book;

    @Before
    public void init(){
        book = new Book();
        book.setBookName("隔壁老王1");
        book.setAuthor("李四");
        book.setCreateTime(new Date());
        book.setType("玄幻");
        book.setPrice(300);
    }
    
    @Test
    public void Test(){
        int ins = bookService.insertBook(book);
//        Assert.assertEquals(1,ins);
        Book book_ = bookService.queryBookByBookName("隔壁老王");
        System.out.println(book_.get_id());
        Assert.assertEquals("张三",book_.getAuthor());

//        int del = bookService.deleteBook(new BasicDBObject("price", 200));
//        System.out.println("delete: "+del);
//        Assert.assertEquals(1,del);
//        BasicDBObject basicDBObject = new BasicDBObject("price", new BasicDBObject("$gte", 100).append("$lte", 200));
//        int[] array = {100,200};
//        BasicDBObject basicDBObject = new BasicDBObject("price", new BasicDBObject("$in", array));
//        List<Book> books = bookService.queryBookListByBasicDBObject(basicDBObject);
//        Assert.assertEquals(3,books.size());
    }

    @Test
    public void POPTest(){
        int i = bookService.updateBookByBasicDBObject(new BasicDBObject("$pop", new BasicDBObject("types", 1)));
        Assert.assertEquals(1,i);
    }

    @Test
    public void updateTest(){
//        BasicDBObject query = new BasicDBObject("author","李四");
        BasicDBObject query = new BasicDBObject("_id",new ObjectId("5b3ecc3f9d8bc336b04763d3"));
        BasicDBObject update = new BasicDBObject("$set",new BasicDBObject("price",600));
        int result = bookService.updateBook(query, update);
        Assert.assertEquals(1,result);
    }
}
