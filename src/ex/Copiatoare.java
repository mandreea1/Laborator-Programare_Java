package ex;

import java.io.Serializable;

public class Copiatoare extends Echipament implements Serializable {
    private int p_ton;
    private FormatCopiere formatCopiere;

    public Copiatoare(String denumire, int nr_inv, int pret,String zona_mag, StatusEchipament status,int p_ton, FormatCopiere formatCopiere) {
        super(denumire, nr_inv, pret, zona_mag, status);
        this.p_ton = p_ton;
        this.formatCopiere = formatCopiere;
    }
    public int getP_ton() {return p_ton;}
    public FormatCopiere getFormatCopiere() {return formatCopiere;}
    public void setP_ton(int p_ton) {this.p_ton = p_ton;}
    public void setFormatCopiere(FormatCopiere formatCopier) {this.formatCopiere = formatCopier;}

    @Override
    public String toString() {
        return super.toString()+";copiator"+";"+p_ton+";"+formatCopiere;
    }
}
