package ex;

/**
 * O firmă comercializează echipamente electronice.
 * @author student
 * @version 1
 * @since 2025
 */

import java.io.Serializable;

public class Echipament implements Serializable {
    private String denumire;
    private int nr_inv;
    private int pret;
    private String zona_mag;
    private StatusEchipament status;

    public Echipament() {}

    /**
     * Constructorul clasei Echipament
     * @param denumire denumirea echipamentului
     */
    public Echipament(String denumire, int nr_inv, int pret,String zona_mag, StatusEchipament status) {
            this.denumire = denumire;
            this.nr_inv = nr_inv;
            this.pret = pret;
            this.zona_mag = zona_mag;
            this.status = status;
        }

    /**
     *Getter care da acces de citire a variabilei membre care contine denumirea
     *echipamentului
     * @return Denumirea echipamentului
     */
    public String getDenumire() {return denumire;}
    /**
     *Getter care da acces de citire a variabilei membre care contine numarul de inventar
     *al echipamentului
     * @return Numar inventar al echipamentului
     */
    public int getNr_inv() {return nr_inv;}
    public int getPret() {return pret;}
    public String getZona_mag() {return zona_mag;}
    public StatusEchipament getStatus() {return status;}
    public void setDenumire(String denumire) {this.denumire = denumire;}
    public void setNr_inv(int nr_inv) {this.nr_inv = nr_inv;}
    public void setPret(int pret) {this.pret = pret;}
    public void setZona_mag(String zona_mag) {this.zona_mag = zona_mag;}
    public void setStatus(StatusEchipament status) {this.status = status;}

    @Override
    public String toString() {
        return denumire+";"+nr_inv+";"+pret+";"+zona_mag+";"+status;
    }
}
