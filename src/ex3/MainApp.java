package ex3;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n,k=0;
        System.out.println("Dati numarul: ");
        n=scanner.nextInt();
        System.out.println("Divizorii sunt: ");
        if(n==1)
        {
            System.out.println("1"); //caz special
            return;
        }
        for(int i=2;i<n;i++)
        {
            if(n%i==0)
        {
            if(k==0)
            {System.out.println("1 ");}
            System.out.println(i+" ");
            k++;
        }
        }
        if(k==0)
        {System.out.println("Numarul "+n+" este prim");}
        else
        {System.out.println(n);}
    }
}
