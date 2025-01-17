package ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/ex1/in.txt"));
        List<Parabola> parabole=new ArrayList<Parabola>();
        while(sc.hasNextLine()) {
            Parabola p=new Parabola(sc.nextInt(), sc.nextInt(),sc.nextInt());
            parabole.add(p);
        }
        sc.close();

        for(Parabola p:parabole) {
            double[] varf=p.getVarf();
            System.out.println(p);
            System.out.println("Varful:( "+varf[0]+" , "+varf[1]+" )");
        }
        if(parabole.size()>2) {
            Parabola p1=parabole.get(0);
            Parabola p2=parabole.get(1);
            double[] mijloc=Parabola.mijlocDreapta2(p1,p2);
            double lungime=Parabola.lungimeSegment2(p1,p2);
            System.out.println("Coordonatele mijlocului care le uneste:("+mijloc[0]+" , "+mijloc[1]+" )");
            System.out.println("Lungimea segmentului care le uneste: "+lungime);
        }
    }
}
