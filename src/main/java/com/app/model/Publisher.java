package com.app.model;

public class Publisher {

    private int id;
    private String publisherName;
    private String publisherPhone;

    // Constructor

    public Publisher(int id){
        this.id = id;
    }
    public Publisher(String publisherName){
        this.publisherName = publisherName;
    }

    public Publisher(int id, String publisherName, String publisherPhone) {
        this.id = id;
        this.publisherName = publisherName;
        this.publisherPhone = publisherPhone;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getPublisherPhone() {
        return publisherPhone;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }

    // toString method
    @Override
    public String toString() {
        return "PublisherModel{" +
                "id=" + id +
                ", publisherName='" + publisherName + '\'' +
                ", publisherPhone='" + publisherPhone + '\'' +
                '}';
    }
}
