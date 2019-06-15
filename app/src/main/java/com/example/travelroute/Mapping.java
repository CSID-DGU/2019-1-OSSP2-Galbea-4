package com.example.travelroute;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapMarkerItem2;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_NUMBER_VARIATION_NORMAL;

public class Mapping extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {

    //tmap gps 객체
    private TMapGpsManager tmapgps = null;
    TMapView tMapView;
    private String server_key = "77ced029-58c5-462e-8a8f-6a34c963d1b7";
    //지도 줌 정도
    int zoom = 18;
    //처음 데이터 인지 확인용
    private boolean first_location = true;


    //사용자가 지도를 스크롤 할 경우를 알려주는 플래그 값
    // 사용자가 지도를 움직일때 위치가 갱신되면 카메라가 이동하므로 카메라 이동을 방지하기 위한 플래그
    // 사용자가 지도를 움직였을때 원래 위치로 돌아가기 버튼이 활성화 되며 누르면 다시 위치로 카메라를 갱신
    private boolean user_controlling = false;

    //길안내 정보 표시
    TextView info;
    //간판
    TextView pred_time, pred_distance, target;
    //내위치로 이동
    ImageButton my_location;
    //현재 위치
    Location current_location;

    //찾은 포인트 데이터리스트
    ArrayList<Point> Points = new ArrayList<>();

    //총 시간 및 거리
    int allTime = -1, allDistance = -1;
    String start_name = "출발지", end_name = "목적지";

    //목적지의 이름과 데이터
    // TODO: 실제 테스트에서는 "" 내부 값 지우기
    String name = "홍대입구역", data = "홍대입구";

    /////////////////////임시위치
    // TODO: 실제 테스트에서는 좌표값만 지우기
    String start_x = "126.92365493654832", start_y = "37.556770374096615";
    String end_x = "126.92432158129688", end_y = "37.55279861528311";
    ////////////////////


    //돌아가기 버튼
    Button back;

    //firebase.db
    FirebaseFirestore db;


