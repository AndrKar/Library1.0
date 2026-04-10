package ru.karasev.library.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Person {

    private int id;

    @NotEmpty(message = "Поле не должно быть пустым")
    private String name;

    @Min(value = 1900, message = "Так долго не живут")
    @Max(value = 2026, message = "Вы из будущего?")
    private int year;


    public Person(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
