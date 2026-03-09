package com.example.hotspot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.bluetooth.BluetoothDevice;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.Calendar;

public class HotspotReceiver extends BroadcastReceiver {
    
    private static final String TAG = "HotspotReceiver";
    private static final String TARGET_BLUETOOTH_MAC = "BC:82:5D:A4:D3:E1";
    private static final int START_HOUR = 8;
    private static final int END_HOUR = 20;
    private static final String HOTSPOT_SSID = "CarHotspot";
    private static final String HOTSPOT_PASSWORD = "12345678";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        
        if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (device != null) {
                String mac = device.getAddress();
                String name = device.getName();
                Log.i(TAG, "蓝牙连接: " + name + " [" + mac + "]");
                
                if (TARGET_BLUETOOTH_MAC.equals(mac)) {
                    if (isInTimeWindow()) {
                        Log.i(TAG, "在时间窗口内，尝试开启热点");
                        enableHotspot(context);
                    } else {
                        Log.i(TAG, "不在时间窗口内，跳过");
                    }
                }
            }
        }
    }
    
    private boolean isInTimeWindow() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        return hour >= START_HOUR && hour < END_HOUR;
    }
    
    private void enableHotspot(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (wifiManager == null) {
                Log.e(TAG, "无法获取WifiManager");
                return;
            }
            
            // 关闭Wi-Fi
            wifiManager.setWifiEnabled(false);
            
            // 创建热点配置
            try {
                Class<?> wifiConfigClass = Class.forName("android.net.wifi.WifiConfiguration");
                Object wifiConfig = wifiConfigClass.newInstance();
                
                // 设置SSID
                java.lang.reflect.Field ssidField = wifiConfigClass.getDeclaredField("SSID");
                ssidField.setAccessible(true);
                ssidField.set(wifiConfig, "\"" + HOTSPOT_SSID + "\"");
                
                // 设置密码
                java.lang.reflect.Field pskField = wifiConfigClass.getDeclaredField("preSharedKey");
                pskField.setAccessible(true);
                pskField.set(wifiConfig, "\"" + HOTSPOT_PASSWORD + "\"");
                
                // 配置安全类型
                java.lang.reflect.Field allowedKeyMgmtField = wifiConfigClass.getDeclaredField("allowedKeyManagement");
                allowedKeyMgmtField.setAccessible(true);
                Object keyMgmt = java.lang.reflect.Array.newInstance(boolean.class, 3);
                java.lang.reflect.Method set = keyMgmt.getClass().getMethod("set", int.class);
                // WPA_PSK 索引通常是1
                set.invoke(keyMgmt, 1);
                allowedKeyMgmtField.set(wifiConfig, keyMgmt);
                
                // 调用系统方法开启热点
                java.lang.reflect.Method setWifiApEnabled = wifiManager.getClass().getDeclaredMethod(
                    "setWifiApEnabled", wifiConfigClass, boolean.class);
                setWifiApEnabled.setAccessible(true);
                boolean result = (boolean) setWifiApEnabled.invoke(wifiManager, wifiConfig, true);
                
                Log.i(TAG, "热点开启结果: " + result);
                
            } catch (Exception e) {
                Log.e(TAG, "反射调用失败: " + e.getMessage());
            }
            
        } catch (Exception e) {
            Log.e(TAG, "开启热点异常: " + e.getMessage());
        }
    }
}