    @Override
    public void onLocationChange(final Location location) {

        current_location = location;

        Log.d("current location", location.getLatitude() + ", " + location.getLongitude());

        //움직임이 있을때
        if (first_location) {

            // TODO 실제 테스트에서는 주석 해제
//            start_y = String.valueOf(location.getLatitude());
//            start_x = String.valueOf(location.getLongitude());

            //위치에 마커 표시및 이동
            gotoMYLocation(current_location);

            //처음 위치를 불러올 때 해당 위치정보를 기준으로 길안내를 로드 합니다.
            RequestQueue queue = Volley.newRequestQueue(Mapping.this);
            String url = "https://api2.sktelecom.com/tmap/routes/pedestrian";

            StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("TAG1", response.toString());

                    //데이터들을 담을 arraylist 준비
                    Points.clear();

                    //response 에는 json data가 있음
                    //포인트 정보를 가져옵니다.
                    /**
                     * 하나의 포인트
                     *
                     * 1.포인트 순서
                     * 2.좌표정보
                     * 3.안내정보
                     *
                     * 4.포인트 타입
                     * 첫 좌표일 경우 경로 총 길이 및 경로 총 소요시간을 가져옵니다.
                     *
                     */

                    try {
                        //전체 데이터 입니다.
                        JSONObject data = new JSONObject(response);

                        //특징 점목록을 가져옵니다.
                        JSONArray features = data.getJSONArray("features");

                        //전체 포인트의 개수 입니다.
                        int total_points = features.length();

                        //각 포인트의 데이터를 가져옵니다.
                        for (int i = 0; i < total_points; i++) {

                            //1feature 정보
                            JSONObject feature = features.getJSONObject(i);

                            //1-1geometry 정보 = 형상 정보 입니다. (geojson 표준 규격)
                            JSONObject geometry = feature.getJSONObject("geometry");

                            //1-1-coordinates 정보 2.
                            /**
                             * 좌표 정보입니다.
                             * - 경도와 위도는 콤마(,)로 구분하여 '[',']'으로 묶어주고
                             * 복수 좌표 역시 콤마로 구분하여 '[',']'으로 묶어줍니다.
                             * -형식: [[x1좌표, y1좌표], [x2좌표, y2좌표]]
                             */
                            String coordinates = geometry.getString("coordinates");

                            /**
                             *
                             * 해당 type
                             *
                             *
                             */
                            String type = geometry.getString("type");

                            //해당 타입이 Point 인 경우만 추가합니다.
                            if (type.equals("Point")) {

                                //1-2properties 정보 = 사용자 정의 프로퍼티 정보입니다.
                                JSONObject properties = feature.getJSONObject("properties");

                                //1-2-index 정보 1.
                                /**
                                 * 경로 순번입니다.
                                 */
                                String pointIndex = properties.getString("pointIndex");


                                /**
                                 * 길 안내 정보입니다.
                                 */
                                String description = properties.getString("description");

                                /**
                                 * 안내지점의 구분입니다.
                                 *
                                 * - SP: 출발지
                                 * - EP: 도착지
                                 * - PP: 경유지
                                 * - PP1: 경유지1
                                 * - PP2: 경유지2
                                 * - PP3: 경유지3
                                 * - PP4: 경유지4
                                 * - PP5: 경유지5
                                 * - GP: 일반 안내점
                                 */

                                String pointType = properties.getString("pointType");

                                /**
                                 * - pointType=SP 일때 응답되는 정보입니다
                                 *
                                 * 소요시간(초) 및 총 길이(m).
                                 */

                                if (pointType.equals("SP")) {
                                    //첫 포인트 입니다. 소요시간 및 총 길이를 가져옵니다.

                                    String totalDistance = properties.getString("totalDistance");
                                    String totalTime = properties.getString("totalTime");


                                    //찾은 데이터를 Point 객체로 만듭니다.
                                    //좌표는 경도 위도를 나누어 저장 합니다.

                                    String coordinates_int = coordinates.substring(1, coordinates.length() - 1);
                                    String cor_x = coordinates_int.split(",")[0];
                                    String cor_y = coordinates_int.split(",")[1];

                                    Point point = new Point(cor_x, cor_y, pointIndex, description, pointType);

                                    //소요시간및 길이 데이터를 저장합니다.
                                    point.setTotalDistance(totalDistance);
                                    point.setTotalTime(totalTime);

                                    //포인트 정보를 arraylist 에 추가합니다.
                                    Points.add(point);


                                } else {
                                    //나머지 포인트 들은 토탈 데이터가 없습니다.

                                    //찾은 데이터를 Point 객체로 만듭니다.
                                    //좌표는 경도 위도를 나누어 저장 합니다.

                                    String coordinates_int = coordinates.substring(1, coordinates.length() - 1);
                                    String cor_x = coordinates_int.split(",")[0];
                                    String cor_y = coordinates_int.split(",")[1];

                                    Point point = new Point(cor_x, cor_y, pointIndex, description, pointType);


                                    //포인트 정보를 array list 에 추가합니다.
                                    Points.add(point);

                                }


                            }

                        }


                        //Points 를 이용하여 지도에 루트를 그립니다.
                        /**
                         *
                         * 1. 지정된 포인트들에 만든 이미지를 찍습니다.
                         *  SP 는 시작점 이미지를
                         *  EP 는 도착점 이미지를
                         *  나머지는 일반 이미지를 이용합니다.
                         *
                         * 2. 지정된 포인트 들을 연결합니다.
                         *
                         */

                        //라인을 그리기 위한 리스트 입니다.
                        ArrayList<TMapPoint> alTMapPoint = new ArrayList<TMapPoint>();

                        //포인트 점 찍기
                        for (int i = 0; i < Points.size(); i++) {
                            //마커 표시.
                            setPointMarker(Points.get(i));

                            //Tmappoint 작성.
                            alTMapPoint.add(new TMapPoint(Double.valueOf(Points.get(i).getCor_y()), Double.valueOf(Points.get(i).getCor_x())));


                        }

                        //라인 그리기.
                        TMapPolyLine tMapPolyLine = new TMapPolyLine();
                        tMapPolyLine.setLineColor(getResources().getColor(R.color.map_line));
                        tMapPolyLine.setLineWidth(20);
                        for (int i = 0; i < alTMapPoint.size(); i++) {
                            tMapPolyLine.addLinePoint(alTMapPoint.get(i));
                        }
                        tMapView.addTMapPolyLine("route", tMapPolyLine);


                        //저장해 두었던 토탈 시간과 토탈 거리를 가져옵니다.
                        allTime = Integer.parseInt(Points.get(0).getTotalTime());
                        allDistance = Integer.parseInt(Points.get(0).getTotalDistance());


                        //시간 및 거리를 표시합니다.
                        checkTimeDistanceInfo();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("TAG2", error.getMessage(), error);

//                    String body;
//                    //get status code here
//                    String statusCode = String.valueOf(error.networkResponse.statusCode);
//                    //get response body and parse with appropriate encoding
//                    if (error.networkResponse.data != null) {
//                        try {
//                            body = new String(error.networkResponse.data, "UTF-8");
//
//                            Log.e("statusCode", statusCode);
//                            Log.e("body", body);
//
//
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                    }

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
//                    params.put("startX", String.valueOf(location.getLongitude()));
//                    params.put("startY", String.valueOf(location.getLatitude()));
                    params.put("startX", start_x);
                    params.put("startY", start_y);
                    params.put("speed", "4");
                    params.put("endX", end_x);
                    params.put("endY", end_y);
                    params.put("startName", start_name);
                    params.put("endName", end_name);

                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/x-www-form-urlencoded");
                    headers.put("Accept-Language", "ko");
                    headers.put("appKey", server_key);
                    headers.put("Host", "api2.sktelecom.com");
                    return headers;
                }
            };


