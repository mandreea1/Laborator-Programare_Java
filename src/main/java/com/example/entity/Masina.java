package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="masiniDATAJPA")
public class Masina {
    @Id
    private String numarInmatriculare;
    private String marca;
    private int anulFabricatiei;
    private String culoare;
    private long kilometri;

    public Masina() {}
    public Masina(String numarInmatriculare, String marca, int anulFabricatiei, String culoare, long kilometri) {
        this.numarInmatriculare = numarInmatriculare;
        this.marca = marca;
        this.anulFabricatiei = anulFabricatiei;
        this.culoare = culoare;
        this.kilometri = kilometri;
    }

    public String getNumarInmatriculare() {
        return numarInmatriculare;
    }

    public void setNumarInmatriculare(String numar_inmatriculare) {
        this.numarInmatriculare = numar_inmatriculare;
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
        return anulFabricatiei;
    }

    public void setAnulFabricatiei(int anul_fabricatiei) {
        this.anulFabricatiei = anul_fabricatiei;
    }

    public long getKilometri() {
        return kilometri;
    }

    public void setKilometri(long nr_km) {
        this.kilometri = nr_km;
    }

    @Override
    public String toString() {
        return "\n"+numarInmatriculare+", "+marca+", "+anulFabricatiei+", "+culoare+", "+kilometri+"\n";
    }

}
