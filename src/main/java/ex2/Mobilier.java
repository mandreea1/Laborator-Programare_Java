package ex2;

import java.util.List;

public class Mobilier {
    private String nume;
    private List<Placa> placi;

    public Mobilier(){}
    /**
     *Constructorul clasei Mobilier
     * @param  nume numele Mobilierului
     */
    public Mobilier(String nume, List<Placa> placi) {
        this.nume = nume;
        this.placi = placi;
    }
    /**
     *getter pentru a returna numele mobilierului
     * @return Nume Mobilier
     */
    public String getNume() {return nume;}
    public List<Placa> getPlaci() {return placi;}
    public void setPlaci(List<Placa> placi) {this.placi = placi;}
    public void setNume(String nume) {this.nume = nume;}

    /**
     *Afisarea Mobilierului cu caracteristicile in parte
     * @return Afis mobilier
     */
    @Override
    public String toString() {
        return nume+"  "+placi;
    }
}
