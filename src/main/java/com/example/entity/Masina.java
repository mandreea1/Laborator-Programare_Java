package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="masinijpa")
public class Masina {
    @Id
    private String numar_inmatriculare;
    private String marca;
    private int anul_fabricatiei;
    private String culoare;
    private long kilometri;

    public Masina() {}
    public Masina(String numar_inmatriculare, String marca, int anul_fabricatiei, String culoare, long kilometri) {
        this.numar_inmatriculare = numar_inmatriculare;
        this.marca = marca;
        this.anul_fabricatiei = anul_fabricatiei;
        this.culoare = culoare;
        this.kilometri = kilometri;
    }

    public String getNumar_Inmatriculare() {
        return numar_inmatriculare;
    }

    public void setNumar_inmatriculare(String numar_inmatriculare) {
        this.numar_inmatriculare = numar_inmatriculare;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public int getAnulFabricatiei() {
        return anul_fabricatiei;
    }

    public void setAnul_fabricatiei(int anul_fabricatiei) {
        this.anul_fabricatiei = anul_fabricatiei;
    }

    public long getKilometri() {
        return kilometri;
    }

    public void setKilometri(long nr_km) {
        this.kilometri = nr_km;
    }

    @Override
    public String toString() {
        return "\n"+numar_inmatriculare+", "+marca+", "+anul_fabricatiei+", "+culoare+", "+kilometri+"\n";
    }

}
