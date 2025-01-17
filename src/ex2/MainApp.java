package ex2;

import java.io.*;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws IOException {
        String nume_fisier="src/ex2/in.txt";
        int suma=0,minim=Integer.MAX_VALUE,maxim=0,contor=0;
        float medie_aritmetica=0;
        Scanner scanner = new Scanner(new File(nume_fisier));
        while(scanner.hasNext())
        {
            int x=Integer.parseInt(scanner.next());
            suma+=x;
            if(x<minim)
                minim=x;
            if(x>maxim)
                maxim=x;
            contor++;
        }
        medie_aritmetica=(float)suma/contor;
        System.out.println("Suma numerelor este: "+suma);
        System.out.println("Media aritmetica este: "+medie_aritmetica);
        System.out.println("Valoarea minima este: "+minim);
        System.out.println("Valoarea maxima este: "+maxim);
        Writer wr=new FileWriter("src/ex2/out.txt");
        wr.write("Suma numerelor este: "+suma);
        wr.write("\nMedia aritmetica este: "+medie_aritmetica);
        wr.write("\nValoarea minima este: "+minim);
        wr.write("\nValoarea maxima este: "+maxim);
        wr.close();
    }
}
