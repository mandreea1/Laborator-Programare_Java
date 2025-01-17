package ex2;

import java.time.LocalDate;

public class Produs {
    private String nume;
    private double pret;
    private int cantitate;
    private LocalDate expirare;

    private static double incasari=0;

    public static double getIncasari() { return incasari; }
    public static void adaugaIncasari(double val){incasari+=val;}

    public Produs(String nume, double pret, int cantitate, LocalDate expirare) {
        this.nume = nume;
        this.pret = pret;
        this.cantitate = cantitate;
        this.expirare = expirare;
    }
    public Produs()
    {}
    public String getNume() { return nume; }
    public double getPret() { return pret; }
    public int getCantitate() { return cantitate; }
    public LocalDate getExpirare() { return expirare; }
    public void setNume(String nume) { this.nume = nume; }
    public void setPret(double pret) { this.pret = pret; }
    public void setCantitate(int cantitate) { this.cantitate = cantitate; }
    public void setExpirare(LocalDate expirare) { this.expirare = expirare; }

    @Override
    public String toString() {
        return nume+" "+pret+" "+cantitate+" "+expirare;
    }
}
