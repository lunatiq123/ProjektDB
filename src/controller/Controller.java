package controller;

import database.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Controller {

    //data access object
    DBConnect dao = new DBConnect();

    public void add(String imie, String nazwisko, String telefon) throws SQLException {

        String sql = "INSERT INTO kontakty (imie, nazwisko, telefon) VALUES (?, ?, ?)";
        PreparedStatement st = dao.getCon().prepareStatement(sql);
        st.setString(1, imie);
        st.setString(2, nazwisko);
        st.setString(3, telefon);
        st.execute();
        st.close();

        System.out.println("Czy chcesz zatwierdzic dzialanie T/N ?");
        Scanner sc = new Scanner(System.in);
        String dec = sc.nextLine().toUpperCase();
        if (dec.equals("T")) {
            dao.getCon().commit();
        } else {
            dao.getCon().rollback();
        }
    }

    public void show() throws SQLException {

        String sql = "SELECT * FROM kontakty";
        Statement st = dao.getCon().createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getInt("ID") + " " + rs.getString("imie") + " "
                    + rs.getString("nazwisko") + " " + rs.getString("telefon"));
        }
    }

    public void del(int ID) throws SQLException {
        String sql = "DELETE FROM kontakty WHERE id = (?)";
        PreparedStatement st = dao.getCon().prepareStatement(sql);

        st.setInt(1, ID);
        st.execute();
        st.close();

        System.out.println("Czy chcesz zatwierdzic dzialanie T/N ?");
        Scanner sc = new Scanner(System.in);
        String dec = sc.nextLine().toUpperCase();
        if (dec.equals("T")) {
            dao.getCon().commit();
        } else {
            dao.getCon().rollback();
        }

    }

    public void mod(int id, String kolumna, String wartosc) throws SQLException {

        //? tj 1 wartosc, drugi ? tj.2 ID
        String sql = "UPDATE kontakty SET " + kolumna + " = ? WHERE ID = ?";
        PreparedStatement st = dao.getCon().prepareStatement(sql);

        st.setString(1, wartosc);
        st.setInt(2, id);
        st.execute();
        st.close();

        System.out.println("Czy chcesz zatwierdzic dzialanie T/N ?");
        Scanner sc = new Scanner(System.in);
        String dec = sc.nextLine().toUpperCase();
        if (dec.equals("T")) {
            dao.getCon().commit();
        } else {
            dao.getCon().rollback();
        }

    }

    public void search (String szukana) throws SQLException {

        String sql = "SELECT * FROM kontakty WHERE imie LIKE '%"+szukana+"%' OR nazwisko LIKE '%"+szukana+"%' OR telefon LIKE '%"+szukana+"%'";
        Statement st = dao.getCon().createStatement();
        ResultSet rs = st.executeQuery(sql);



        while (rs.next()) {
            System.out.println(rs.getInt("ID") + " " + rs.getString("imie") + " "
                    + rs.getString("nazwisko") + " " + rs.getString("telefon"));
        }

    }


}
