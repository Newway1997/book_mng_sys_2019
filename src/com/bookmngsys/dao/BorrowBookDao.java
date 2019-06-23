package com.bookmngsys.dao;

import com.bookmngsys.po.BorrowBook;

import java.util.ArrayList;

public interface BorrowBookDao {
    void insertBorrowBook(BorrowBook borrowBook);
    ArrayList<BorrowBook> getBorrowBooks(int id);
    void updateBorrowBookStatus(int id,int readerId);
}
