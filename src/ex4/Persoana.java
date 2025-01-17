package ex4;

import java.time.LocalDate;
import java.time.Period;

public class Persoana {
    private String nume;
    private String cnp;
    public Persoana(String nume, String cnp) {
        this.nume = nume;
        this.cnp = cnp;
    }
    public String getNume() { return nume; }
    public String getCnp() { return cnp; }
    public void setNume(String nume) { this.nume = nume; }
    public void setCnp(String cnp) { this.cnp = cnp; }

    public int getVarsta()
    {
        int an=Integer.parseInt((cnp.substring(1,3)));
        int luna=Integer.parseInt((cnp.substring(3,5)));
        int zi=Integer.parseInt((cnp.substring(5,7)));
        char sexul=cnp.charAt(0);
        int secol;
        if(sexul=='1' || sexul=='2')
            secol=1900;
        else if(sexul=='5' || sexul=='6')
            secol=2000;
        else return 0;
        an+=secol;

        LocalDate dn=LocalDate.of(an,luna,zi);
        LocalDate date=LocalDate.now();

        int varsta= Period.between(dn,date).getYears();
        return varsta;
    }

    public static boolean validCnp(String cnp)
    {
        if(cnp.length()!=13)
            return false;
        if(!cnp.chars().allMatch(Character::isDigit))
            return false;
        char cifra=cnp.charAt(0);
        if(cifra!='1' && cifra!='2' && cifra!='5' && cifra!='6')
            return false;

        int[] constante={2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9};
        int suma=0;
        for(int i=0;i<12;i++)
        {
            suma+=Character.getNumericValue(cnp.charAt(i))*constante[i];
        }
        int cifra_Control=suma%11;
        if(cifra_Control==10)
        {
            cifra_Control=1;
        }
        int cifra_cnp=Character.getNumericValue(cnp.charAt(12));
        return cifra_cnp==cifra_Control;
    }
    @Override
    public String toString() {
        return "Persoana:" + nume + ", " + cnp +", "+ getVarsta()+" ani";
    }
}
