package ex1;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        int lungime,latime,perimetru,arie;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Dati lungimea: ");
        lungime=scanner.nextInt();
        System.out.println("Dati latimea: ");
        latime=scanner.nextInt();
        perimetru=lungime+latime;
        arie=lungime*latime;
        System.out.println("Perimetrul este: "+perimetru);
        System.out.println("Aria este: "+arie);
    }
}
