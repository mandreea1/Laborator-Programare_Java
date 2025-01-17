package ex;

import java.io.Serializable;

public class Imprimanta extends Echipament implements Serializable {
    private int ppm;
    private String rezolutie;
    private int p_car;
    private ModTiparire modTiparire;

    public Imprimanta(){}

    public Imprimanta(String denumire, int nr_inv, int pret,String zona_mag, StatusEchipament status,int ppm, String rezolutie,int p_car ,ModTiparire modTiparire) {
        super(denumire,nr_inv,pret,zona_mag,status);
        this.ppm = ppm;
        this.rezolutie = rezolutie;
        this.p_car = p_car;
        this.modTiparire = modTiparire;
    }
    public int getPpm() {return ppm;}
    public String getRezolutie() {return rezolutie;}
    public int getP_car() {return p_car;}
    public ModTiparire getModTiparire() {return modTiparire;}
    public void setPpm(int ppm) {this.ppm = ppm;}
    public void setRezolutie(String rezolutie){this.rezolutie = rezolutie;}
    public void setP_car(int p_car){this.p_car = p_car;}
    public void setModTiparire(ModTiparire modTiparire) {this.modTiparire = modTiparire; }

    @Override
    public String toString() {
        return super.toString()+";imprimanta" + ";" + ppm + ";" + rezolutie + ";" + p_car + ";" + modTiparire;
    }
}
