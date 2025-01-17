package ex1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class MainApp {
    record Carte(String titlul, String autorul, int anul){}
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File file=new File("src/main/resources/carti.json");
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer,Carte> carti=mapper.readValue(file,new TypeReference<>(){});
        do{
            System.out.println("0.Iesire");
            System.out.println("1.Afis Colectie");
            System.out.println("2.Stergere carte");
            System.out.println("3.Adaugare carte");
            System.out.println("4.Salvare modificari");
            System.out.println("5.Cartile autorului Yual Noah Harari si ordonare dupa titlu si datele celei mai vechi carti");
            System.out.println("Dati optiunea: ");
            int opt=sc.nextInt();
            switch(opt){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    carti.forEach((id,carte)->System.out.println(id+" "+carte));
                    break;
                case 2:
                    System.out.println("Dati id ul cartii pe care doriti sa o stergeti: ");
                    int nr=sc.nextInt();
                    carti.remove(nr);
                    break;
                case 3:
                     System.out.println("Dati titlul cartii: ");
                     sc.nextLine();
                     String ti=sc.nextLine();
                     System.out.println("Dati autorul: ");
                     String a=sc.nextLine();
                     System.out.println("Dati anul de aparitie: ");
                     int an=sc.nextInt();
                     carti.putIfAbsent(7,new Carte(ti,a,an));
                     break;
                case 4:
                    mapper.writeValue(file,carti);
                    break;
                case 5:
                    Set<Carte> extragere=carti.values().stream()
                            .filter(c->c.autorul().equalsIgnoreCase("Yuval Noah Harari"))
                            .collect(Collectors.toSet());
                    if(extragere.isEmpty())
                        System.out.println("Nu exista astfel de carti!");
                      else
                    {
                        System.out.println("Afisare neordonata!");
                          extragere.forEach(System.out::println);
                        System.out.println("Afisare ordonata!");
                    }
                    extragere.stream().sorted(Comparator.comparing(Carte::titlul))
                            .forEach(System.out::println);
                    System.out.println("Datele celei mai vechi carti: ");
                    extragere.stream().min(Comparator.comparing(Carte::anul))
                            .ifPresentOrElse(System.out::println, () -> System.out.println("Nu exista carti!"));
                    break;
                default:
            }
        }while(true);
    }
}
