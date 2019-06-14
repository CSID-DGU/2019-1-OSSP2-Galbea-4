package com.example.travelroute;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import org.json.JSONArray;

import org.json.JSONException;

import org.json.JSONObject;


public class SubActivity3 extends AppCompatActivity{

    private static final String TAG = "imagesearchexample";
    public static final int LOAD_SUCCESS = 101;
    private final int MY_PERMISSIONS_REQUEST_CAMERA=1001;

    private String SEARCH_URL = "https://us-central1-travelmaker-a02d4.cloudfunctions.net/fun?";
    private String lat = "lat=37.5625339";
    private String lng = "&lng=126.9857421";

    private String REQUEST_URL;

    private ProgressDialog progressDialog;
    private Button JSONText1;
    private Button JSONText2;
    private Button JSONText3;
    private String[] Loca =  new String[2];
    static String[][] arraysum = new String[3][6];

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    Context context = this;
    TextView user_location;
    private FusedLocationProviderClient mFusedLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        String category2 = "&category="+category;

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fetchLocation();

        REQUEST_URL = SEARCH_URL + lat + lng + category2;

        JSONText1 = (Button)findViewById(R.id.textview_main_jsontext1);
        JSONText2 = (Button)findViewById(R.id.textview_main_jsontext2);
        JSONText3 = (Button)findViewById(R.id.textview_main_jsontext3);

        JSONText1.setMovementMethod(new ScrollingMovementMethod());

        progressDialog = new ProgressDialog( SubActivity3.this );
        progressDialog.setMessage("Please wait.....");
        progressDialog.show();

        getJSON();

        JSONText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(SubActivity3.this, Mapping.class);

                intent.putExtra("Current",Loca);
                intent.putExtra("Data", arraysum[0]);

                startActivity(intent);
            }
        });
        JSONText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(SubActivity3.this, Mapping.class);

                intent.putExtra("Current",Loca);
                intent.putExtra("Data", arraysum[1]);

                startActivity(intent);
            }
        });
        JSONText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(SubActivity3.this, Mapping.class);

                intent.putExtra("Current",Loca);
                intent.putExtra("Data", arraysum[2]);

                startActivity(intent);
            }
        });



    }
    private void fetchLocation() {


        if (ContextCompat.checkSelfPermission(SubActivity3.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(SubActivity3.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                new AlertDialog.Builder(this)
                        .setTitle("Required Location Permission")
                        .setMessage("You have to give this permission to acess this feature")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(SubActivity3.this,
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(SubActivity3.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                Double latittude = location.getLatitude();
                                Double longitude = location.getLongitude();

                                Loca[0] =Double.toString(latittude);
                                Loca[1] =Double.toString(longitude);

                                lat="lat="+latittude;
                                lng="&lng="+longitude;
                            }
                        }
                    });

        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //abc
            }else{

            }
        }
    }


    private final MyHandler mHandler = new MyHandler(this);


    private static class MyHandler extends Handler {
        private final WeakReference<SubActivity3> weakReference;

        public MyHandler(SubActivity3 mainactivity) {
            weakReference = new WeakReference<SubActivity3>(mainactivity);
        }

        @Override
        public void handleMessage(Message msg) {

            SubActivity3 mainactivity = weakReference.get();

            if (mainactivity != null) {
                switch (msg.what) {

                    case LOAD_SUCCESS:
                        mainactivity.progressDialog.dismiss();

                        String jsonString = (String)msg.obj;

                        try {
                            JSONArray jarray = new JSONObject(jsonString).getJSONArray("recommend");
                            for (int i = 0; i < 3; i++) {
                                HashMap map = new HashMap<>();
                                JSONObject jObject = jarray.getJSONObject(i);

                                arraysum[i][0] = jObject.optString("name");
                                arraysum[i][1] = jObject.optString("location");
                                arraysum[i][2] = jObject.optString("score");
                                arraysum[i][3] = jObject.optString("lat");
                                arraysum[i][4] = jObject.optString("lng");
                                arraysum[i][5] = jObject.optString("category");
                            }
                            String a1 = arraysum[0][0] + "   " + arraysum[0][2];
                            String a2 = arraysum[1][0] + "   " + arraysum[1][2];
                            String a3 = arraysum[2][0] + "   " + arraysum[2][2];

                            mainactivity.JSONText1.setText(a1);
                            mainactivity.JSONText2.setText(a2);
                            mainactivity.JSONText3.setText(a3);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        }
    }

    public void  getJSON() {

        Thread thread = new Thread(new Runnable() {

            public void run() {

                String result;

                try {

                    Log.d(TAG, REQUEST_URL);
                    URL url = new URL(REQUEST_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                    httpURLConnection.setReadTimeout(20000);
                    httpURLConnection.setConnectTimeout(20000);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.connect();


                    int responseStatusCode = httpURLConnection.getResponseCode();

                    InputStream inputStream;
                    if (responseStatusCode == HttpURLConnection.HTTP_OK) {

                        inputStream = httpURLConnection.getInputStream();
                    } else {
                        inputStream = httpURLConnection.getErrorStream();

                    }


                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder sb = new StringBuilder();
                    String line;


                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }

                    bufferedReader.close();
                    httpURLConnection.disconnect();

                    result = sb.toString().trim();


                } catch (Exception e) {
                    result = e.toString();
                }


                Message message = mHandler.obtainMessage(LOAD_SUCCESS, result);
                mHandler.sendMessage(message);
            }

        });
        thread.start();
    }

}