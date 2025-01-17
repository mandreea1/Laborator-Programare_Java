package ex2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static List<Mobilier> citire() {
        try {
            File file=new File("src/main/resources/mobilier.json");
            ObjectMapper mapper=new ObjectMapper();
            List<Mobilier> persoane = mapper
                    .readValue(file, new TypeReference<List<Mobilier>>(){});
            return persoane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void cautaMobilier(List<Mobilier> mobilier) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Dati piesa de mobil la care doriti sa afisati caracteristicile placilor");
        String piesa=scanner.nextLine().trim();
        boolean ok=false;
        for(Mobilier m: mobilier) {
            if(m.getNume().trim().equalsIgnoreCase(piesa)) {
                System.out.println(m);
                ok=true;

            }
        }
        if(!ok) {
            System.out.println("Nu exista asa piesa de mobilier!");
        }
    }

    public static void numarColi(List<Mobilier> mobilier) {
        int lungime=2800;
        int latime=2070;
        int arie=lungime*latime;
        int ariePlaca,ariePlacaT=0;
        for(Mobilier m: mobilier) {
            for(Placa p: m.getPlaci()) {
                 ariePlaca=p.get_Arie();
                 ariePlacaT+=ariePlaca*p.getNr_bucati();
            }
            int coli=(int)Math.ceil((double) ariePlacaT/arie);
            System.out.println("Mobilier: "+m.getNume()+" sunt necesare aprox. "+coli+" coli pal");
        }
    }

    public static void main(String[] args) {
        int opt;
        List<Mobilier>mobilier=citire();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("0.Iesire");
            System.out.println("1.Afisarea datelor despre mobilier");
            System.out.println("2.Afişează elementele de mobilier din colecție şi plăcile care le compun");
            System.out.println("3.Afişează caracteristicile plăcilor care compun o anumită piesă de mobilier");
            System.out.println("4.Afișează estimativ numărul colilor de pal necesare pentru realizarea unui anumit corp");
            System.out.println("Dati optiunea: ");
            opt = sc.nextInt();
            switch (opt) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    for(Mobilier m: mobilier) {
                        System.out.println(m);
                    }
                    break;
                case 2:
                    System.out.println("Numele pieselor de mobilier si placile care la compun");
                    for(Mobilier m: mobilier) {
                        System.out.println(m.getNume()+" ");
                        System.out.println("Plăci:");
                        for (Placa p : m.getPlaci()) {
                            System.out.println("- " + p.getDescriere());
                        }
                        System.out.println();
                    }
                    break;
                case 3:
                    cautaMobilier(mobilier);
                    break;
                case 4:
                    numarColi(mobilier);
                    break;
                    default:
            }
        }while(true);
    }
}
