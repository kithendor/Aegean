package com.app.kitsos.jsonaki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //trexei sto background kai katevazei to uliko
    public class DownloadPrama extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... res) {
            String result = "";
            URL url;
            HttpURLConnection urlcon = null;

            try {
                url = new URL(res[0]);

                urlcon = (HttpURLConnection) url.openConnection();

                InputStream inp = urlcon.getInputStream();
                InputStreamReader reader = new InputStreamReader(inp);

                int data = reader.read();

                while (data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonobject = new JSONObject(s);
                String info = jsonobject.getString("weather");

                Log.i("apotelesmaa",info);

                JSONArray arr = new JSONArray(info);

                for (int i=0;i<arr.length();i++){
                    JSONObject jsonpart = arr.getJSONObject(i);
                    Log.i("main ",jsonpart.getString("main"));
                    Log.i("description ",jsonpart.getString("description"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadPrama dn = new DownloadPrama();
        dn.execute("http://openweathermap.org/data/2.5/weather?q=Athens&appid=b6907d289e10d714a6e88b30761fae22");
    }
}
