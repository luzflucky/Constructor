package com.maomi.coder.service;

import com.maomi.coder.domain.Book;
import com.mongodb.BasicDBObject;

import java.util.List;
import java.util.Map;

/**
 * Created by lucky on 2018/7/5.
 */
public interface BookService {

    int insertBook(Book book);

    Book queryBookByBookName(String bookName);

    List<Book> queryBookListByBasicDBObject(BasicDBObject obj);

    int deleteBook(BasicDBObject obj);

    int updateBookByBasicDBObject(BasicDBObject obj);

    int updateBook(BasicDBObject query,BasicDBObject update);
}
