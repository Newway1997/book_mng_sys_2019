package com.bookmngsys.po;

import java.util.ArrayList;
import java.util.Date;

public class Book {
   private String isbn;
   private int categoryId;
   private String categoryName;
   private String bookName;
   private String author;
   private String publisher;
   private Date publishDate;
   private float unitPrice;
   private String imgUrl;
   private String summary;
   private int allNum;
   private String tags;
   private int lastNum;
   private int status=1;
public Book(){

}
   public Book(String isbn, int categoryId, String categoryName, String bookName, String author, String publisher, Date publishDate, float unitPrice, String imgUrl, String summary, int allNum,String tags, int lastNum, int status) {
      this.isbn = isbn;
      this.categoryId = categoryId;
      this.categoryName = categoryName;
      this.bookName = bookName;
      this.author = author;
      this.publisher = publisher;
      this.publishDate = publishDate;
      this.unitPrice = unitPrice;
      this.imgUrl = imgUrl;
      this.summary = summary;
      this.allNum = allNum;
      this.tags = tags;
      this.lastNum = lastNum;
      this.status = status;
   }

   public String getIsbn() {
      return isbn;
   }

   public void setIsbn(String isbn) {
      this.isbn = isbn;
   }

   public int getCategoryId() {
      return categoryId;
   }

   public void setCategoryId(int categoryId) {
      this.categoryId = categoryId;
   }

   public String getCategoryName() {
      return categoryName;
   }

   public void setCategoryName(String categoryName) {
      this.categoryName = categoryName;
   }

   public String getBookName() {
      return bookName;
   }

   public void setBookName(String bookName) {
      this.bookName = bookName;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public String getPublisher() {
      return publisher;
   }

   public void setPublisher(String publisher) {
      this.publisher = publisher;
   }

   public Date getPublishDate() {
      return publishDate;
   }

   public void setPublishDate(Date publishDate) {
      this.publishDate = publishDate;
   }

   public float getUnitPrice() {
      return unitPrice;
   }

   public void setUnitPrice(float unitPrice) {
      this.unitPrice = unitPrice;
   }

   public String getImgUrl() {
      return imgUrl;
   }

   public void setImgUrl(String imgUrl) {
      this.imgUrl = imgUrl;
   }

   public String getSummary() {
      return summary;
   }

   public void setSummary(String summary) {
      this.summary = summary;
   }

   public int getAllNum() {
      return allNum;
   }

   public void setAllNum(int allNum) {
      this.allNum = allNum;
   }

   public String getTags() {
      return tags;
   }

   public void setTags(String tags) {
      this.tags = tags;
   }

   public int getLastNum() {
      return lastNum;
   }

   public void setLastNum(int lastNum) {
      this.lastNum = lastNum;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }
}
