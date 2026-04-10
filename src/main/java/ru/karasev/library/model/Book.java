package ru.karasev.library.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Book {

    private int id;
    @NotEmpty(message = "Поле не должно быть пустым")
    private String title;

    @NotEmpty(message = "Поле не должно быть пустым")
    private String author;

    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 0, message = "Книг до нашей эры у нас, к сожалению, нет")
    @Max(value = 2026, message = "Книг из будущего у нас, к сожалению, нет")
    private int year;

    private Integer personId;

    private String personName;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
