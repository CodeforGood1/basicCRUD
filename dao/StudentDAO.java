package dao;

import model.Student;
import java.sql.*;
import java.util.*;

public class StudentDAO {
    private Connection conn;

    public StudentDAO() throws Exception {
        String url = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = System.getenv("DB_USER");      // read from env variable
        String password = System.getenv("DB_PASS");  // read from env variable

        if (user == null || password == null) {
            throw new Exception("Database credentials not set in environment variables!");
        }

        Class.forName("com.mysql.cj.jdbc.Driver");   // ensures driver is loaded
        conn = DriverManager.getConnection(url, user, password);
    }

    public void addStudent(Student s) throws Exception {
        String sql = "INSERT INTO students (rollNo, name, age) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, s.getRollNo());
        stmt.setString(2, s.getName());
        stmt.setInt(3, s.getAge());
        stmt.executeUpdate();
    }

    public Student getStudent(int rollNo) throws Exception {
        String sql = "SELECT * FROM students WHERE rollNo=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, rollNo);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Student(rs.getInt("rollNo"), rs.getString("name"), rs.getInt("age"));
        }
        return null;
    }

    public List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM students");
        while (rs.next()) {
            list.add(new Student(rs.getInt("rollNo"), rs.getString("name"), rs.getInt("age")));
        }
        return list;
    }

    public void updateStudent(Student s) throws Exception {
        String sql = "UPDATE students SET name=?, age=? WHERE rollNo=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, s.getName());
        stmt.setInt(2, s.getAge());
        stmt.setInt(3, s.getRollNo());
        stmt.executeUpdate();
    }

    public void deleteStudent(int rollNo) throws Exception {
        String sql = "DELETE FROM students WHERE rollNo=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, rollNo);
        stmt.executeUpdate();
    }
}
