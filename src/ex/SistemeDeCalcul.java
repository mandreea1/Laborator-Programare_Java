package ex;

import java.io.Serializable;

public class SistemeDeCalcul extends Echipament implements Serializable {
    private String tip_mon;
    private double vit_proc;
    private int c_hdd;
    private SistemeOperare sisteme;
    public SistemeDeCalcul() {}
    public SistemeDeCalcul(String denumire, int nr_inv, int pret,String zona_mag, StatusEchipament status,String tip_mon, double vit_proc, int c_hdd, SistemeOperare sisteme) {
        super(denumire, nr_inv, pret, zona_mag, status);
        this.tip_mon = tip_mon;
        this.vit_proc = vit_proc;
        this.c_hdd = c_hdd;
        this.sisteme = sisteme;
    }
    public String getTip_mon() {return tip_mon;}
    public double getVit_proc() {return vit_proc;}
    public int getC_hdd() {return c_hdd;}
    public SistemeOperare getSisteme() {return sisteme;}
    public void setTip_mon(String tip_mon) {this.tip_mon = tip_mon;}
    public void setVit_proc(double vit_proc) {this.vit_proc = vit_proc;}
    public void setC_hdd(int c_hdd) {this.c_hdd = c_hdd;}
    public void setSisteme(SistemeOperare sisteme) {this.sisteme = sisteme;}

    @Override
    public String toString() {
        return super.toString()+";sistem de calcul"+";"+tip_mon+";"+vit_proc+";"+c_hdd+";"+sisteme;
    }
}
