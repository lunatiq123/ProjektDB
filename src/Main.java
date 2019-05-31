import controller.Controller;
import database.DBConnect;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Scanner input = new Scanner(System.in);
        Controller controller = new Controller();

        while (true) {

            System.out.println("P-pokaz, D-dodaj, U-usun, Z-zmien, S-szukaj, K-koniec");
            String dec = input.nextLine().toUpperCase();

            if (dec.equals("P")) {
                controller.show();

            }

            if (dec.equals("D")) {
                System.out.println("Podaj imię");
                String imie = input.nextLine();
                System.out.println("Podaj nazwisko");
                String nazwisko = input.nextLine();
                System.out.println("Podaj numer telefonu");
                String telefon = input.nextLine();
                controller.add(imie, nazwisko, telefon);
            }
            if (dec.equals("U")) {
                System.out.println("Podaj ID do usuniecia");
                int ID = input.nextInt();
                input.nextLine();
                controller.del(ID);

            }
            if (dec.equals("Z")) {
                System.out.println("Podaj ID do zmiany");
                int id = input.nextInt();
                input.nextLine();
                System.out.println("Podaj nazwę kolumny do edycji");
                String kolumna = input.nextLine();
                System.out.println("Podaj nową wartość");
                String wartosc = input.nextLine();
                controller.mod(id, kolumna, wartosc);
            }
            if (dec.equals("S")) {
                System.out.println("Podaj wyszukiwaną frazę ");
                String szukana = input.nextLine();

                controller.search(szukana);





            }

            if (dec.equals("K")) {
                break;
            }

        }
    }
}
