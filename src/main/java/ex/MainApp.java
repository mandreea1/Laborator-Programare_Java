package ex;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.Year;

class ExceptieVarsta extends Exception {
    public ExceptieVarsta(String mesaj) {
        super(mesaj);
    }
}

class ExceptieAnExcursie extends Exception {
    public ExceptieAnExcursie(String mesaj) {
        super(mesaj);
    }
}

public class MainApp {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/java_lab8";
        try {
            Connection connection = DriverManager.getConnection(url, "andreea", "Andreea?");
            Statement statement = connection.createStatement();
            Scanner sc = new Scanner(System.in);
            while(true)
            {
                System.out.println("0. Iesire");
                System.out.println("1. Adaugare tabela persoane");
                System.out.println("2. Adaugare excursie");
                System.out.println("3. Afisarea persoanelor si a excursiilor corespunzatoare");
                System.out.println("4. Afisare excursii la o persoana");
                System.out.println("5. Afisare persoane care au vizitat o anumita locatie");
                System.out.println("6. Afisare persoane care au excursii intr-un an introdus");
                System.out.println("7. Stergerea unei excursii");
                System.out.println("8. Stergerea unei persoane impreuna cu excursiile");
                System.out.println("Optiunea dvs:");
                int optiune = sc.nextInt();
                switch (optiune) {
                    case 0:
                        statement.close();
                        connection.close();
                        System.exit(0);
                        break;
                    case 1:
                        adaugarePersoana(connection);
                        break;
                    case 2:
                        adaugareExcursie(connection, statement);
                        break;

                    case 3:
                        afisPersoane(statement);
                        break;
                    case 4:
                        excursiiPersona(statement);
                        break;
                    case 5:
                        persoaneDestinatie(statement);
                        break;
                    case 6:
                        persoaneExcursiiAn(statement);
                        break;
                    case 7:
                        stergereExcursie(statement);
                        break;
                    case 8:
                        stergerePersoana(statement);
                        break;
                    default:
                        System.out.println("Optiune invalida");
                        break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void adaugarePersoana(Connection connection) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Numele persoanei: ");
        String nume = sc.next();
        int varsta = -1;
        while (true) {
            try {
                System.out.print("Varsta persoanei: ");
                varsta = sc.nextInt();
                if (varsta < 0 || varsta > 100) {
                    throw new ExceptieVarsta("Eroare: Vârsta trebuie să fie între 0 și 100.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Eroare: Vârsta trebuie să fie un număr întreg.");
                sc.next();
            } catch (ExceptieVarsta e) {
                System.out.println(e.getMessage());
            }
        }

        String sql = "INSERT INTO persoane (nume, varsta) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nume);
            ps.setInt(2, varsta);
            int nr_randuri = ps.executeUpdate();
            System.out.println("Numar randuri afectate de adaugare: " + nr_randuri);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void adaugareExcursie(Connection connection, Statement statement) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Introduceti Id-ul persoanei: ");
        int id_pers = sc.nextInt();

        String sql = "SELECT varsta FROM persoane WHERE id = " + id_pers;
        try (ResultSet rs = statement.executeQuery(sql)) {
            if (!rs.next()) {
                System.out.println("Persoana cu id-ul " + id_pers + " nu exista.");
                return;
            }

            int varstaPersoanei = rs.getInt("varsta");
            int anNastere = Year.now().getValue() - varstaPersoanei;

            System.out.print("Locatia excursiei: ");
            String locatie = sc.next();

            int an_ex = -1;
            while (true) {
                try {
                    System.out.print("Anul excursiei: ");
                    an_ex = sc.nextInt();
                    int anCurent = Year.now().getValue();

                    if (an_ex < anNastere || an_ex > anCurent) {
                        throw new ExceptieAnExcursie("Eroare: Anul excursiei trebuie să fie între " + anNastere + " și " + anCurent + ".");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Eroare: Anul excursiei trebuie să fie un număr întreg.");
                    sc.next();
                } catch (ExceptieAnExcursie e) {
                    System.out.println(e.getMessage());
                }
            }

            String insertSQL = "INSERT INTO excursii (id_persoana, destinatia, anul) VALUES (?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(insertSQL)) {
                ps.setInt(1, id_pers);
                ps.setString(2, locatie);
                ps.setInt(3, an_ex);
                int nr_randuri = ps.executeUpdate();
                System.out.println("Numar randuri afectate de adaugare: " + nr_randuri);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void afisPersoane(Statement statement) {
        String sql ="select * from persoane";
        int id=0;
        try(ResultSet rs =statement.executeQuery(sql)) {
            while (rs.next()) {
                id= rs.getInt(1);
                System.out.println("id=" + id + ", nume=" + rs.getString(2) + ", varsta=" + rs.getInt(3));
                System.out.println("Excursii:");
                String sql1 ="select * from excursii where id_persoana="+id;
                try(Statement statement1 = statement.getConnection().createStatement();
                    ResultSet rs1 =statement1.executeQuery(sql1)) {
                    while (rs1.next())
                        System.out.println("id_excursie=" + rs1.getInt(1) +" id_persoana=" + rs1.getInt(2)+ ", Locatia=" + rs1.getString(3) + ", Anul=" + rs1.getInt(4));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void excursiiPersona(Statement statement) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nume persoana: ");
        String nume_persoana = sc.nextLine();
        String sql = "select id from persoane where nume='" + nume_persoana + "'";
        try (ResultSet rs = statement.executeQuery(sql)) {
            if (!rs.next()) {
                System.out.println("Persoana: " + nume_persoana + " nu exista");
                return;
            }
            int id = rs.getInt(1);
            String sql1 = "select * from excursii where id_persoana=" + id;
            try (Statement statement1 = statement.getConnection().createStatement();
                 ResultSet rs1 = statement1.executeQuery(sql1)) {
                while (rs1.next())
                    System.out.println("id_excursie=" + rs1.getInt(1) + " id_persoana=" + rs1.getInt(2) + ", Locatia=" + rs1.getString(3) + ", Anul=" + rs1.getInt(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void persoaneDestinatie(Statement statement)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Destinatia dupa care cautati persoanele :");
        String destinatie = sc.nextLine();
        String sql="select * from excursii where Destinatia='"+destinatie+"'";
        try(ResultSet rs=statement.executeQuery(sql)) {
            while (rs.next()) {
                int id_pers = rs.getInt(2);
                String sql1 = "select * from persoane where id=" + id_pers;
                try (Statement statement1 = statement.getConnection().createStatement();
                     ResultSet rs1 = statement1.executeQuery(sql1)) {
                    while (rs1.next())
                        System.out.println("id=" + rs1.getInt(1) + ", nume=" + rs1.getString(2) + ", varsta=" + rs1.getInt(3));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void persoaneExcursiiAn(Statement statement) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Anul:");
        int an = sc.nextInt();
        String url = "select * from excursii where Anul=" + an;
        try (ResultSet rs = statement.executeQuery(url)) {
            while (rs.next()) {
                int id_pers = rs.getInt(2);
                String sql1 = "select * from persoane where id=" + id_pers;
                try (Statement statement1 = statement.getConnection().createStatement();
                     ResultSet rs1 = statement1.executeQuery(sql1)) {
                    while (rs1.next())
                        System.out.println("id=" + rs1.getInt(1) + ", nume=" + rs1.getString(2) + ", varsta=" + rs1.getInt(3));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void stergereExcursie(Statement statement) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Id-ul excursiei pe care doriti sa o stergeti:");
        int id_excursie = sc.nextInt();
        String sql = "delete from excursii where id_excursie=" + id_excursie;
        try {
            int nr_randuri = statement.executeUpdate(sql);
            System.out.println("\nNumar randuri afectate de stergere=" + nr_randuri);
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static void stergerePersoana(Statement statement) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Id-ul persoanei pe care doriti sa o stergeti:");
        int id_persoana = sc.nextInt();
        String sql = "delete from persoane where Id=" + id_persoana;
        try {
            int nr_randuri = statement.executeUpdate(sql);
            System.out.println("\nNumar randuri afectate de stergere=" + nr_randuri);
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }
}
