package com.lerocas.pokeapiapp.utils;

import java.io.*;
import java.net.URL;

/*
 * This class allows to get a request and parse it to a string to be treated on Java
 */
public class Information {

    private static String get(BufferedReader bufferedReader) {
        try {
            StringBuilder stringBuilder = new StringBuilder();

            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
                stringBuilder.append(System.lineSeparator());
            }

            bufferedReader.close();
            return stringBuilder.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String fromInternet(String targetURL) {
        try {
            System.setProperty("http.agent", "Chrome");
            URL url = new URL(targetURL);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            return Information.get(bufferedReader);
        } catch (Exception e) {
            e.printStackTrace();            
            System.out.println("COULDN'T REACH API");
            return "";
        }
    }
}