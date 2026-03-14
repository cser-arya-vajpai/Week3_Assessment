package library_inventory_system;

import java.sql.*;
import java.util.Scanner;

public class LibraryBookInventorySystem {

    static final String url = "jdbc:mysql://localhost:3306/librarydb";
    static final String user = "root";
    static final String password = "ashuvishu";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("Enter title author price");

            String title = sc.next();
            String author = sc.next();
            double price = sc.nextDouble();

            // INSERT
            String insertQuery = "INSERT INTO book(title, author, price) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setDouble(3, price);

            ps.executeUpdate();

            System.out.println("Book added successfully");

            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;

            if(rs.next()) {
                id = rs.getInt(1);
            }

            // SEARCH
            String searchQuery = "SELECT * FROM book WHERE id = ?";
            PreparedStatement ps2 = con.prepareStatement(searchQuery);

            ps2.setInt(1, id);

            ResultSet result = ps2.executeQuery();

            while(result.next()) {

                System.out.println("ID: " + result.getInt("id"));
                System.out.println("Title: " + result.getString("title"));
                System.out.println("Author: " + result.getString("author"));
                System.out.println("Price: " + result.getDouble("price"));
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}