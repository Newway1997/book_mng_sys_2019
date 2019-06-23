package com.bookmngsys.dao;

import com.bookmngsys.po.Reader;

import java.util.ArrayList;

public interface ReaderDao {
    Reader getReader(int id);
    void updateReader(Reader reader);
    ArrayList<Reader> getReaders(int curPage,int pageSize);
}
