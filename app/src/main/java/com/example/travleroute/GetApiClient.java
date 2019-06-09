package com.example.travelroute;

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

public class GetApiClient {

    final static String openAPIURL = "http://api.openweathermap.org/data/2.5/weather";

    public GetAPI getAPI(int lat,int lon){

        GetAPI w = new GetAPI();

        String urlString = openAPIURL + "?lat="+lat+"&lon="+lon;



        try {

            // call API by using HTTPURLConnection

            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

//            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);

//            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);



            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            JSONObject json = new JSONObject(getStringFromInputStream(in));



            // parse JSON

            w = parseJSON(json);

            w.setIon(lon);

            w.setLat(lat);



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

        GetAPI w = new GetAPI();

        w.setTemprature(json.getJSONObject("main").getInt("temp"));

        w.setCity(json.getString("name"));

        //w.setCloudy();



        return w;

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