package com.ruap.backend;

import com.ruap.backend.model.Input;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class CallRequestResponseService {


    public String invokeRequestResponseService(Input userInput) throws IOException {
        String url = "https://ussouthcentral.services.azureml.net/workspaces/6391b6fc5762472293b89b737b987708/services/3314b7eb783549d793bcc76c238097c6/execute?api-version=2.0&details=true";
        String apiKey = "ILD/l9mKrwo65Wlt718kMjIo50ldI/SNhTL2Vl/fZH4stY3W/P56vDk28pcONfQfYU8MN+rRzNuP+AMC1Gd7Yg=="; // Replace this with the API key for the web service
        String input = "{\"Inputs\":{\"input1\":{\"ColumnNames\":[\"mileage\",\"make\",\"model\",\"fuel\",\"gear\",\"offerType\",\"price\",\"hp\",\"year\"],\"Values\":[[\""+userInput.getMileage()+"\",\""+userInput.getMake()+"\",\""+userInput.getModel()+"\",\""+userInput.getFuel()+"\",\""+userInput.getGear()+"\",\""+userInput.getOfferType()+"\",\""+0+"\",\""+userInput.getHp()+"\",\""+userInput.getYear()+"\"]]}}}";

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
            JSONObject jsonResponse = new JSONObject(response);
            JSONObject output1 = jsonResponse.getJSONObject("Results").getJSONObject("output1");
            JSONArray values = output1.getJSONObject("value").getJSONArray("Values");
            String scoredLabel = values.getJSONArray(0).getString(9);
            System.out.println(scoredLabel);
            connection.disconnect();
            return scoredLabel;
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