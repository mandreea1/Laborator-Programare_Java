package ex2;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void afisExpiate(List<Produs>produse)
    {
        LocalDate today = LocalDate.now();
        for(Produs produs: produse)
            if(today.isAfter(produs.getExpirare()))
            {
                System.out.println(produs);
            }

    }

    public static void vanzareProdus(List<Produs> produse)
    {
        System.out.println("Dati numele si cantitatea produsului pe care doriti sa il cumparati");
        Scanner sc = new Scanner(System.in);
        String nume=sc.nextLine();
        int cantitate=sc.nextInt();
        boolean ok=false;
        for (int i = produse.size() - 1; i >= 0; i--)
        {
            if(produse.get(i).getNume().equals(nume))
            {
                if(produse.get(i).getCantitate()>=cantitate)
                {
                    produse.get(i).setCantitate(produse.get(i).getCantitate()-cantitate);
                    Produs.adaugaIncasari(produse.get(i).getPret()*cantitate);
                }
            }
               if(produse.get(i).getCantitate()==0)
                 produse.remove(i);
            ok=true;

        }
        if(ok==false)
        {
            System.out.println("Nu exista produs cu asa nume in lista!");
        }
    }

    public static void prodMinime(List<Produs> produse)
    {
        List<Produs>prodMin=new ArrayList<Produs>();
        double minim=produse.get(0).getPret();
        for(int i=produse.size()-1; i>=0; i--)
        {
            if(produse.get(i).getPret()<=minim)
            {
                prodMin.add(produse.get(i));
            }
        }
        for(Produs produs: prodMin)
            System.out.println(produs);
    }

    public static void salvareProduse(List<Produs> produse) throws IOException {
        System.out.println("Dati cantitatea sub care doriti sa salvati produsele:");
        Scanner sc = new Scanner(System.in);
        Writer writer=new FileWriter("src/ex2/out.csv");
        int cantitate=sc.nextInt();
        for(Produs produs: produse)
        {
            if(produs.getCantitate()<cantitate)
                writer.write(produs.getNume()+","+produs.getPret()+","+produs.getCantitate()+","+produs.getExpirare()+"\n");
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        int opt;
        List<Produs> produse=new ArrayList<Produs>();
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(new File("src/ex2/produse.csv"));
        while(sc1.hasNextLine())
        {
            String []linie=sc1.nextLine().split(",");
            String denumire=linie[0].trim();
            double pret=Double.parseDouble(linie[1].trim());
            int cantitate=Integer.parseInt(linie[2].trim());
            LocalDate expirare=LocalDate.parse(linie[3].trim());
            produse.add(new Produs(denumire,pret,cantitate,expirare));
        }
        do {
            System.out.println("Dati optiunea:");
            System.out.println("0.Iesire");
            System.out.println("1.Afisarea produselor");
            System.out.println("2.Afisarea produselor expirate");
            System.out.println("3.Vanzarea unui produs");
            System.out.println("4.Afisarea produselor cu pret minim");
            System.out.println("5.Salvarea produselor care au o cantitate mai mica decat una data intr un fisier");
            opt=sc.nextInt();
            switch(opt)
            {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    for(Produs produs:produse)
                        System.out.println(produs);

                    System.out.println("Incasari totale:"+Produs.getIncasari());
                    break;
                case 2:
                    afisExpiate(produse);
                    break;
                case 3:
                    vanzareProdus(produse);
                    break;
                case 4:
                    prodMinime(produse);
                    break;
                case 5:
                    salvareProduse(produse);
                    break;
                default:
            }
        }while(true);
    }
}
