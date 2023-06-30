import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Server {
    private int port;
    private String databasePath;

    public Server(int port, String databasePath) {
        this.port = port;
        this.databasePath = databasePath;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for client connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Handle client request in a separate thread
                Thread clientThread = new Thread(() -> handleClientRequest(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientRequest(Socket clientSocket) {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String symbolNumber = in.readLine();

            // Perform the necessary logic to fetch the data based on the symbol number
            String userData = fetchUserDataFromDatabase(symbolNumber);

            // Send the response back to the client
            out.println(userData);

            System.out.println("Response sent to client: " + userData);

            clientSocket.close();
            System.out.println("Client connection closed: " + clientSocket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String fetchUserDataFromDatabase(String symbolNumber) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // Connect to the SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);

            // Prepare the SQL query
            String query = "SELECT * FROM result WHERE \"Exam Roll No.\" = ?";

            statement = connection.prepareStatement(query);
            statement.setString(1, symbolNumber);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if a result is found
            if (resultSet.next()) {
                StringBuilder userDataBuilder = new StringBuilder();

                // Append each column value to the string representation
                userDataBuilder.append("\n");
                userDataBuilder.append("  Exam Roll No: ").append(resultSet.getString("Exam Roll No."))
                        .append("\n");

                userDataBuilder.append(
                        "\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014")
                        .append("\n");
                userDataBuilder
                        .append("  Engineering Mathematics II:                                                             l       ")
                        .append(resultSet.getString("Engineering Mathematics II")).append("\n");

                userDataBuilder.append(
                        "\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014")
                        .append("\n");
                userDataBuilder.append(
                        "  Logic Circuits:                                                                                    l       ")
                        .append(resultSet.getString("Logic Circuits")).append("\n");
                userDataBuilder.append(
                        "\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014")
                        .append("\n");
                userDataBuilder
                        .append("  Mathematical Foundation of Computer Science:                           l       ")
                        .append(resultSet.getString("Mathematical Founation of Computer Science")).append("\n");
                userDataBuilder.append(
                        "\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014")
                        .append("\n");
                userDataBuilder
                        .append("  Engineering Drawing:                                                                       l       ")
                        .append(resultSet.getString("Engineering Drawing"))
                        .append("\n");
                userDataBuilder.append(
                        "\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014")
                        .append("\n");
                userDataBuilder.append(
                        "  Object Oriented Programming in C++:                                            l       ")
                        .append(resultSet.getString("Object Oriented Programming in C++")).append("\n");
                userDataBuilder.append(
                        "\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014")
                        .append("\n");
                userDataBuilder
                        .append("  Web Technology:                                                                               l       ")
                        .append(resultSet.getString("Web Technology")).append("\n");
                userDataBuilder.append(
                        "\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014")
                        .append("\n");
                userDataBuilder.append(
                        "                                                                                                                    SGPA: ")
                        .append(resultSet.getString("SGPA")).append("\n");
                userDataBuilder.append(
                        "\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014")
                        .append("\n");

                return userDataBuilder.toString();
            } else {
                return "No result found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred during database query.";
        } finally {
            // Close the database resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int port = 1234; // Change the port number if needed
        String databasePath = "/Users/xdzc0/Desktop/Project-java/thirdSem.db"; // Path to the SQLite
        // database file
        Server server = new Server(port, databasePath);
        server.start();
    }
}
// Use following code to run Server
// javac Server.java
// java -cp ".:sqlite-jdbc-3.42.0.0.jar" Server
