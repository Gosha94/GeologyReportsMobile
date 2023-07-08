package com.teligent.mobile;

//public class OfflineMapActivity extends AppCompatActivity implements MapListener
//{
//    OfflineMapView offlineMapView;
//    MapUtils mapUtils;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        offlineMapView = findViewById(R.id.map);
//        offlineMapView.init(this, this);
//    }
//
//
//    @Override
//    public void mapLoadSuccess(MapView mapView, MapUtils mapUtils) {
//
//        // GeoPoint belongs to ISTANBUL heart of the world :)
//        this.mapUtils = mapUtils;
//        offlineMapView.setInitialPositionAndZoom(new GeoPoint(41.025135, 28.974101), 14.5);
//
//        // 41.025135, 28.974101 Galata Tower
//
//        Marker marker = new Marker(mapView);
//        marker.setPosition(new GeoPoint(41.025135, 28.974101));
//        marker.setIcon(getResources().getDrawable(R.drawable.galata_tower));
//
//        // marker.setImage(drawable);
//
//        marker.setTitle("Hello Istanbul");
//        marker.showInfoWindow();
//        mapView.getOverlays().add(marker);
//        mapView.invalidate();
//    }
//
//    @Override
//    public void mapLoadFailed(String ex) {
//        Log.e("ex:", ex);
//    }
//
//    @Override
//    public void onGeoPointRecieved(GeoPoint geoPoint) {
//
//        //Selected GeoPoint Returns Here
//
//        Toast.makeText(this, geoPoint.toDoubleString(), Toast.LENGTH_SHORT).show();
//    }
//
//    public void activateAnimatePicker(View view) {
//        if (mapUtils != null)
//            offlineMapView.setAnimatedLocationPicker(true, this, mapUtils);
//    }
//}