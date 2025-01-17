package ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws FileNotFoundException {
        String nume_fisier="src/ex1/judete_in.txt";
        Scanner sc = new Scanner(new File(nume_fisier));
        int numar_linii=0, index=0;
        while(sc.hasNextLine()) {
            sc.nextLine();
            numar_linii++;
        }
        String [] array=new String[numar_linii];
        sc=new Scanner(new File(nume_fisier));
        while(sc.hasNextLine()) {
            array[index++]=sc.nextLine();
        }
        Arrays.sort(array);
        for(String str:array) {
            System.out.println(str);
        }

        Scanner sc1=new Scanner(System.in);
        System.out.println("Introduceti judetul pe care doriti sa il cautati: ");
        String judet=sc1.nextLine();
        int rezultat_cautare=Arrays.binarySearch(array,judet)+1;
        if(rezultat_cautare>=0) {
            System.out.println("Judetul "+judet+" se afla pe pozitia "+rezultat_cautare);
        }
        else {System.out.println("Judetul nu se afla in lista!");}
    }
}
