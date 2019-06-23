package com.bookmngsys.service.impl;

import com.bookmngsys.dao.BookDao;
import com.bookmngsys.dao.BorrowBookDao;
import com.bookmngsys.po.Book;
import com.bookmngsys.po.BorrowBook;
import com.bookmngsys.po.Category;
import com.bookmngsys.po.Reader;
import com.bookmngsys.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookDao;
    @Autowired
    BorrowBookDao borrowBookDao;
    @Override
    public ArrayList<Book> getBooks(int curPage,int pageSize,int mode) {
        if(mode==0){
            return  bookDao.getAllBooks(curPage,pageSize);
        }
        return bookDao.getBooks(curPage,pageSize);
    }

    @Override
    public Book getBook(String isbn) {
        return bookDao.getBook(isbn);
    }

    @Override
    public void borrowBook(String isbn, int readerId) {
        BorrowBook item=new BorrowBook();
        Book book=new Book();
        book.setIsbn(isbn);

        item.setBook(book);

        Reader reader=new Reader();
        reader.setId(readerId);
        item.setReader(reader);
        Date date = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR, 1440);
        Date date1=nowTime.getTime();
        item.setBorrowDate(date);
        item.setReturnDate(date1);

        borrowBookDao.insertBorrowBook(item);
        bookDao.updateBookNum(isbn);
    }

    @Override
    public ArrayList<BorrowBook> getBorrowBooks(int id) {
        return borrowBookDao.getBorrowBooks(id);
    }

    @Override
    public void returnBook(int id,String isbn, int readerId) {
        borrowBookDao.updateBorrowBookStatus(id,readerId);
        bookDao.updateBookNum2(isbn);
    }

    @Override
    public void enableBook(String isbn) {
        bookDao.updateBookStatus(isbn,1);
    }

    @Override
    public void disableBook(String isbn) {
        bookDao.updateBookStatus(isbn,0);
    }

    @Override
    public ArrayList<Category> getCategory() {
        return bookDao.getCategory();
    }

    @Override
    public void addBook(Book book) {
        bookDao.insertBook(book);
    }

    @Override
    public void updateBook(String originBook, Book book) {
        bookDao.updateBook(originBook,book);
    }
}
