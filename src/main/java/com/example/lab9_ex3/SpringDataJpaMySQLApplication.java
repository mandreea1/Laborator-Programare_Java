package com.example.lab9_ex3;

import com.example.entity.Masina;
import com.example.repository.MasinaSpringDataJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.lab9_ex2"})
@EnableJpaRepositories(basePackages = "com.example.repository")
@EntityScan(basePackages = "com.example.entity")
public class SpringDataJpaMySQLApplication implements  CommandLineRunner {
    @Autowired
    MasinaSpringDataJpaRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaMySQLApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Logger logger = LoggerFactory.getLogger(this.getClass());
        Scanner sc = new Scanner(System.in);
        while (true) {
        	System.out.println("1. Afisare masini");
			System.out.println("2. Adaugare masina");
			System.out.println("3. Stergere masina");
			System.out.println("4. Cautare masina dupa numărul de inmatriculare");
			System.out.println("5. Extragerea unei liste care conţine toate mașinile din baza de date");
			System.out.println("6. Determinarea numărului de mașini din BD care au o anumită marcă");
			System.out.println("7. Determinarea numărului de mașini din BD care au sub 100 000 km");
			System.out.println("8. Extragera unei liste care conţine maşinile mai noi de 5 ani");
			System.out.println("0. Exit");
            System.out.println("Introduceti optiunea: ");
            int opt = sc.nextInt();
            switch (opt) {

                case 0:
                    sc.close();
                    System.exit(0);
                    break;
                case 1:
//                    System.out.println("Toate masinile:");
//                    repository.findAll().forEach(System.out::println);
                    logger.info("Toate masinile: " + repository.findAll());
                    break;
                case 2:
                    System.out.println("Introduceti datele masinii:");
                    System.out.println("Nr inmatriculare:");
                    String nrInmatriculare = sc.next();
                    System.out.println("Marca:");
                    String marca = sc.next();
                    System.out.println("Anul fabricatiei:");
                    int anulFabricatiei = sc.nextInt();
                    System.out.println("Culoare:");
                    String culoare = sc.next();
                    System.out.println("Nr km:");
                    int nrKm = sc.nextInt();
                    repository.save(new Masina(nrInmatriculare, marca, anulFabricatiei, culoare, nrKm));
                    break;
                case 3:
                    System.out.println("Introduceti nr inmatriculare:");
                    String nrInmatriculare1 = sc.next(); 

                    if (repository.findById(nrInmatriculare1).isPresent()) { 
                        repository.deleteById(nrInmatriculare1);
                        System.out.println("Masina cu numarul " + nrInmatriculare1 + " a fost stearsa.");
                    } else {
                        System.out.println("Nu exista nicio masina cu numarul de inmatriculare " + nrInmatriculare1 + " in baza de date.");
                    }
                    break;
                case 4:
                    System.out.println("Introduceti nr inmatriculare:");
                    String nrInmatriculare2 = sc.next(); 

                    repository.findById(nrInmatriculare2).ifPresentOrElse(
                        masina -> System.out.println("Masina gasita: " + masina),
                        () -> System.out.println("Nu exista nicio masina cu numarul de inmatriculare " + nrInmatriculare2 + " in baza de date.")
                    );
                    break;
                case 5:
                    System.out.println("Toate masinile:");
                    List<Masina> masini = repository.findAll();

                    if (masini.isEmpty()) {
                        System.out.println("Nu există mașini în baza de date.");
                    } else {
                        masini.forEach(System.out::println);
                    }
                    break;
                case 6:
                    System.out.println("Introduceti marca:");
                    String marca1 = sc.next();
                    System.out.println("Nr masini marca " + marca1 + ": " + repository.findByMarca(marca1).size());
                    break;
                case 7:
                    System.out.println("Nr masini care au sub 100 000 km: " + repository.findByKilometriIsBefore(100000).size());
                    break;
                case 8:
                    int anulFabricatiei1 = LocalDate.now().getYear()-5;
                    System.out.println("Masini mai noi de 5 ani.: " + repository.findByAnulFabricatieiIsAfter(anulFabricatiei1).size());
                    break;
                default:
                    System.out.println("Optiune invalida");
                    break;
            }
        }
    }
}