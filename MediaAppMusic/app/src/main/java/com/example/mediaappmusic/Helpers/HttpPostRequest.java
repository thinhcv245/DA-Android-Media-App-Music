package com.example.mediaappmusic.Helpers;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPostRequest extends AsyncTask<String, String, String> {
    public static final String REQUEST_METHOD = "POST";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    @Override
    protected String doInBackground(String... strings) {
        String stringUrl = strings[0];
        String params = strings[1];
        String result;
        String inputLine;
        String response = "";
        try {
            // Create URL object
            URL url = new URL(stringUrl);

            // Create HTTPURLConnection object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            // Create JSON object
//            JSONObject jsonParam = new JSONObject();
//            jsonParam.put("name", "John");
//            jsonParam.put("age", 30);
//            jsonParam.put("email", "john@example.com");

            // Send POST request
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(params);
            os.flush();
            os.close();


            // Read response
            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                if(line != null) {
                    response += line;
                }
            }
            bufferedReader.close();
            inputStream.close();
            conn.disconnect();
        } catch (Exception e) {
            Log.e("TAG", "Error: " + e.toString());
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d("TAG", "Response: " + result);
    }
}
