package com.example.lab9_ex1;

import com.example.jdbc.Masina;
import com.example.jdbc.MasinaJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.lab9_ex1", "com.example.jdbc"}) 
public class Lab9Ex1Application implements CommandLineRunner {

	@Autowired
	MasinaJdbcDao dao;
	public static void main(String[] args) {

		SpringApplication.run(Lab9Ex1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		Logger logger= LoggerFactory.getLogger(this.getClass());
		Scanner sc=new Scanner(System.in);
		while(true)
		{
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
			int opt=sc.nextInt();
			switch (opt)
			{
				case 0:
					sc.close();
					System.exit(0);
					break;
				case 1:
//					System.out.println("Toate masinile:");
//					dao.findAll().forEach(System.out::println);
					
					logger.info("\nToate masinile: "+dao.findAll());
					break;
				case 2:
					System.out.println("Nr inmatriculare:");
					String nrInmatriculare=sc.next();
					System.out.println("Marca:");
					String marca=sc.next();
					System.out.println("Anul fabricatiei:");
					int anulFabricatiei=sc.nextInt();
					System.out.println("Culoare:");
					String culoare=sc.next();
					System.out.println("Nr km:");
					Long nrKm=sc.nextLong();
					System.out.println("Adauga masina cu nr inmatriculare "+nrInmatriculare+", numarul de randuri adaugate: "
							+ dao.insert(new Masina(nrInmatriculare,marca,anulFabricatiei,culoare,nrKm)));
					dao.update(new Masina(nrInmatriculare,marca,anulFabricatiei,culoare,nrKm));
					break;
				case 3:
					System.out.println("Introduceti nr inmatriculare masina de sters:");
					String nrInmatriculare1 = sc.next();
					int rowsDeleted = dao.deleteById(nrInmatriculare1);
					if (rowsDeleted > 0) {
					    System.out.println("Masina cu nr inmatriculare " + nrInmatriculare1 + " a fost stearsa cu succes.");
					} else {
					    System.out.println("Nu exista nicio masina cu numarul de inmatriculare " + nrInmatriculare1 + " in baza de date.");
					}
					break;
				case 4:
					System.out.println("Introduceti nr inmatriculare masina de cautat:");
					String nrInmatriculare2=sc.next();
					try {
					    Masina masina = dao.findById(nrInmatriculare2);
					    System.out.println("Masina cu nr inmatriculare " + nrInmatriculare2 + ": " + masina);
					} catch (org.springframework.dao.EmptyResultDataAccessException e) {
					    System.out.println("Nu exista nicio masina cu numarul de inmatriculare " + nrInmatriculare2 + " in baza de date.");
					}
					break;
				case 5:
					List<Masina> m =dao.findAll();
					m.forEach(System.out::println);
					break;
				case 6:
					System.out.println("Introduceti marca masinii:");
					String marca1=sc.next();
					System.out.println("Nr masini marca "+marca1+": "
							+dao.findAll()
							.stream()
							.filter(x->x.getMarca().equals(marca1))
							.count());
					break;
				case 7:
					System.out.println("Nr masini care au sub 100.000 km: "
							+dao.findAll()
							.stream()
							.filter(x->x.getNrKm()<100000)
							.count());
					break;
				case 8:
					System.out.println("Masini mai noi de 5 ani:");
					dao.findAll()
							.stream()
							.filter(x->x.getAnulFabricatiei()+5>= LocalDate.now().getYear())
							.forEach(System.out::println);
					break;
					default:
						System.out.print("Optiune gresita!");
			}
		}

	}

}