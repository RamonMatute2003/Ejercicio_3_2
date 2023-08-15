package com.example.ejercicio3_2;

import android.os.AsyncTask;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendMessageTask extends AsyncTask<Void, Void, Integer> {

    @Override
    protected Integer doInBackground(Void... voids) {
        try {
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //configuracion y key unuico
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "key=AAAA0gYY8SM:APA91bGhrB6-Rcc3XhHNMIQ8kechThLB6QxgfI-mAhvwJ0xPY8wzJYVtI_zgWewDwxLmeCPODfqxobooMddcew6CqOKfiNA9UvQhfw1OazFb5_4uvWC_910oVZLp5YDTwGx8weXp7td8");
            conn.setDoOutput(true);

            // Crea el mensaje en formato JSON
            String message = "{" +
                    "\"to\": \"" + Token.token + "\"," +
                    "\"notification\": {" +
                    "\"title\": \"Mensaje local\"," +
                    "\"body\": \"Hola ingeniero\"" +
                    "}," +
                    "\"data\": {" +
                    "\"message\": \"Como esta?\"" +
                    "}" +
                    "}";

            // Envía el mensaje
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = message.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Obtiene la respuesta
            int responseCode = conn.getResponseCode();
            return responseCode;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    protected void onPostExecute(Integer responseCode) {
        super.onPostExecute(responseCode);
        // Maneja el resultado de la operación de red aquí, por ejemplo, muestra un mensaje al usuario
    }
}

