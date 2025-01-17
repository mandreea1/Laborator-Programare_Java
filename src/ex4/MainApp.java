package ex4;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduceti numarul de persoane: ");
        int nr = sc.nextInt();
        sc.nextLine();
        Persoana[] persoane = new Persoana[nr];
        for(int i = 0; i < nr; i++) {
            System.out.println("Introduceti numele persoanei"+(i+1)+": ");
            String nume = sc.nextLine();
            String cnp;
            do {
                System.out.println("Introduceti CNP ul persoanei"+(i+1)+": ");
                cnp = sc.nextLine();
                if(!Persoana.validCnp(cnp))
                {
                    System.out.println("CNP invalid!");
                }
            }while(!Persoana.validCnp(cnp));
                persoane[i]=new Persoana(nume,cnp);
        }
        System.out.println("Informatii despre persoanele introduse: ");

        for(Persoana p : persoane)
        {
            System.out.println(p);
        }
        sc.close();
    }
}
