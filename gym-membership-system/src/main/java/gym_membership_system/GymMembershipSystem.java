package gym_membership_system;

import java.sql.*;
import java.util.Scanner;

public class GymMembershipSystem {

    static final String url = "jdbc:mysql://localhost:3306/gymdb";
    static final String user = "root";
    static final String password = "ashuvishu";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("Enter name membershipType fee");

            String name = sc.next();
            String membershipType = sc.next();
            double fee = sc.nextDouble();

            // INSERT
            String insertQuery = "INSERT INTO member(name, membershipType, fee) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, membershipType);
            ps.setDouble(3, fee);

            ps.executeUpdate();

            System.out.println("Member added successfully");

            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;

            if(rs.next()) {
                id = rs.getInt(1);
            }

            // SEARCH
            String searchQuery = "SELECT * FROM member WHERE id = ?";
            PreparedStatement ps2 = con.prepareStatement(searchQuery);

            ps2.setInt(1, id);

            ResultSet result = ps2.executeQuery();

            while(result.next()) {

                System.out.println("ID: " + result.getInt("id"));
                System.out.println("Name: " + result.getString("name"));
                System.out.println("Membership Type: " + result.getString("membershipType"));
                System.out.println("Fee: " + result.getDouble("fee"));
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}