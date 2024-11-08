package com.app.model;

public class Book{
    private int id;
    private String title;
    private Author author;
    private Category category;
    private Publisher publisher;
    private int pages;
    private String edition;
    private int borrowerId;

    public Book(){

    }

    public Book(int id, String title, String edition) {
        this.id = id;
        this.title = title;
        this.edition = edition;
    }

    public Book(String title, int pages, String edition) {
        this.title = title;
        this.pages = pages;
        this.edition = edition;
    }

    public Book(String title, Author author, Category category, Publisher publisher, int pages, String edition){
        this.title = title;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.pages = pages;
        this.edition = edition;
    }

    public Book(int id, String title, Author author, Category category, Publisher publisher, int pages, String edition, int isArchived){
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.pages = pages;
        this.edition = edition;
        this.borrowerId = isArchived;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public int getPages() {
        return pages;
    }

    public String getEdition() {
        return edition;
    }

    public int getBorrowerId(){
        return borrowerId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setBorrowerId(int borrowerId){
        this.borrowerId = borrowerId;
    }

    @Override
    public String toString() {
        return  "=".repeat(50) +
                "\nBookModel{\n" +
                "\s\s\s\sid=" + id +
                "\n\s\s\s\stitle='" + title + '\'' +
                "\n\s\s\s\sauthorName ='" + author.getAuthorName() + '\'' +
                "\n\s\s\s\scategory='" + category.getCategoryName() + '\'' +
                "\n\s\s\s\spages=" + pages +
                "\n\s\s\s\sedition='" + edition + '\'' +
                "\n\s\s\s\sBorrower Id='" + borrowerId + '\'' +
                "\n}\n" +
                "=".repeat(50);

    }
}
