package com.dev.loginregproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public abstract class SendToServer {

    private ConnectServer connectServer;
    public SendToServer(Context context,JSONObject payload){
        connectServer = new ConnectServer(context, String.valueOf(payload));

    }

    public void start(String url){
        connectServer.execute(Util.BASE_URL+url);
    }

    public abstract void onResultArrive(String result);

    private class ConnectServer extends AsyncTask<String,Void,String>{

        private String mPayload;
        private ProgressDialog progressDialog;

        public ConnectServer(Context context,String payloadObj){
            mPayload = payloadObj;
            progressDialog = ProgressDialog.show(context,"Connecting...","Connecting server");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {

            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(urls[0]);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setRequestProperty("Content-Type","application/json");
                httpsURLConnection.setRequestProperty("Accept","application/json");
                httpsURLConnection.setReadTimeout(25000);
                httpsURLConnection.setConnectTimeout(20000);
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);

                OutputStream outputStream = httpsURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(mPayload);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                httpsURLConnection.connect();

                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

                String line = "";

                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            onResultArrive(s);
            progressDialog.dismiss();
        }
    }

}
