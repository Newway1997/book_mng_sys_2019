package com.bookmngsys.dao;

import com.bookmngsys.po.Book;
import com.bookmngsys.po.Category;

import java.util.ArrayList;

public interface BookDao {
    ArrayList<Book> getBooks(int curPage,int pageSize);
    ArrayList<Book> getAllBooks(int curPage,int pageSize);
    Book getBook(String isbn);
    void updateBookNum(String isbn);
    void updateBookNum2(String isbn);
    void updateBookStatus(String isbn,int status);
    ArrayList<Category> getCategory();
    void insertBook(Book book);
    void updateBook(String originIsbn,Book book);
}
