package ex3;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Introduceti sirul de caractere: ");
        StringBuilder sir=new StringBuilder(sc.nextLine());

        System.out.println("Introduceti sirul care doriti sa fie inserat: ");
        StringBuilder sir_inserat=new StringBuilder(sc.nextLine());

        System.out.println("Introduceti pozitia la care doriti sa inserati sirul: ");
        int poz=sc.nextInt();
        sc.nextLine();

        if(poz>=0 && poz<sir.length())
        {
            sir.insert(poz, sir_inserat);
            System.out.println("Sirul 1 complet dupa inserare: "+sir.toString());
        }
        else
        {
            System.out.println("Pozitia data nu e buna!");
        }

        System.out.println("Din ce sir doriti sa stergeti?(1-primul sir,2-al 2 lea sir)");
        int opt=sc.nextInt();
        System.out.println("Introduceti pozita de inceput a stergerii:");
        int poz_stergere=sc.nextInt();
        System.out.println("Introduceti numarul de caractere de sters:");
        int nr_caractere=sc.nextInt();
        if(opt==1)
        {
            if(poz_stergere>=0 && poz_stergere+nr_caractere<sir.length())
            {
                sir.delete(poz_stergere, poz_stergere+nr_caractere);
                System.out.println("Sirul complet dupa stergere: "+sir.toString());
            }
            else
                System.out.println("Pozitia sau numarul de caractere nu e buna!");
        }
        else if(opt==2)
        {
            if(poz_stergere>=0 && poz_stergere+nr_caractere<sir_inserat.length())
            {
                sir_inserat.delete(poz_stergere, poz_stergere+nr_caractere);
                System.out.println("Sirul  complet dupa stergere: "+sir_inserat.toString());
            }
            else
                System.out.println("Pozitia sau numarul de caractere nu e buna!");
        }
        else {
            System.out.println("Optiune invalida!");
        }
        sc.close();
    }
}
