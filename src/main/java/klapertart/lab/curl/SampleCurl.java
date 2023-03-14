package klapertart.lab.curl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

/**
 * @author kurakuraninja
 * @since 14/03/2023
 */

public class SampleCurl {
    public static void main(String[] args) {
        String username = "admin";
        String password = "tricadatritronik123";

        String stringUrl = "http://localhost:9000/api/search/universal/relative?query=source%3ADESKTOP-S9I46UA&range=28800&limit=1&decorate=true";
        URL url;

        try {
            url = new URL(stringUrl);
            URLConnection uc;
            uc = url.openConnection();

            String userpass = username + ":" + password;
            String basicAuth = "Basic " + Base64.getEncoder().encodeToString(userpass.getBytes());//+ new String(new Base64().encode(userpass.getBytes()));

            uc.setRequestProperty("Accept", "application/json");
            uc.setRequestProperty("Authorization", basicAuth);

            BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));

            }
            String result = builder.toString();
            System.out.println(result);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
