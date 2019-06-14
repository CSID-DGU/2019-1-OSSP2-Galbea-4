package com.example.travelroute;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetApiClient extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_api);
    }
    final static String openAPIURL = "https://us-central1-travelmaker-a02d4.cloudfunctions.net/fun";

    public GetAPI getAPI(double lat,double lon, String category){
        GetAPI w = new GetAPI();
        String line;
        BufferedReader bf;
        String result = "";
        String urlString = openAPIURL + "?lat="+lat+"&lon="+lon+"&category="+category;
        try {
            // call API by using HTTPURLConnection
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

//            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);

//            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);



            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            bf = new BufferedReader(new InputStreamReader(url.openStream()));
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            while((line=bf.readLine())!=null){
                result=result.concat(line);
                //System.out.println(line);
            }


            // parse JSON

            w = parseJSON(json);

            w.setIon(lon);

            w.setLat(lat);

            w.setCategory(category);



        }catch(MalformedURLException e){
            System.err.println("Malformed URL");
            e.printStackTrace();
            return null;

        }catch(JSONException e) {
            System.err.println("JSON parsing error");
            e.printStackTrace();
            return null;

        }catch(IOException e){

            System.err.println("URL Connection failed");

            e.printStackTrace();

            return null;

        }



        // set Weather Object



        return w;

    }



    private GetAPI parseJSON(JSONObject json) throws JSONException {

        GetAPI w1= new GetAPI();
        GetAPI w2= new GetAPI();
        GetAPI w3= new GetAPI();

        w1.setLocation(json.getJSONObject("main").getString("temp"));
        w1.setName(json.getString("name"));
        w1.setScore(json.getJSONObject("main").getInt("temp"));

        w2.setLocation(json.getJSONObject("main").getString("temp"));
        w2.setName(json.getString("name"));
        w2.setScore(json.getJSONObject("main").getInt("temp"));

        w3.setLocation(json.getJSONObject("main").getString("temp"));
        w3.setName(json.getString("name"));
        w3.setScore(json.getJSONObject("main").getInt("temp"));
        //w.setCloudy();



        return w1;

    }



    private static String getStringFromInputStream(InputStream is) {



        BufferedReader br = null;

        StringBuilder sb = new StringBuilder();



        String line;

        try {



            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {

                sb.append(line);

            }



        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (br != null) {

                try {

                    br.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }



        return sb.toString();



    }

}