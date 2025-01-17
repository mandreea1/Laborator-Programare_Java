package ex2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainApp {
    public static void scriere(Set<InstrumentMuzical> lista) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            File file=new File("src/main/resources/instrumente.json");
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            mapper.writeValue(file,lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Set<InstrumentMuzical> citire() {
        try {
            File file=new File("src/main/resources/instrumente.json");
            ObjectMapper mapper=new ObjectMapper();
            Set<InstrumentMuzical> ins = mapper
                    .readValue(file, new TypeReference<Set<InstrumentMuzical>>(){});
            return ins;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Set<InstrumentMuzical> lista=new HashSet<InstrumentMuzical>();
        Scanner sc=new Scanner(System.in);
        lista.add(new Chitara("and",3300,TipChitara.ELECTRICA,4));
        lista.add(new Chitara("III",100,TipChitara.ACUSTICA,6));
        lista.add(new Chitara("ale",500,TipChitara.CLASICA,3));
        lista.add(new SetTobe("www",90,TipTobe.ELECTRONICE,2,4));
        lista.add(new SetTobe("yyy",4000,TipTobe.ELECTRONICE,3,1));
        lista.add(new SetTobe("aaa",200,TipTobe.ACUSTICE,4,7));
        lista.stream().forEach(System.out::println);

        scriere(lista);
        lista=citire();
        System.out.println("Instrumente citite din json");
        lista.forEach(System.out::println);

        System.out.println("\nImplementarea utilizata pentru interfata in urma citirii "+lista.getClass().getName());

        System.out.println("\nVerificare duplicat.Adaugati un instrument\nChitara-0 SetTobe 1?");
        int opt=sc.nextInt();
        System.out.println("Dati producatorul: ");
        String prod=sc.next();
        System.out.println("Dati pretul: ");
        int pret=sc.nextInt();
        if(opt==0)
        {
            System.out.println("Dati tipul chitarei:ELECTRICA, ACUSTICA şi CLASICA");
            String x=sc.next().trim().toUpperCase();
            TipChitara tip=TipChitara.valueOf(x);
            System.out.println("Dati numarul de corzi: ");
            int corzi=sc.nextInt();
            Chitara c=new Chitara(prod,pret,tip,corzi);

            if(lista.stream().anyMatch(i->i instanceof Chitara && i.getProducator().equals(prod)&& i.getPret()==pret
                    && ((Chitara) i).getTip_chitara().equals(tip) && ((Chitara) i).getNr_corzi()==corzi))
            {
                System.out.println("Nu se permit duplicate ");
            }
            else
            {
                System.out.println("Lista dupa adaugare: ");
                lista.add(c);
                lista.forEach(System.out::println);
                scriere(lista);
            }
        }
        else if(opt==1) {
            System.out.println("Dati tipul de tobe:electronice sau acustice ");
            String x = sc.next().trim().toUpperCase();
            TipTobe tip = TipTobe.valueOf(x);
            System.out.println("Dati numarul de tobe: ");
            int tobe = sc.nextInt();
            System.out.println("Dati numarul de cinele: ");
            int cinele = sc.nextInt();
            SetTobe t = new SetTobe(prod, pret, tip, tobe, cinele);

            if(lista.stream().anyMatch(i->i instanceof SetTobe && i.getProducator().equals(prod)&& i.getPret()==pret
            && ((SetTobe) i).getTip_tobe().equals(tip) && ((SetTobe) i).getNr_tobe()==tobe && ((SetTobe) i).getNr_cinele()==cinele))
            {
                System.out.println("Nu se permit duplicate ");
            }
            else
            {
                System.out.println("Lista dupa adaugare: ");
                lista.add(t);
                lista.forEach(System.out::println);
                scriere(lista);
            }
        }

        System.out.println("\nStergere instrumente a caror pret e mai mare de 3000");
        lista.removeIf(i->i.getPret()>3000);
        System.out.println("\nLista dupa stergere: ");
        lista.forEach(System.out::println);
        System.out.println("\nToate datele chitarilor: ");
        lista.stream().filter(i-> i instanceof Chitara)
                .forEach(System.out::println);
        System.out.println("\nToate datele tobelor: ");
        lista.stream().filter(i->i.getClass().equals(SetTobe.class))
                .forEach(System.out::println);

        System.out.println("\nDatele chitarii cu cele mai multe corzi: ");
        lista.stream().filter(i->i instanceof Chitara)
                .map(i->(Chitara)i)
                .max(Comparator.comparing(Chitara::getNr_corzi))
                .ifPresentOrElse(System.out::println, ()->System.out.println("Nu exista chitari!"));

        System.out.println("\nToate datele tobelor acustice ordonat după numărul de tobe: ");
        lista.stream().filter(i->i instanceof SetTobe)
                .map(i->(SetTobe)i)
                .filter(i->i.getTip_tobe()==TipTobe.ACUSTICE)
                .sorted(Comparator.comparing(SetTobe::getNr_tobe))
                .forEach(System.out::println);
    }
}
