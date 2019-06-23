package com.bookmngsys.service;

import com.bookmngsys.po.Reader;

import java.util.ArrayList;

public interface ReaderService {
    Reader getReader(int id);
    void updateReader(Reader reader);
    ArrayList<Reader> getReaders(int curPage,int pageSize);
}
