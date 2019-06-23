package com.bookmngsys.controller;

import com.bookmngsys.po.Book;
import com.bookmngsys.po.BorrowBook;
import com.bookmngsys.po.Category;
import com.bookmngsys.po.User;
import com.bookmngsys.service.BookService;
import com.bookmngsys.tool.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/api/book/getBooks")
    @ResponseBody
    public Response getBooks(HttpServletRequest request, HttpSession session){
        //按页码返回书籍
        String curPage=request.getParameter("curPage");
        String pageSize=request.getParameter("pageSize");
        if(curPage==null){
            curPage="0";
        }
        if(pageSize==null){
            pageSize="24";
        }
        User user =(User) session.getAttribute("USER_SESSION");
        ArrayList<Book>books;
        if(user!=null&&user.getType()==0){
            books=bookService.getBooks(Integer.parseInt(curPage),Integer.parseInt(pageSize),0);
        }else{
           books=bookService.getBooks(Integer.parseInt(curPage),Integer.parseInt(pageSize),1);
        }

        return new Response().success(books);
    }
    @RequestMapping("/api/book/getBookDetail")
    @ResponseBody
    public Response getBook(HttpServletRequest request, HttpSession session){
        //按页码返回书籍
        String isbn=request.getParameter("isbn");
        if(isbn==null){
            return new Response().failure("没有isbn");
        }else{
            Book book=bookService.getBook(isbn);
            return new Response().success(book);
        }
    }

    @RequestMapping("/api/book/borrowBook")
    @ResponseBody
    public Response borrowBook(HttpServletRequest request, HttpSession session){
        //按isbn借阅图书
        User user =(User) session.getAttribute("USER_SESSION");
        String isbn=request.getParameter("isbn");
        if(isbn==null){
            return new Response().failure("没有isbn");
        }else{
            bookService.borrowBook(isbn,user.getId());
            return new Response().success();
        }
    }
    @RequestMapping("/api/book/returnBook")
    @ResponseBody
    public Response returnBook(HttpServletRequest request, HttpSession session){
        //按isbn借阅图书
        User user =(User) session.getAttribute("USER_SESSION");
        String id=request.getParameter("id");
        String isbn=request.getParameter("isbn");
        if(id==null||isbn==null){
            return new Response().failure("未找到该条记录");
        }else{
            bookService.returnBook(Integer.parseInt(id),isbn,user.getId());
            return new Response().success();
        }
    }
    @RequestMapping("/api/borrowBook/getBorrowBooks")
    @ResponseBody
    public Response getBorrowBooks( HttpSession session){
        User user =(User)session.getAttribute("USER_SESSION");
        try {
            ArrayList<BorrowBook> borrowBooks= bookService.getBorrowBooks(user.getId());
            return new Response().success(borrowBooks);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new Response().failure("出错");
    }


    @RequestMapping("/api/book/enableBook")
    @ResponseBody
    public Response enableBook(HttpServletRequest request, HttpSession session){
        //按isbn借阅图书
        User user =(User) session.getAttribute("USER_SESSION");
        String isbn=request.getParameter("isbn");

        if(isbn==null||user.getType()!=0){
            return new Response().failure("未找到该条记录");
        }else{
            bookService.enableBook(isbn);
            return new Response().success();
        }
    }
    @RequestMapping("/api/book/disableBook")
    @ResponseBody
    public Response disableBook(HttpServletRequest request, HttpSession session) throws IllegalStateException,IOException {
        //按isbn借阅图书
        User user =(User) session.getAttribute("USER_SESSION");
        String isbn=request.getParameter("isbn");

        if(isbn==null||user.getType()!=0){
            return new Response().failure("未找到该条记录");
        }else{
            bookService.disableBook(isbn);
            return new Response().success();
        }
    }
    @RequestMapping("/api/book/getCategory")
    @ResponseBody
    public Response getCategory(HttpServletRequest request, HttpSession session){
        //按isbn借阅图书
        User user =(User) session.getAttribute("USER_SESSION");
        ArrayList<Category> categories=bookService.getCategory();
        return new Response().success(categories);

    }
    //文件上传
    @RequestMapping(value = "/api/book/uploadBook",method = RequestMethod.POST)
    @ResponseBody
    public Response uploadBook(HttpServletRequest request,@RequestParam(value="img", required = false) MultipartFile file)
    {
        String isbn=request.getParameter("isbn");
        String categoryId=request.getParameter("categoryId");
        String bookName=request.getParameter("bookName");
        String author=request.getParameter("author");
        String publisher=request.getParameter("publisher");
        String publishDate=request.getParameter("publishDate");
        String unitPrice=request.getParameter("unitPrice");
        String summary=request.getParameter("summary");
        int allNum=Integer.parseInt(request.getParameter("allNum"));
        String imgUrl=file.getOriginalFilename();
        String tags=request.getParameter("tags");
        int status=1;
        int lastNum=allNum;
        Book book=new Book(isbn, Integer.parseInt(categoryId),"", bookName, author, publisher, new Date(publishDate),Float.parseFloat(unitPrice), imgUrl,summary, allNum, tags, lastNum, status);
        bookService.addBook(book);
        String path="E:\\WorkSpace\\book_mng_sys_2019\\front-cli\\static\\img\\"+file.getOriginalFilename();
        try{
            file.transferTo(new File(path));
        }catch (IOException ex){
            ex.printStackTrace();
            return new Response().failure("图片读取错误");
        }finally {
            return new Response().success();
        }

    }
    @RequestMapping(value = "/api/book/updateBook",method = RequestMethod.POST)
    @ResponseBody
    public Response updateBook(HttpServletRequest request,@RequestParam(value="img", required = false) MultipartFile file)
    {
        String originIsbn=request.getParameter("oringnIsbn");
        String isbn=request.getParameter("isbn");
        String categoryId=request.getParameter("categoryId");
        String bookName=request.getParameter("bookName");
        String author=request.getParameter("author");
        String publisher=request.getParameter("publisher");

        String tmpDate=request.getParameter("publishDate");
        Date publishDate;
        publishDate=new Date(tmpDate);

        String unitPrice=request.getParameter("unitPrice");
        String summary=request.getParameter("summary");

        int allNum=Integer.parseInt(request.getParameter("allNum"));
//        String imgUrl=file.getOriginalFilename();
        String imgUrl="";
        if(file!=null)
        {
            imgUrl=file.getOriginalFilename();
        }
            String tags=request.getParameter("tags");
        int status=1;
        int lastNum=allNum;

        Book book=new Book(isbn, Integer.parseInt(categoryId),"", bookName, author, publisher, publishDate,Float.parseFloat(unitPrice), imgUrl,summary, allNum, tags, lastNum, status);
        bookService.updateBook(originIsbn,book);

        if(file!=null){
            String path="E:\\WorkSpace\\book_mng_sys_2019\\front-cli\\static\\img\\"+file.getOriginalFilename();
            try{
                file.transferTo(new File(path));
            }catch (IOException ex){
                ex.printStackTrace();
                return new Response().failure("图片读取错误");
            }finally {
                return new Response().success();
            }
        }

        return new Response().success();
    }
}
