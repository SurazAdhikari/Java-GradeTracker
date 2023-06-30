import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FetchData {
    private String serverAddress;
    private int serverPort;

    public FetchData(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public String fetchUserData(String symbolNumber) {
        try {
            Socket socket = new Socket(serverAddress, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(symbolNumber);

            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                responseBuilder.append(line).append("\n");
            }

            String response = responseBuilder.toString();
            if (!response.isEmpty()) {
                System.out.println("Result:\n" + response);
                response = response + "\n";
            } else {
                System.out.println("No result found.");
            }
            socket.close();
            System.out.println("Fetch data is returning data check");
            System.out.println(response);
            return response;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error occurred while fetching data.";
    }
}
