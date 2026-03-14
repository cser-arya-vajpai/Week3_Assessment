package student_course_registration;

import java.sql.*;
import java.util.Scanner;

public class StudentCourseRegistrationSystem {

    static final String url = "jdbc:mysql://localhost:3306/universitydb";
    static final String user = "root";
    static final String password = "ashuvishu";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("Enter name course semester");

            String name = sc.next();
            String course = sc.next();
            int semester = sc.nextInt();

            // INSERT
            String insertQuery = "INSERT INTO student(name, course, semester) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setInt(3, semester);

            ps.executeUpdate();

            System.out.println("Student added successfully");

            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;

            if(rs.next()) {
                id = rs.getInt(1);
            }

            // SEARCH
            String searchQuery = "SELECT * FROM student WHERE id = ?";
            PreparedStatement ps2 = con.prepareStatement(searchQuery);

            ps2.setInt(1, id);

            ResultSet result = ps2.executeQuery();

            while(result.next()) {

                System.out.println("ID: " + result.getInt("id"));
                System.out.println("Name: " + result.getString("name"));
                System.out.println("Course: " + result.getString("course"));
                System.out.println("Semester: " + result.getInt("semester"));
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}