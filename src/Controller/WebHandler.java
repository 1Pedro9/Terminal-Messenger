package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WebHandler {

    private final String link = "https://schoolhelp.co.za/Server/Services/Messenger/message.php";

    public String sendMessage(int send_id, int receive_id, String message) {
        System.out.println("We are sending it to server");
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Prepare data (URL-encoded message)
            String data = "send_id=" + send_id +
                    "&receive_id=" + receive_id +
                    "&message=" + URLEncoder.encode(message, "UTF-8");

            // Set up POST request
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Send request
            try (OutputStream os = con.getOutputStream()) {
                os.write(data.getBytes());
                os.flush();
            }

            // Read response
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
            }

            System.out.println("Response Code: " + con.getResponseCode());
            System.out.println("Server Response: " + response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    public void getMessage() {
        // Not yet implemented
    }
}
