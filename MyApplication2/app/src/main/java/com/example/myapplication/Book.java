package com.example.myapplication;

public class Book {
    private int id;
    private String title;
    private String price;
    private byte[] image;

    public Book(String title,String price, byte[] image, int id) {
        this.title = title;
        this.price = price;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String geTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String name) {
//        this.author = name;
//    }
//    public String getIsbn() {
//        return isbn;
//    }
//
//    public void setIsbn(String name) {
//        this.isbn = name;
//    }
//    public String getClasss() {
//        return classs;
//    }
//
//    public void setClasss(String name) {
//        this.classs = name;
//    }
//    public String getCondition() {
//        return condition;
//    }
//
//    public void setCondition(String name) {
//        this.condition = name;
//    }
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String name) {
//        this.phone = name;
//    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
