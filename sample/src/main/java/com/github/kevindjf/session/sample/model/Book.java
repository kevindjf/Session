package com.github.kevindjf.session.sample.model;

/**
 * Created by kevindejesusferreira on 05/05/15.
 */
public class Book {

    String name;
    int number;

    public Book(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "nameBook='" + name ;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;
        return !(getName() != null ? !getName().equals(book.getName()) : book.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getNumber();
        return result;
    }
}