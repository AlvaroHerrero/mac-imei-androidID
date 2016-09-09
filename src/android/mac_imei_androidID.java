package com.alvaroherrero;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ActivityManager;
import android.telephony.TelephonyManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.provider.Settings.Secure;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import android.util.Log;

public class mac_imei_androidID extends CordovaPlugin {

	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getImei")) {
            this.DeviceImeiNumber(callbackContext);
            return true;
        }
		if (action.equals("getMac")) {
            this.DeviceMacNumber(callbackContext);
            return true;
        }
        if (action.equals("getAndroidID")) {
            this.DeviceAndroidID(callbackContext);
             return true;
        }
        return false;
    }
	
    private static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0") && !nif.getName().equalsIgnoreCase("etho0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

    public void DeviceImeiNumber(CallbackContext callbackContext) {
        Context context = this.cordova.getActivity().getApplicationContext();
        TelephonyManager tManager = (TelephonyManager)cordova.getActivity().getSystemService(context.TELEPHONY_SERVICE);
        callbackContext.success(tManager.getDeviceId());
    }
	
	public void DeviceMacNumber(CallbackContext callbackContext) {
	    Context context = this.cordova.getActivity().getApplicationContext();
        WifiManager wifiMan = (WifiManager) cordova.getActivity().getSystemService(context.WIFI_SERVICE);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();
        String macAddr = mac_imei_androidID.getMacAddr();
        callbackContext.success(macAddr);
    }

    public void DeviceAndroidID(CallbackContext callbackContext) {
        String android_id = Secure.getString(this.cordova.getActivity().getContentResolver(), Secure.ANDROID_ID);
        callbackContext.success(android_id);
    }

    private void getImei(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

	private void getMac(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void getAndroidID(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

} 
