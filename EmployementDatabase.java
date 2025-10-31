package calc;
import java.sql.*;
import java.util.Scanner;
public class EmployementDatabase {


    private static final String URL = "jdbc:mysql://localhost:3306/employeedb";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password"; // Enter here actual password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void addEmployee(int id, String name, String dept, double salary) {
        String sql = "INSERT INTO employees (id, name, department, salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, dept);
            stmt.setDouble(4, salary);
            stmt.executeUpdate();
            System.out.println("Employee added.");
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    public static void viewEmployees() {
        String sql = "SELECT * FROM employees";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n Employee List:");
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Dept: %s | Salary: %.2f\n",
                    rs.getInt("id"), rs.getString("name"), rs.getString("department"), rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing employees: " + e.getMessage());
        }
    }

    public static void updateSalary(int id, double newSalary) {
        String sql = "UPDATE employees SET salary = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newSalary);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Salary updated." : " Eployee not found.");
        } catch (SQLException e) {
            System.out.println("Error updating salary: " + e.getMessage());
        }
    }

    public static void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Employee deleted." : "Employee not found.");
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nEmployee DB Menu");
            System.out.println("1.Add Employee\n2. View All\n3. Update Salary\n4. Delete\n5. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("ID: "); int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Dept: "); String dept = sc.nextLine();
                    System.out.print("Salary: "); double sal = sc.nextDouble();
                    addEmployee(id, name, dept, sal);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    System.out.print("ID: "); int uid = sc.nextInt();
                    System.out.print("New Salary: "); double nsal = sc.nextDouble();
                    updateSalary(uid, nsal);
                    break;
                case 4:
                    System.out.print("ID: "); int did = sc.nextInt();
                    deleteEmployee(did);
                    break;
                case 5:
                    System.out.println(" Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
