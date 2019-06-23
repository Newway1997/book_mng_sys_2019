package com.bookmngsys.service.impl;

import com.bookmngsys.dao.ReaderDao;
import com.bookmngsys.po.Reader;
import com.bookmngsys.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReaderServiceImpl implements ReaderService {
    @Autowired
    ReaderDao readerDao;
    @Override
    public Reader getReader(int id) {
        return readerDao.getReader(id);
    }

    @Override
    public void updateReader(Reader reader) {
        readerDao.updateReader(reader);
    }

    @Override
    public ArrayList<Reader> getReaders(int curPage,int pageSize) {
        return readerDao.getReaders(curPage,pageSize);
    }
}