            //데이터를 요청합니다;.
            queue.add(sr);

            //처음 위치를 불러올 때만 이동시킵니다.
            first_location = false;

        } else {
            //위치가 이동될경우 해당 위치를 갱신합니다.
            gotoMYLocation(current_location);

            //거리 및 시간을 갱신합니다.
            checkTimeDistanceInfo();
        }


    }


    @Override
    public void onBackPressed() {
        showAlert();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__mapping);

        Intent intent = getIntent();
        String[] Current = intent.getStringArrayExtra("Current");
        String[] Data = intent.getStringArrayExtra("Data");

        name = Data[0];
        data = Data[1];
        start_x = Current[1];
        start_y = Current[0];
        end_x = Data[4];
        end_y = Data[3];


        //firebase 초기화.
//        FirebaseApp.initializeApp(getApplicationContext());
        db = FirebaseFirestore.getInstance();

        // TODO: 실제 테스트 에서는 풀기!!
//        end_y = getIntent().getStringExtra("lat");
//        end_x = getIntent().getStringExtra("lon");
//        name = getIntent().getStringExtra("name");
//        data = getIntent().getStringExtra("data");


        info = findViewById(R.id.info);
        my_location = findViewById(R.id.my_location);
        pred_time = findViewById(R.id.pred_time);
        pred_distance = findViewById(R.id.pred_distance);
        target = findViewById(R.id.target);
        back = findViewById(R.id.back);

        target.setText(name);


        // 퍼미션 설정
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                //Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();

                // 뷰 설정
                LinearLayout linearLayoutTmap = findViewById(R.id.linearLayoutTmap);
                tMapView = new TMapView(Mapping.this);
                tMapView.setHttpsMode(true);
                tMapView.setSKTMapApiKey(server_key);
                tMapView.setIconVisibility(true);
                linearLayoutTmap.addView(tMapView);

                // gps 동작
                tmapgps = new TMapGpsManager(Mapping.this);

                //1초 1m 간격으로 위치를 갱신
                tmapgps.setMinTime(1);
                tmapgps.setMinDistance(1);
                tmapgps.setProvider(tmapgps.NETWORK_PROVIDER);
                tmapgps.OpenGps();

                // 클릭 이벤트 설정
                tMapView.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {
                    @Override
                    public boolean onPressEvent(ArrayList arrayList, ArrayList arrayList1, TMapPoint tMapPoint, PointF pointF) {
                        //Toast.makeText(MapEvent.this, "onPress~!", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onPressUpEvent(ArrayList arrayList, ArrayList arrayList1, TMapPoint tMapPoint, PointF pointF) {
                        //Toast.makeText(MapEvent.this, "onPressUp~!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

                // 롱 클릭 이벤트 설정
                tMapView.setOnLongClickListenerCallback(new TMapView.OnLongClickListenerCallback() {
                    @Override
                    public void onLongPressEvent(ArrayList arrayList, ArrayList arrayList1, TMapPoint tMapPoint) {
                        //Toast.makeText(MapEvent.this, "onLongPress~!", Toast.LENGTH_SHORT).show();
                    }
                });

                // 지도 스크롤 종료
                tMapView.setOnDisableScrollWithZoomLevelListener(new TMapView.OnDisableScrollWithZoomLevelCallback() {
                    @Override
                    public void onDisableScrollWithZoomLevelEvent(float zoom, TMapPoint centerPoint) {
                        //Toast.makeText(MainActivity.this, "zoomLevel=" + zoom + "\nlon=" + centerPoint.getLongitude() + "\nlat=" + centerPoint.getLatitude(), Toast.LENGTH_SHORT).show();
                    }

                });

                // 지도 이동
                tMapView.setOnEnableScrollWithZoomLevelListener(new TMapView.OnEnableScrollWithZoomLevelCallback() {
                    @Override
                    public void onEnableScrollWithZoomLevelEvent(float v, final TMapPoint tMapPoint) {

                        //지도를 이용하면 현재 위치 갱신을 종료 하며 현재 위치로 가기 버튼을 활성화 합니다.
                        user_controlling = true;
                        my_location.setVisibility(View.VISIBLE);
                        my_location.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                //다시 화면을 갱신합니다.
                                user_controlling = false;

                                if (current_location != null) {
                                    //현재 위치로 화면을 이동시킵니다.
                                    gotoMYLocation(current_location);

                                    //다시 버튼을 비활성화 합니다.
                                    my_location.setVisibility(View.INVISIBLE);
                                }


                            }
                        });


                    }
                });


                //마커 클릭시.
                tMapView.setOnMarkerClickEvent(new TMapView.OnCalloutMarker2ClickCallback() {
                    @Override
                    public void onCalloutMarker2ClickEvent(String s, TMapMarkerItem2 tMapMarkerItem2) {

                    }
                });
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                //Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(Mapping.this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("퍼미션 미허용시 설정에서 수동으로 허용해주셔야 이용가능합니다.")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();


        //돌아가기 버튼 설정.
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //onBackPressed 와 같은 이벤트를 방생시킵니다.
                showAlert();

            }
        });

    }

    //시간 및 거리, 길안내 정보를 갱신
    private void checkTimeDistanceInfo() {

        //point 데이터가 있는 상태
        //현재의 위치와 가장 가까운 위치의 좌표를 가져옵니다.
        Point nearestPoint = null;
        for (int i = 0; i < Points.size(); i++) {
            if (i == 0)
                nearestPoint = Points.get(i);
            else {

                //저장된 포인트와 거리를 계산해서 작을경우 nearest 포인트를 갱신합니다.

                double currentDistance = calDinstance(nearestPoint);
                double calDistance = calDinstance(Points.get(i));

                if (calDistance < currentDistance) {
                    nearestPoint = Points.get(i);
                }

            }

        }

        //가장 가까운 포인트의 정보로 갱신합니다.
        //info 갱신
        //가져온 좌표의 안내 데이터를 표시합니다..
        if (nearestPoint != null) {
            info.setText(nearestPoint.getDescription());
        }

        //시간 및 거리 갱신.
        if (nearestPoint != null) {

            int cal_time = allTime * Integer.parseInt(nearestPoint.getPointIndex()) / Points.size(); //초단위
            int cal_distance = allDistance * Integer.parseInt(nearestPoint.getPointIndex()) / Points.size();//m 단위


            // 남은 거리및 시간을 point 기준으로 계산하여 표시합니다.
            pred_time.setText((cal_time / 60) + " 분");
            pred_distance.setText(cal_distance + " m");

            if (cal_distance > 1000) {
                pred_distance.setText((cal_distance / 1000) + "km");
            }

            if (Integer.parseInt(nearestPoint.getPointIndex()) == 0) {
                pred_time.setText((allTime / 60) + " 분");
                pred_distance.setText(allDistance + " m");

                if (cal_distance > 1000) {
                    pred_distance.setText((allDistance / 1000) + "km");
                }

            }


        }


    }

    // 현재의 위치와 포인트의 거리를 계산
    private double calDinstance(Point point) {

        double point_lon = Double.parseDouble(point.getCor_x());
        double point_lat = Double.parseDouble(point.getCor_y());

        double cur_lat = current_location.getLatitude();
        double cur_lon = current_location.getLongitude();

        double theta = point_lon - cur_lon;

        double dist = Math.sin((point_lat * Math.PI / 180.0)) * Math.sin((cur_lat * Math.PI / 180.0)) + Math.cos((point_lat * Math.PI / 180.0)) * Math.cos((cur_lat * Math.PI / 180.0)) * Math.cos((theta * Math.PI / 180.0));
        dist = Math.acos(dist);
        dist = (dist * 180 / Math.PI);
        dist = dist * 60 * 1.1515;
        dist = dist * 1609.344;

        //m 단위의 거리를 반환합니다.
        return dist;
    }


    // 마커를 찍어주는 메소드
    private void setPointMarker(Point point) {

        //위치 마킹
        Double cor_x = Double.valueOf(point.getCor_x());
        Double cor_y = Double.valueOf(point.getCor_y());

        TMapMarkerItem markerItem = new TMapMarkerItem();
        TMapPoint tMapPoint = new TMapPoint(cor_y, cor_x);


        //마커 아이콘은 타입에 따라서 다르게 설정합니다.
        Bitmap bitmap = BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.point);

        if (point.getPointType().equals("SP"))
            bitmap = BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.start);
        else if (point.getPointType().equals("EP"))
            bitmap = BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.stop);

        markerItem.setIcon(bitmap); // 마커 아이콘 지정
        markerItem.setPosition(0.0f, 0.0f);
        markerItem.setTMapPoint(tMapPoint); // 마커의 좌표 지정
        markerItem.setName("point" + point.getPointIndex()); // 마커의 타이틀 지정
        markerItem.setCanShowCallout(true);
        markerItem.setCalloutTitle("point" + point.getPointIndex());
        markerItem.setCalloutSubTitle(point.getDescription());
        markerItem.setAutoCalloutVisible(true);

        //마지막 좌표에 데이터 설정합니다.
        if (point.getPointType().equals("EP")) {
            markerItem.setName(name); // 마커의 타이틀 지정
            markerItem.setCalloutTitle(name);
            markerItem.setCalloutSubTitle(data);
        }

        tMapView.addMarkerItem("point" + point.getPointIndex(), markerItem); // 지도에 마커 추가


    }

    // 현재 gps 위치로 이동
    private void gotoMYLocation(Location location) {

        if (!user_controlling) {
            //사용자가 스크롤 중이 아닐때만 화면을 이동시킵니다.
            tMapView.setLocationPoint(location.getLongitude(), location.getLatitude());
            tMapView.setCenterPoint(location.getLongitude(), location.getLatitude(), true);
            tMapView.setZoomLevel(zoom);
        }

    }


    //다이얼로그를 띄워서 평점을 등록합니다.
    private void showAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Mapping.this);
        builder.setCancelable(false);
        builder.setTitle(name + "의 평점을 입력해주세요!");
        builder.setMessage("평점은 익명으로 등록됩니다. 정확한 정보를 위해 평점을 입력해주세요.");
        final EditText inputValue = new EditText(Mapping.this);
        inputValue.setPadding(60, 50, 20, 30);
        inputValue.setBackgroundColor(Color.parseColor("#00000000"));
        inputValue.setHint("0~100 사이 점수를 적어주세요.");
        inputValue.setTextColor(Color.RED);
        inputValue.setTextSize(15);
        inputValue.setInputType(TYPE_CLASS_NUMBER | TYPE_NUMBER_VARIATION_NORMAL);
        builder.setView(inputValue);
        builder.setPositiveButton("평점등록", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (inputValue.getText().length() == 0) {
                    Toast.makeText(Mapping.this, "평점을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    final int value = Integer.valueOf(inputValue.getText().toString());
                    if (value > 100 || value < 0) {
                        Toast.makeText(Mapping.this, "평점은 0점에서 100점까지 입력하실 수 있습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        //등록 가능.
                        //현재 등록되어 있는 문서 수를 체크한다.


                        db.collection("guest")
                                .whereGreaterThanOrEqualTo("score", 0) //점수가 0 점보다 같거나 큰 데이터. 즉 모든 데이터/
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {

                                            //총 문서개수 가져오기.
                                            QuerySnapshot get_document = task.getResult();
                                            //총 문서 개수.
                                            int totalCount = get_document.size();
                                            Log.d("tag", String.valueOf(totalCount));

                                            //데이터 추가.
                                            Map<String, Object> score = new HashMap<>();
                                            score.put("name", name);
                                            score.put("location", data);
                                            score.put("score", value);


                                            db.collection("guest").document("guest" + (totalCount + 1))
                                                    .set(score)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Log.d("tag", "DocumentSnapshot successfully written!");
                                                            Toast.makeText(Mapping.this, "평점이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                                                            finish();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.w("tag", "Error writing document", e);
                                                            Toast.makeText(Mapping.this, "평점이 등록되지 않았습니다,", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                        } else {
                                            Log.d("tag", "Error getting documents: ", task.getException());
                                        }
                                    }
                                });

                    }

                }


            }
        });
        builder.setNegativeButton("나가기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();


    }

}