package com.app.model;

public class Author {
    private int id;
    private String authorName;
    private String phone;
    private String address;

    public Author(){

    }

    public Author(int id) {
        this.id = id;
    }

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author(String authorName, String phone, String address) {
        this.authorName = authorName;
        this.phone = phone;
        this.address = address;
    }

    public Author(int id, String authorName, String phone, String address) {
        this.id = id;
        this.authorName = authorName;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AuthorModel{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
