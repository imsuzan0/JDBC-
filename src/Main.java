import java.sql.*;

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
            Statement statement = conn.createStatement();

//            retrieve data from db:
//            String query0 = "select * from students;";
//            ResultSet resultSet = statement.executeQuery(query0);
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int age = resultSet.getInt("age");
//                double marks = resultSet.getDouble("marks");
//                System.out.println("ID : " + id);
//                System.out.println("Name : " + name);
//                System.out.println("Age : " + age);
//                System.out.println("Marks : " + marks);
//            }

            //insert data into db
//            String query1=String.format("insert into students(name,age,marks) values('%s',%o,%f)","Sujan",18,98.0);
//            int rowsAffected=statement.executeUpdate(query1);
//            if(rowsAffected>0){
//                System.out.println("Data inserted successfully!");
//            }else{
//                System.out.println("Data not inserted.");
//            }

            //update data in database
//            String query2 = String.format("update students set marks=%f where id =%d", 99.5, 2);
//            int rowsAffected = statement.executeUpdate(query2);
//            if (rowsAffected > 0) {
//                System.out.println("Data updated successfully!");
//            } else {
//                System.out.println("Data not updated.");
//            }

            //update data from database
            String query3=String.format("delete from students where id=%d",2);
            int rowsAffected=statement.executeUpdate(query3);
            if (rowsAffected > 0) {
                System.out.println("Data deleted successfully!");
            } else {
                System.out.println("Data not deleted.");
            }

            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}