import java.util.*;

// Requirement: Handling invalid inputs (Source: 2)
class MyError extends Exception {
    public MyError(String message) { super(message); }
}

// Requirement: Student Management (Source: 4)
class Student {
    int id;
    String name;
    String email;
    public Student(int id, String name, String email) {
        this.id = id; this.name = name; this.email = email;
    }
    public String toString() { return "ID: " + id + " | Name: " + name; }
}

// Requirement: Course Management (Source: 4)
class Course {
    int id; String name; double fee;
    public Course(int id, String name, double fee) {
        this.id = id;
        this.name = name;
        this.fee = fee;
    }
}

// Requirement: Multithreading (Source: 4)
class EnrollmentThread extends Thread {
    String sName;
    String cName;
    public EnrollmentThread(String Kajol, String Btech) {   
        this.sName = Kajol;
         this.cName = Btech; 
        }
    public void run() {
        try {
            System.out.println("\n[System] Processing " + sName + " for " + cName + "...");
            Thread.sleep(2000); 
            System.out.println("[System] Enrollment Successful!");
        } catch (Exception e) {}
    }
}

// Requirement: Main Program (Source: 4)
public class SmartCampusSystem {
    static List<Student> students = new ArrayList<>();
    static List<Course> courses = new ArrayList<>();
    static Map<Integer, List<Course>> enrollments = new HashMap<>(); // Source: 7, 8
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- GNC SMART CAMPUS ---");
            System.out.println("1. Add Student\n2. Add Course\n3. Enroll\n4. View All\n5. Exit");
            System.out.print("Choice: ");
            try {
                int ch = Integer.parseInt(sc.nextLine());
                if (ch == 1) addS();
                else if (ch == 2) addC();
                else if (ch == 3) enroll();
                else if (ch == 4) view();
                else if (ch == 5) break;
            } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        }
    }

    static void addS() {
        System.out.print("ID: ");
         int id = Integer.parseInt(sc.nextLine());
        System.out.print("Name: ");
        String n = sc.nextLine();
        students.add(new Student(id, n, n + "@gnc.com"));
        System.out.println("Student Added.");
    }

    static void addC() throws MyError {
        System.out.print("Course ID: "); int id = Integer.parseInt(sc.nextLine());
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Fee: "); double fee = Double.parseDouble(sc.nextLine());
        if (fee < 0) throw new MyError("Fee cannot be negative!"); // Source: 10
        courses.add(new Course(id, name, fee));
    }

    static void enroll() {
        System.out.print("Student ID: "); int sid = Integer.parseInt(sc.nextLine());
        System.out.print("Course ID: "); int cid = Integer.parseInt(sc.nextLine());
        new EnrollmentThread("Student "+sid, "Course "+cid).start(); // Source: 11
    }

    static void view() {
        students.forEach(System.out::println);
    }
}