package com.ruap.backend;

import com.ruap.backend.model.Input;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class CallRequestResponseService {


    public String invokeRequestResponseService(Input userInput) throws IOException {
        String url = "https://ussouthcentral.services.azureml.net/workspaces/6391b6fc5762472293b89b737b987708/services/b8ab949b81f14da59b87cdb7d68ce34c/execute?api-version=2.0&details=true";
        String apiKey = "EFf8Bi/9/YGwtuB2Axd+n9tujm2Mhved7KQG/rZ/rsRQxoxjiELCpCM3682deeOQpw6P27Rj5+Nn+AMCw5yIFA=="; // Replace this with the API key for the web service

        String input = "{\"Inputs\":{\"input1\":{\"ColumnNames\":[\"Recency\",\"Frequency\",\"Monetary\",\"Time\",\"Class\"],\"Values\":[[\""+userInput.getRecency()+"\",\""+userInput.getFrequency()+"\",\""+userInput.getMonetary()+"\",\""+userInput.getTime() +"\",\""+userInput.getClass_()+"\"],[\"0\",\"0\",\"0\",\"0\",\"0\"]]}},\"GlobalParameters\":{}}";

        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

        // Set request headers
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);

        // Enable output and send request
        connection.setDoOutput(true);
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
            outputStream.write(inputBytes, 0, inputBytes.length);
        }

        // Get response
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String response = new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(response);
            connection.disconnect();
            return response;
        } else {
            System.out.println("The request failed with status code: " + responseCode);
            System.out.println(connection.getHeaderFields());
            String errorResponse = new String(connection.getErrorStream().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(errorResponse);
            connection.disconnect();
            return errorResponse;
        }


    }


}