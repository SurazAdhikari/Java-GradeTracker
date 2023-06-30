import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class sql {
    public static void main(String[] args) {
        String databaseUrl = "jdbc:sqlite:/Users/xdzc0/Desktop/Project-java/dummy.db"; // Replace with the actual path
                                                                                       // to your dummy.db file

        try {
            // Load the SQLite JDBC driver
            Class.forName("/Users/xdzc0/Desktop/Project-java/sqlite-jdbc-3.42.0.0.jar");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(databaseUrl);

            // Create a statement object to execute SQL statements
            Statement statement = connection.createStatement();

            // Execute your SQL statements
            String createTableQuery = "CREATE TABLE userdata (symbolnumber TEXT, data TEXT)";
            String insertDataQuery1 = "INSERT INTO userdata (symbolnumber, data) VALUES ('12345', 'John Doe')";
            String insertDataQuery2 = "INSERT INTO userdata (symbolnumber, data) VALUES ('54321', 'Jane Smith')";

            statement.execute(createTableQuery);
            statement.executeUpdate(insertDataQuery1);
            statement.executeUpdate(insertDataQuery2);

            // Close the statement and connection
            statement.close();
            connection.close();

            System.out.println("SQL statements executed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
