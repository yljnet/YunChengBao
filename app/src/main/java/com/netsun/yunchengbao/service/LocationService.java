package com.netsun.yunchengbao.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.netsun.yunchengbao.activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/12/9.
 */

public class LocationService extends Service {
    LocationClient mlocationClient = null;
    public MyLocationListener mMyLocationListener = new MyLocationListener();
    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    private boolean isStop = false;

    @Override
    public void onCreate() {
        super.onCreate();
        mlocationClient = new LocationClient(getApplicationContext());
        mlocationClient.setLocOption(initLocationOption());
        mlocationClient.registerLocationListener(mMyLocationListener);
//        mlocationClient.start();
    }

    private LocationClientOption initLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(1000*60*60);
        option.setIsNeedAddress(true);
        return option;
    }

    private void requestLocation() {
        mlocationClient.start();
    }

    private void startTimer() {
        isStop = true;
        if (mTimer == null) {
            mTimer = new Timer();
        }
        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    do {
                        Log.d("tag", "isStop=" + isStop);
                        Log.d("tag", "mMyLocationListener=" + mMyLocationListener);
                        mlocationClient.start();
                        Log.d("tag", "mLocationClient.start()");
                        Log.d("tag", "mLocationClient==" + mlocationClient);
                        try {
                            Thread.sleep(1000 * 60*60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (isStop);
                }
            };
        }
        if (mTimer != null && mTimerTask != null) {
            Log.d("tag", "mTimer.schedule(mTimerTask, delay)");
            mTimer.schedule(mTimerTask, 0);//执行定时器中的任务
        }
    }

    /**
     * 停止定时器，初始化定时器开关
     */
    private void stopTimer() {

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
        isStop = false;//重新打开定时器开关
        Log.d("tag", "isStop="+isStop);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isStop) {
            Log.i("tag", "定时器启动");
            startTimer();
        }
        return super.onStartCommand(intent, flags, startId);
    }
//        @Override
//    protected void onHandleIntent(Intent intent) {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            List<String> permissionList = new ArrayList<String>();
//            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.
//                    permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
//            }
//            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.
//                    permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                permissionList.add(Manifest.permission.READ_PHONE_STATE);
//            }
//            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.
//                    permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            }
//            if (!permissionList.isEmpty()) {
//                String[] permissions = permissionList.toArray(new String[permissionList.size()]);
//                ActivityCompat.requestPermissions(null, permissions, 1);
//            }
//        } else {
//            requestLocation();
//        }
//
//    }

    @Override
    public void onDestroy() {
        if (mlocationClient!=null) {
            mlocationClient.stop();
        }
        super.onDestroy();
        if (isStop) {
            Log.i("tag", "定时器服务停止");
            stopTimer();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            Log.d(MainActivity.TAG, "onReceiveLocation: ");
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("维度：").append(bdLocation.getLatitude()).append("\n");
            currentPosition.append("经线：").append(bdLocation.getLongitude()).append("\n");
            currentPosition.append("国家：").append(bdLocation.getCountry()).append("\n");
            currentPosition.append("省：").append(bdLocation.getProvince()).append("\n");
            currentPosition.append("市：").append(bdLocation.getCity()).append("\n");
            currentPosition.append("区：").append(bdLocation.getDistrict()).append("\n");
            currentPosition.append("街道：").append(bdLocation.getStreet()).append("\n");
            currentPosition.append("定位方式：");
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                currentPosition.append("GPS");
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                currentPosition.append("网络");
            } else {
                currentPosition.append("其他:"+ bdLocation.getLocType());
            }
            Log.d(MainActivity.TAG, currentPosition.toString());
        }
    }
}
