package com.example.lab10;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carti")
public class Carte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID auto-increment
    private int id;

    private String isbn;
    private String titlul;
    private String autorul;

    public Carte() {}

    public Carte(String isbn, String titlul, String autorul) {
        this.isbn = isbn;
        this.titlul = titlul;
        this.autorul = autorul;
    }

    // Getters și setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitlul() {
        return titlul;
    }

    public void setTitlul(String titlul) {
        this.titlul = titlul;
    }

    public String getAutorul() {
        return autorul;
    }

    public void setAutorul(String autorul) {
        this.autorul = autorul;
    }
}