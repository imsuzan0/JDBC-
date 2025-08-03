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

            //retrieve data from database
//            String query="select marks from students where id = ?";
//            PreparedStatement preparedStatement=conn.prepareStatement(query);
//            preparedStatement.setInt(1,1);
//            ResultSet resultSet=preparedStatement.executeQuery();
//            if(resultSet.next()){
//                double marks=resultSet.getDouble("marks");
//                System.out.println(marks);
//            }else{
//                System.out.println("Marks not found!");
//            }


            //insert data into database
//            String query = "insert into students(name,age,marks) values(?,?,?)";
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            preparedStatement.setString(1, "Sujan");
//            preparedStatement.setInt(2, 18);
//            preparedStatement.setDouble(3, 99.0);
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Data inserted successfully!");
//            } else {
//                System.out.println("Data insertion failed!");
//            }

            //upate data in database
//            String query="update students set name=? where id=?";
//            PreparedStatement preparedStatement=conn.prepareStatement(query);
//            preparedStatement.setString(1,"Amita");
//            preparedStatement.setInt(2,1);
//            int rowsAffected=preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Data updated successfully!");
//            } else {
//                System.out.println("Failed to update data!");
//            }

            //delete data from database
            String query="delete from students where id=?";
            PreparedStatement preparedStatement=conn.prepareStatement(query);
            preparedStatement.setInt(1,1);
            int rowsAffected=preparedStatement.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Data deleted successfully!");
            }else{
                System.out.println("Failed to delete data!");
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