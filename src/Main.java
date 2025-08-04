import java.sql.*;
import java.util.Scanner;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    static Dotenv dotenv = Dotenv.load();

    private static final String url = dotenv.get("DB_URL");
    private static final String username = dotenv.get("DB_USER");
    private static final String password = dotenv.get("DB_PASSWORD");

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver connected!");

            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to MySQL successfully!");
            System.out.println("Connected to: " + conn.getCatalog());

            Scanner scanner = new Scanner(System.in);
            String query = "INSERT INTO students(name, age, marks) VALUES(?,?,?)";
            PreparedStatement preparedStatement=conn.prepareStatement(query);

            while (true) {
                System.out.print("Enter name: ");
                String name = scanner.next();

                System.out.print("Enter age: ");
                int age = scanner.nextInt();

                System.out.print("Enter marks: ");
                double marks = scanner.nextDouble();

                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,age);
                preparedStatement.setDouble(3,marks);

               preparedStatement.addBatch();

                System.out.print("Enter more data (y/n): ");
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("n")) {
                    break;
                }
            }

            // Execute the batch after the loop
            int[] results = preparedStatement.executeBatch();
            for (int i = 0; i < results.length; i++) {
                if (results[i] == Statement.EXECUTE_FAILED) {
                    System.out.println("Query " + (i + 1) + " failed to execute.");
                } else {
                    System.out.println("Query " + (i + 1) + " executed successfully.");
                }
            }

            conn.close();
            scanner.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
    }
}
