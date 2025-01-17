package ex;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainApp {
    public static void scriere(List<Angajat> lista) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            File file=new File("src/main/resources/angajati.json");
            mapper.writeValue(file,lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Angajat> citire() {
        try {
            File file=new File("src/main/resources/angajati.json");
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<Angajat> angajati = mapper
                    .readValue(file, new TypeReference<List<Angajat>>(){});
            return angajati;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sub3(List<Angajat>angajati) {
        int an_curent= LocalDate.now().getYear();
        int anul_trecut=an_curent-1;
        List<Angajat>angAprilie=angajati.stream()
                .filter(a->a.getPostul().contains("sef") || a.getPostul().contains("director"))
                .filter(a->a.getData_angajarii().getYear()==anul_trecut && a.getData_angajarii().getMonth()== Month.APRIL)
                .collect(Collectors.toList());
        if(angAprilie.isEmpty())
            System.out.println("Nu exista astfel de angajati!");
        else
            angAprilie.forEach(System.out::println);
    }

    public static void sub4(List<Angajat>angajati) {
        angajati.stream()
                .filter(a->!(a.getPostul().contains("sef") || a.getPostul().contains("director")))
                .sorted(Comparator.comparing(Angajat::getSalariul).reversed())
                .forEach(System.out::println);
    }

    public static void sub5(List<Angajat>angajati) {
        List<String>nume=angajati.stream()
                .map(a->a.getNume().toUpperCase())
                .collect(Collectors.toList());
        nume.forEach(System.out::println);
    }

    public static void sub6(List<Angajat>angajati) {
        angajati.stream()
                .filter(a->a.getSalariul()<3000)
                .map(a->a.getSalariul())
                .forEach(System.out::println);
    }

    public static void sub7(List<Angajat>angajati) {
        angajati.stream()
                .min(Comparator.comparing(Angajat::getData_angajarii))
                .ifPresentOrElse(System.out::println, ()->System.out.println("Nu exista angajati!"));
    }

    public static void sub8(List<Angajat>angajati) {
        DoubleSummaryStatistics statistici=angajati.stream()
                .collect(Collectors.summarizingDouble(Angajat::getSalariul));
        System.out.println("Salar minim: "+statistici.getMin());
        System.out.println("Salar mediu: "+statistici.getAverage());
        System.out.println("Salar max: "+statistici.getMax());
    }

    public static void sub9(List<Angajat>angajati) {
        angajati.stream()
                .filter(a->a.getNume().toLowerCase().equalsIgnoreCase("ion"))
                .findAny()
                .ifPresentOrElse(a->System.out.println("Firma are cel puțin un Ion angajat"), ()->System.out.println("Firma nu are nici un Ion\n" +
                        "angajat!"));
    }

    public static void sub10(List<Angajat>angajati) {
        int an_curent= LocalDate.now().getYear();
        int anul_trecut=an_curent-1;
       long nr= angajati.stream()
                .filter(a->a.getData_angajarii().getYear()==anul_trecut&& (a.getData_angajarii().getMonth()==Month.JUNE ||
                        a.getData_angajarii().getMonth()==Month.JULY || a.getData_angajarii().getMonth()==Month.AUGUST))
                .count();
       System.out.println("Persoane care au fost angajate vara trecuta: "+nr);
    }

    public static void main(String[] args) {
        List<Angajat> angajati=citire();
        Scanner scanner=new Scanner(System.in);
        do{
            System.out.println("0.Iesire");
            System.out.println("1.Afisare angajati");
            System.out.println("2.Afișarea angajaților care au salariul peste 2500 RON");
            System.out.println("3.Crearea unei liste cu angajații din luna aprilie, a anului trecut, care au funcție de conducere");
            System.out.println("4.Afișarea angajaților care nu au funcție de conducere, în ordine descrescătoare a salariilor");
            System.out.println("5.Extragerea din lista de angajați a unei liste de String-uri care conține numele angajaților scrise cu majuscule");
            System.out.println("6.Afișarea salariilor mai mici de 3000 de RON");
            System.out.println("7.Afișarea datelor primului angajat al firmei");
            System.out.println("8.Afis salar minim,mediu,maxim");
            System.out.println("9.Afișarea unor mesaje care indică dacă printre angajați există cel puțin un “Ion”");
            System.out.println("10.Afișarea numărului de persoane care s-au angajat în vara anului precedent.");
            System.out.println("Dati optiunea: ");
            int op=scanner.nextInt();
            switch(op){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    for(Angajat a:angajati){
                        System.out.println(a);
                    }
                    break;
                case 2:
                    angajati.stream()
                            .filter(a->a.getSalariul()>2500)
                            .forEach(a->System.out.println(a));
                    break;
                case 3:
                    sub3(angajati);
                    break;
                case 4:
                    sub4(angajati);
                    break;
                case 5:
                    sub5(angajati);
                    break;
                case 6:
                    sub6(angajati);
                    break;
                case 7:
                    sub7(angajati);
                    break;
                case 8:
                    sub8(angajati);
                case 9:
                    sub9(angajati);
                case 10:
                    sub10(angajati);
                    break;
                    default:
            }
        }while (true);
    }
}
