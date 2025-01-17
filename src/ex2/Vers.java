package ex2;

import java.util.Random;
import java.util.Scanner;

public class Vers {
    String linie;
    Vers(String linie) {
        this.linie = linie;
    }
    public int Get_numarCuvinte()
    {
        String [] cuvinte = linie.split(" ");
        return cuvinte.length;
    }
    public int Get_numarVocale()
    {
        int numar_vocale=0;
        for(char c: linie.toLowerCase().toCharArray())
            if("aeiou".indexOf(c) != -1)
                numar_vocale++;
        return numar_vocale;
    }
    public void majorare_Vers()
    {
        Random random=new Random();
        double x=random.nextDouble();
        if(x<0.1)
        {
            linie=linie.toUpperCase();
        }
    }
    public boolean setSteluta(String grupare)
    {
        return linie.endsWith(grupare);
    }
    @Override
    public String toString() {
        return linie;
    }
}
