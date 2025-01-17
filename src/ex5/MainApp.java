package ex5;

import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
        Random rand = new Random();
        int a=1,b=1,c,esteTermen=0;
        int nr=rand.nextInt(20)+1;
        if(nr==1)
            System.out.println("Numarul "+nr+" apartine sirului lui Fibonacci");
        else
        {
            do {
                c=a+b;
                a=b;
                b=c;
                if(nr==c)
                {
                    esteTermen=1;
                    break;
                }
            }while (c<=nr);
            if (esteTermen==0)
                System.out.println("Numarul "+nr+" nu apartine sirului lui Fibonacci");
            else
                System.out.println("Numarul "+nr+" apartine sirului lui Fibonacci");
        }
    }
}
