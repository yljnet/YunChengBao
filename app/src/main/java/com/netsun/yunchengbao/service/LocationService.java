package com.netsun.yunchengbao.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.netsun.yunchengbao.activity.MainActivity;

/**
 * Created by Administrator on 2016/12/9.
 */

public class LocationService extends IntentService {
    LocationClient mlocationClient = null;
    public LocationService() {
        super("LocationService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        List<String> permissionList = new ArrayList<String>();
//        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.
//                permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
//        }
//        if (ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.
//                permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            permissionList.add(Manifest.permission.READ_PHONE_STATE);
//        }
//        if (ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.
//                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        }
//        if (!permissionList.isEmpty()) {
//            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
//            ActivityCompat.requestPermissions(MainActivity,permissions,1);
//        } else{
//            requestLocation();
//        }

    }

    private void requestLocation() {
        mlocationClient.start();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mlocationClient = new LocationClient(getApplicationContext());
        mlocationClient.registerLocationListener(new MyLocationListener());
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        mlocationClient.setLocOption(option);
        mlocationClient.start();
    }

    @Override
    public void onDestroy() {
        mlocationClient.stop();
        super.onDestroy();
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("维度：").append(bdLocation.getLatitude()).append("\n");
            currentPosition.append("经线：").append(bdLocation.getLongitude()).append("\n");
            currentPosition.append("定位方式：");
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                currentPosition.append("GPS");
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                currentPosition.append("网络");
            }
            Log.d(MainActivity.TAG, currentPosition.toString());
        }
    }
}
