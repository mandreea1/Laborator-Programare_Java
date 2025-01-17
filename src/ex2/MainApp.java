package ex2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws IOException {
        String fisier_intrare="src/ex2/cantec_in.txt";
        Scanner scan = new Scanner(new File(fisier_intrare));
        Writer writer = new FileWriter("src/ex2/cantec_out.txt");
        List<Vers> versuri=new ArrayList<>();

        while(scan.hasNextLine()) {
            Vers vers=new Vers(scan.nextLine());
            versuri.add(vers);
        }
        scan.close();
        System.out.println("Dati gruparea aleasa: ");
        Scanner sc = new Scanner(System.in);
        String grupare = sc.nextLine();
        sc.close();
        Vers[] cantec=versuri.toArray(new Vers[0]);

        for(Vers vers:cantec) {
            vers.majorare_Vers();
            writer.write(vers.toString() + " ");
            writer.write(vers.Get_numarCuvinte() + " cuvinte ");
            writer.write(vers.Get_numarVocale() + " vocale ");
            if(vers.setSteluta(grupare))
            {
                writer.write("* ");
            }
            writer.write("\n");
        }
        writer.close();
    }
}
