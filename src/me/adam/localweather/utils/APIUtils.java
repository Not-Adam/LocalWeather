package me.adam.localweather.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIUtils {
    public static String executePost(String targetURL) {
        try {
            URL url = new URL(targetURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.connect();

            try (OutputStream output = conn.getOutputStream()) {
                byte[] input = "{\"signal\": \"start\"}".getBytes("utf-8");
                output.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;

                while ((responseLine = br.readLine()) != null)
                    response.append(responseLine.trim());

                return response.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
