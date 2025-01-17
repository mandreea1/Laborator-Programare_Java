package ex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainApp {

    public static void modificareStare(List<Echipament>echipamente)
    {
        boolean ok=false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Dati numele echipamentului caruia doriti sa ii modificati starea:");
        String nume=sc.nextLine();
        System.out.println("Dati statusul cu care doriti sa modificati.(achizitionat,expus,vandut)");
        String status=sc.nextLine();
        for(Echipament e : echipamente)
            if(e.getDenumire().equals(nume))
            {
                e.setStatus(StatusEchipament.valueOf(status.toLowerCase()));
                ok=true;
                break;
            }
        if(ok==false)
            System.out.println("Nu s a gasit astfel de echipament");
    }

    public static void modificareFormatScriere(List<Echipament>echipamente)
    {
        boolean ok=false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Dati numele imprimantei caruia doriti sa ii modificati formatul de scriere:");
        String nume=sc.nextLine();
        System.out.println("Dati modul de tiparire dorit(color, alb_negru)");
        String mod=sc.nextLine();
        for(Echipament e : echipamente)
            if(e instanceof Imprimanta && e.getDenumire().equals(nume))
            {
                ((Imprimanta) e).setModTiparire(ModTiparire.valueOf(mod.toLowerCase()));
                ok=true;
                break;
            }
        if(ok==false)
            System.out.println("Nu s a gasit astfel de imprimanta");
    }

    public static void modificareFormatCopiere(List<Echipament>echipamente)
    {
        boolean ok=false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Dati numele copiatorului caruia doriti sa ii modificati formatul de copiere:");
        String nume=sc.nextLine();
        System.out.println("Dati formatul de copiere dorit(a3, a4");
        String mod=sc.nextLine();
        for(Echipament e : echipamente)
            if(e instanceof Copiatoare && e.getDenumire().equals(nume))
            {
                ((Copiatoare) e).setFormatCopiere(FormatCopiere.valueOf(mod.toLowerCase()));
                ok=true;
                break;
            }
        if(ok==false)
            System.out.println("Nu s a gasit astfel de copiator");
    }

    public static void modificareSistemOperare(List<Echipament>echipamente)
    {
        boolean ok=false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Dati numele sistemului de calcul caruia doriti sa ii instalati un sistem de operare:");
        String nume=sc.nextLine();
        System.out.println("Dati sistemul de operare dorit(Windows, Linux)");
        String mod=sc.nextLine();
        for(Echipament e : echipamente)
            if(e instanceof SistemeDeCalcul && e.getDenumire().equals(nume))
            {
                ((SistemeDeCalcul) e).setSisteme(SistemeOperare.valueOf(mod.toLowerCase()));
                ok=true;
                break;
            }
        if(ok==false)
            System.out.println("Nu s a gasit astfel de sistem de calcul");
    }

    public static void afisVanudute(List<Echipament>echipamente)
    {
        boolean ok=false;
        for(Echipament e : echipamente)
            if(e.getStatus().equals(StatusEchipament.valueOf("vandut")))
            {
                ok=true;
                System.out.println(e);
            }
        if(ok==false)
            System.out.println("Nu s a gasit echipamente vandute");
    }

    static void scrie(Object o, String fis) {
        try {
            FileOutputStream f = new FileOutputStream(fis);
            ObjectOutputStream oos = new ObjectOutputStream(f);
            oos.writeObject(o);
            oos.close();
            f.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    static Object citeste(String fis) {
        try {
            FileInputStream f = new FileInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(f);
            Object o=ois.readObject();
            ois.close();
            f.close();
            return o;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc1=new Scanner(System.in);
        int opt;
        Scanner sc=new Scanner(new File("src/ex/echipamente.txt"));
        List<Echipament> echipamente=new ArrayList<Echipament>();
        while(sc.hasNextLine())
        {
            String[] line=sc.nextLine().split(";");
            String denumire=line[0].trim();
            int nr_inv=Integer.parseInt(line[1].trim());
            int pret=Integer.parseInt(line[2].trim());
            String zona_mag=line[3].trim();
            StatusEchipament stare =StatusEchipament.valueOf(line[4].trim().toLowerCase());
            String ech=line[5].trim();
            if(line[5].equals("imprimanta"))
            {
                int ppm=Integer.parseInt(line[6].trim());
                String rezolutie=line[7].trim();
                int p_cart=Integer.parseInt(line[8].trim());
                ModTiparire mod=ModTiparire.valueOf(line[9].trim().toLowerCase());

                echipamente.add(new Imprimanta(denumire,nr_inv,pret,zona_mag,stare,ppm,rezolutie,p_cart,mod));
            }
            else if(line[5].equals("copiator"))
            {
                int p_ton=Integer.parseInt(line[6].trim());
                FormatCopiere format=FormatCopiere.valueOf(line[7].trim().toLowerCase());

                echipamente.add(new Copiatoare(denumire,nr_inv,pret,zona_mag,stare,p_ton,format));
            }
            else if(line[5].equals("sistem de calcul"))
            {
                 String tip_mon=line[6].trim();
                 double vit_proc=Double.parseDouble(line[7].trim());
                 int c_hdd=Integer.parseInt(line[8].trim());
                 SistemeOperare sisteme=SistemeOperare.valueOf(line[9].trim().toLowerCase());

                 echipamente.add(new SistemeDeCalcul(denumire,nr_inv,pret,zona_mag,stare,tip_mon,vit_proc,c_hdd,sisteme));
            }

        }

        do
        {
                System.out.println("0.Iesire");
                System.out.println("1.Afisare echipamente");
                System.out.println("2.Afisare imprimante");
                System.out.println("3.Afisare copiatore");
                System.out.println("4.Afisare sisteme de calcul");
                System.out.println("5.Modificare stare echipament");
                System.out.println("6.Setare mod scriere pentru imprimanta");
                System.out.println("7.Setare format copiere pentru copiatoare");
                System.out.println("8.Instalare sistem de operare pt sisteme de calcul");
                System.out.println("9.Afisare echipamente vandute");
                System.out.println("10.Serializarea / deserializarea colecției de obiecte în fișierul echip.bin");
                System.out.println("Dati opriunea:");
                opt=sc1.nextInt();
                switch (opt)
                {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        for(Echipament e:echipamente)
                            System.out.println(e);
                        break;
                    case 2:
                        for(Echipament e:echipamente)
                            if(e instanceof Imprimanta)
                                System.out.println(e);
                        break;
                    case 3:
                        for(Echipament e:echipamente)
                            if(e instanceof Copiatoare)
                                System.out.println(e);
                        break;
                    case 4:
                        for(Echipament e:echipamente)
                            if(e instanceof SistemeDeCalcul)
                                System.out.println(e);
                        break;
                    case 5:
                        modificareStare(echipamente);
                        break;
                    case 6:
                        modificareFormatScriere(echipamente);
                        break;
                    case 7:
                        modificareFormatCopiere(echipamente);
                        break;
                    case 8:
                        modificareSistemOperare(echipamente);
                        break;
                    case 9:
                        afisVanudute(echipamente);
                        break;
                    case 10:
                        scrie(echipamente,"echip.bin");
                        List<Echipament>q;
                        q=(List<Echipament>) citeste("echip.bin");
                        for(Echipament e:q)
                            System.out.println(e);
                        break;
                    default:
                }
        }while(true);
    }
}
