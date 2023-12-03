package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        URL urlObj = new URL("https://jsonplaceholder.typicode.com/posts");
        HttpURLConnection conexao = (HttpURLConnection) urlObj.openConnection();
        conexao.setRequestProperty("Accept", "application/json");
        conexao.setDoOutput(true);
        conexao.setRequestMethod("POST");

        ObjectMapper novoMapper = new ObjectMapper();
        Output saida = new Output();
        saida.setId(5);
        saida.setUserId(5);
        saida.setTitle("King at turn");
        saida.setCompleted(false);
        saida.setBody("King at turn, is an topic that anybody talks est rerrum nulla");

        String jsonPostar = novoMapper.writeValueAsString(saida);


        conexao.getOutputStream().write(jsonPostar.getBytes());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        StringBuffer response = new StringBuffer();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();

        System.out.println(response);
    }
}


