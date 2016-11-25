package com.dxs.deviceid;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import java.util.UUID;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {
    private TextView tx_mac;
    private TextView tx_iemi;
    private TextView tx_sn;
    private TextView tx_deviceid;
    private TextView tx_androidid;
    private TextView tx_installionid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initData();
    }

    private void initUI() {
        tx_mac = (TextView) findViewById(R.id.tx_mac);
        tx_iemi = (TextView) findViewById(R.id.tx_iemi);
        tx_sn = (TextView) findViewById(R.id.tx_sn);
        tx_deviceid = (TextView) findViewById(R.id.tx_deviceid);
        tx_androidid = (TextView) findViewById(R.id.tx_androidid);
        tx_installionid = (TextView) findViewById(R.id.tx_installionid);
    }

    private void initData() {
        tx_mac.setText(getMacAddress(this));
        tx_iemi.setText(getIMEI(this));
        tx_sn.setText(getSN(this));
        tx_deviceid.setText(getDeviceId(this));
        tx_androidid.setText(getAndroidId(this));
        tx_installionid.setText(getUUID());
    }

    private String getMacAddress(Context context){
        //wifi mac地址
        StringBuilder deviceId=new StringBuilder("Mac:");
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String wifiMac = info.getMacAddress();
        if(!isEmpty(wifiMac)){
            deviceId.append(wifiMac);
        }
        Log.e("dxsTest","deviceId"+deviceId.toString());
        return deviceId.toString();
    }
    private String getIMEI(Context context){
        //wifi mac地址
        StringBuilder deviceId=new StringBuilder("IMEI:");
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        if(!isEmpty(imei)){
            deviceId.append(imei);
        }
        Log.e("dxsTest","deviceId"+deviceId.toString());
        return deviceId.toString();
    }
    private String getSN(Context context){
        //wifi mac地址
        StringBuilder deviceId=new StringBuilder("SN:");
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String sn = tm.getSimSerialNumber();
        if(!isEmpty(sn)){
            deviceId.append(sn);
        }
        Log.e("dxsTest","deviceId"+deviceId.toString());
        return deviceId.toString();
    }

    private String getDeviceId(Context context){
        //wifi mac地址
        StringBuilder deviceId=new StringBuilder("DeviceId:");
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String sn = tm.getDeviceId();
        if(!isEmpty(sn)){
            deviceId.append(sn);
        }
        Log.e("dxsTest","deviceId"+deviceId.toString());
        return deviceId.toString();
    }

    private String getAndroidId(Context context){
        //wifi mac地址
        StringBuilder deviceId=new StringBuilder("ANDROID_ID:");
        String ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        if(!isEmpty(ANDROID_ID)){
            deviceId.append(ANDROID_ID);
        }
        Log.e("dxsTest","deviceId"+deviceId.toString());
        return deviceId.toString();
    }

    private String getUUID(){
        return UUID.randomUUID().toString();
    }
}
