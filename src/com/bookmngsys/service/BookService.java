package com.bookmngsys.service;

import com.bookmngsys.po.Book;
import com.bookmngsys.po.BorrowBook;
import com.bookmngsys.po.Category;

import java.util.ArrayList;

public interface BookService {
    ArrayList<Book> getBooks(int curPage,int pageSize,int mode);
    Book getBook(String isbn);
    void borrowBook(String isbn, int readerId);
    ArrayList<BorrowBook> getBorrowBooks(int id);
    void returnBook(int id,String isbn,int readerId);
    void disableBook(String isbn);
    void enableBook(String isbn);
    ArrayList<Category> getCategory();
    void addBook(Book book);
    void updateBook(String originBook,Book book);
}
