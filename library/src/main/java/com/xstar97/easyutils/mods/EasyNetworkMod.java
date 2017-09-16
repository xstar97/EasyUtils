package com.xstar97.easyutils.mods;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * The Easy network mod API.
 */

@SuppressWarnings("MissingPermission")
public class EasyNetworkMod extends BaseMod
{
    private String TAG = "EasyNetworkMod";

    /**
     * Instantiates a new Easy network mod.
     *
     *This is the basic and easy "builder" method that lets you use do stuff.
     *
     */
    public EasyNetworkMod(){
    }

    public static class wifiBuilder
    {
        private String TAG = "EasyNetworkMod.networkBuilder";
        private Context context = null;

        private String ERROR_WIFI_NOT_ENABLED = "Error_WIFI_NOT_Enabled";
        private String ERROR_WIFI_NOT_SUPPORTED = "Error_WIFI_NOT_Supported";

        private String ERROR_CM_IS_NULL = "Error_ConnectivityManager_IS_NULL";
        private String ERROR_WM_IS_NULL = "Error_WIFIManager_IS_NULL";

        public wifiBuilder(Context context){
            this.context = context;
        }

        /**
         * Is connected boolean.
         *
         * @return the boolean
         */
        public boolean isConnected(){
            try {
                NetworkInfo netInfo = getActiveNetworkInfo();
                return netInfo != null && netInfo.isConnected();
            }catch (Exception e){
                error(TAG, e.getMessage());
                return false;
            }
        }

        /**
         * Is wifi enabled boolean.
         *
         * @return boolean boolean
         */
        public boolean isWifiEnabled(){
            boolean wifiState = false;
            try {
                WifiManager wifiManager = wifiManager();
                wifiState = wifiManager != null && wifiManager.isWifiEnabled();
                if (!wifiState) {
                    debug(TAG, ERROR_WIFI_NOT_ENABLED);
                }
                return wifiState;
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
                return wifiState;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return wifiState;
            }
        }

        private WifiManager wifiManager(){
            try {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if(wifiManager != null) {
                    return wifiManager;
                } else {
                    debug(TAG, ERROR_WM_IS_NULL);
                    return null;
                }
            }catch (Exception e){
                error(TAG, e.getMessage());
                return null;
            }
        }

        private NetworkInfo getActiveNetworkInfo(){
            try {
                ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                if(cm != null) {
                    return cm.getActiveNetworkInfo();
                } else {
                    debug(TAG, ERROR_CM_IS_NULL);
                    return null;
                }
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
                return null;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return null;
            }
        }
    }

    public static class bluBuilder
    {
        private String TAG = "EasyNetworkMod.networkBuilder";
        private Context context = null;

        private String ERROR_BLU_NOT_ENABLED = "Error_Blu_NOT_Enabled";
        private String ERROR_BLU_NOT_SUPPORTED = "Error_Blu_NOT_Supported";

        public bluBuilder(Context context){
            this.context = context;
        }

        /**
         * Is blu enabled boolean.
         *
         * @return the boolean
         */
        public boolean isBluEnabled(){
            boolean blu = false;
            try {
                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter != null) {
                    blu = mBluetoothAdapter.isEnabled();
                    if(!blu)
                        error(TAG, ERROR_BLU_NOT_ENABLED);

                    return blu;
                } else {
                        error(TAG, ERROR_BLU_NOT_SUPPORTED);
                    return blu;
                }
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
                return blu;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return blu;
            }
        }
    }

}