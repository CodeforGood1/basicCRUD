import java.util.*;
import model.Student;
import dao.StudentDAO;

public class Main {
    public static void main(String[] args) {
        try {
            StudentDAO dao = new StudentDAO();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n1. Add  2. View  3. View All  4. Update  5. Delete  6. Exit");
                int choice = sc.nextInt();

                if (choice == 1) {
                    System.out.print("Roll: "); int r = sc.nextInt();
                    System.out.print("Name: "); String n = sc.next();
                    System.out.print("Age: "); int a = sc.nextInt();
                    dao.addStudent(new Student(r, n, a));
                } else if (choice == 2) {
                    System.out.print("Roll: "); int r = sc.nextInt();
                    Student s = dao.getStudent(r);
                    System.out.println(s == null ? "Not found" : s);
                } else if (choice == 3) {
                    for (Student s : dao.getAllStudents()) {
                        System.out.println(s);
                    }
                } else if (choice == 4) {
                    System.out.print("Roll to update: "); int r = sc.nextInt();
                    System.out.print("New name: "); String n = sc.next();
                    System.out.print("New age: "); int a = sc.nextInt();
                    dao.updateStudent(new Student(r, n, a));
                } else if (choice == 5) {
                    System.out.print("Roll to delete: "); int r = sc.nextInt();
                    dao.deleteStudent(r);
                } else if (choice == 6) {
                    break;
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
