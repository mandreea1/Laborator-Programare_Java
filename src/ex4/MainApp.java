package ex4;

import java.util.Random;

public class MainApp {

        public static int cmmdc(int a, int b)
        {
            while(b!=0)
            {
                int r=a%b;
                a=b;
                b=r;
            }
            return a;
        }
    public static void main(String[] args) {
        Random rand = new Random();
        int a=rand.nextInt(30)+1;
        int b=rand.nextInt(30)+1;
        int calcul_cmmdc=cmmdc(a,b);
        System.out.println("Cel mai mare divizor comun dintre "+a+" si "+b+" este: "+calcul_cmmdc);
    }
}
