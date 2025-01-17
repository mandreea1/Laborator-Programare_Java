package ex2;
import ex2.Orientare;
import java.util.Arrays;
import java.util.List;

public class Placa {
    private String descriere;
    private int lungime;
    private int latime;
    private Orientare orientare;
    private List<Boolean> canturi;
    private int nr_bucati;

    public Placa() {}
    /**
     *Constructorul clasei Placa
     * @param descriere Descrierea Placii
     */
    public Placa(String descriere, int lungime, int latime, Orientare orientare,List<Boolean> canturi, int nr_bucati) {
        this.descriere=descriere;
        this.lungime=lungime;
        this.latime=latime;
        this.orientare=orientare;
        this.canturi=canturi;
        this.nr_bucati=nr_bucati;
    }
    /**
     *getter pentru a returna descrierea placii
     * @return Descriere placa
     */
    public String getDescriere() {return descriere;}
    public int getLungime() {return lungime;}
    public int getLatime() {return latime;}
    public Orientare getOrientare() {return orientare;}
    public List<Boolean> getCanturi() {return canturi;}
    public int getNr_bucati() {return nr_bucati;}
    public void setDescriere(String descriere) {this.descriere = descriere;}
    public void setLungime(int lungime) {this.lungime = lungime;}
    public void setLatime(int latime) {this.latime = latime;}
    public void setOrientare(Orientare orientare) {this.orientare = orientare;}
    public void setCanturi(List<Boolean> canturi) {this.canturi = canturi;}
    public void setNr_bucati(int nr_bucati) {this.nr_bucati = nr_bucati;}
    public int get_Arie(){return lungime*latime;}

    /**
     *Afisarea placii cu caracteristicile in parte
     * @return Afis placa
     */
    @Override
    public String toString() {
        return descriere+" "+lungime+" "+latime+" "+orientare+" "+ canturi +" "+nr_bucati;
    }

}
