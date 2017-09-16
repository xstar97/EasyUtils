package com.xstar97.easyutils.mods;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * The Easy device mod API.
 */
public class EasyDeviceMod extends BaseMod
{
    private static String TAG = "EasyDeviceMod";

    /**
     * Instantiates a new Easy device mod.
     *
     *This is the basic and easy "builder" method that lets you use do stuff.
     */
    public EasyDeviceMod(){
    }

    public static class abiBuilder
    {
        private String TAG = ".abiBuilder";

        /**
         * The X86.
         *
         * This String method returns "x86";
         */
        public String X86 = "x86";

        /**
         * The Arm.
         *
         * This String method returns "armeabi-v7a";
         */
        public String ARM = "armeabi-v7a";

        /**
         * The Arm64.
         *
         * This String method returns "arm64-v8a";
         */
        public String ARM64 = "arm64-v8a";

        /**
         * Device supported abis string[].
         *
         * This String[] method returns Build.SUPPORTED_ABIS;
         *
         * @return the string[]
         */
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public String[] SUPPORTED_ABIS(){
            return (String[]) utilObject(Build.SUPPORTED_ABIS);
        }

        /**
         * Device supported 64 bit abis string.
         *
         * This String[] method returns Build.SUPPORTED_64_BIT_ABIS;
         *
         * @return the string[]
         */
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public String[] SUPPORTED_64_BIT_ABIS(){
            return (String[]) utilObject(Build.SUPPORTED_64_BIT_ABIS);
        }

        /**
         * Device supported 32 bit abis string[].
         *
         * This String[] method returns Build.SUPPORTED_32_BIT_ABIS;
         *
         * @return the string[]
         */
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public String[] SUPPORTED_32_BIT_ABIS(){
            return (String[]) utilObject(Build.SUPPORTED_32_BIT_ABIS);
        }

        /**
         * Device cpu abi 2 string.
         *
         * This String method returns Build.CPU_ABI2;
         *
         * @return the string
         */
        @SuppressWarnings("deprecation")
        public String CPU_ABI2(){
            return (String) utilObject(Build.CPU_ABI2);
        }

        /**
         * Device cpu abi string.
         *
         * This String method returns Build.CPU_ABI;
         *
         * @return the string
         */
        @SuppressWarnings("deprecation")
        public String CPU_ABI(){
            return (String) utilObject(Build.CPU_ABI);
        }
    }

    public static class versionBuilder
    {
        private String TAG = ".versionBuilder";

        /**
         * Device sdk int.
         *
         * This int method returns Build.VERSION.SDK_INT;
         *
         * @return the int
         */
        public int SDK_INT() {
            return (Integer) utilObject(Build.VERSION.SDK_INT);
        }

        /**
         * Device base OS string.
         *
         * This String method returns Build.VERSION.BASE_OS;
         *
         * @return the string
         */
        @TargetApi(Build.VERSION_CODES.M)
        public String BASE_OS() {
            return (String) utilObject(Build.VERSION.BASE_OS);
        }

        /**
         * Device sdk dep string.
         *
         * This String method returns Build.VERSION.SDK;
         *
         * @deprecated
         *
         * @return the string
         */
        public String SDK_DEP() {
            return (String) utilObject(Build.VERSION.SDK);
        }
    }

    public static class deviceBuilder
    {
        private String TAG = ".deviceBuilder";

        /**
         * Device manufacturer string.
         *
         * This String method returns Build.MANUFACTURER;
         *
         * @return the string
         */
        public String MANUFACTURER() {
            return (String) utilObject(Build.MANUFACTURER);
        }

        /**
         * Device model string.
         *
         * This String method returns Build.MODEL;
         *
         * @return the string
         */
        public String MODEL() {
            return (String) utilObject(Build.MODEL);
        }

        /**
         * Device board string.
         *
         * This String method returns Build.BOARD;
         *
         * @return the string
         */
        public String BOARD(){
            return (String) utilObject(Build.BOARD);
        }

        /**
         * Device bootloader string.
         *
         * This String method returns Build.BOOTLOADER;
         *
         * @return the string
         */
        public String BOOTLOADER(){
            return (String) utilObject(Build.BOOTLOADER);
        }

        /**
         * Device brand string.
         *
         * This String method returns Build.BRAND;
         *
         * @return the string
         */
        public  String BRAND(){
            return (String) utilObject(Build.BRAND);
        }

        /**
         * Device device string.
         *
         * This String method returns Build.DEVICE;
         *
         * @return the string
         */
        public String DEVICE(){
            return (String) utilObject(Build.DEVICE);
        }

        /**
         * Device display string.
         *
         * This String method returns Build.DISPLAY;
         *
         * @return the string
         */
        public String DISPLAY(){
            return (String) utilObject(Build.DISPLAY);
        }

        /**
         * Device fingerprint string.
         *
         * This String method returns Build.FINGERPRINT;
         *
         * @return the string
         */
        public String FINGERPRINT(){
            return (String) utilObject(Build.FINGERPRINT);
        }

        /**
         * Device host string.
         *
         * This String method returns Build.HOST;
         *
         * @return the string
         */
        public String HOST(){
            return (String) utilObject(Build.HOST);
        }

        /**
         * Device product string.
         *
         * This String method returns Build.PRODUCT;
         *
         * @return the string
         */
        public String PRODUCT(){
            return (String) utilObject(Build.PRODUCT);
        }

        /**
         * Device tags string.
         *
         * This String method returns Build.TAGS;
         *
         * @return the string
         */
        public String TAGS(){
            return (String) utilObject(Build.TAGS);
        }

        /**
         * Device type string.
         *
         * This String method returns Build.TYPE;
         *
         * @return the string
         */
        public String TYPE(){
            return (String) utilObject(Build.TYPE);
        }

        /**
         * Device user string.
         *
         * This String method returns Build.USER;
         *
         * @return the string
         */
        public String USER(){
            return (String) utilObject(Build.USER);
        }
    }

    public static class utilsBuilder
    {
        private String TAG = ".utilsBuilder";

        /**
         * Device radioversion string.
         *
         * This String method returns Build.getRadioVersion;
         *
         * @return the string
         */
        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        public String RADIO_VERSION(){
            return (String) utilObject(Build.getRadioVersion());
        }

        /**
         * Device radio dep string.
         *
         * This String method returns Build.RADIO;
         *
         * @return the string
         */
        public String RADIO_DEP(){
            return (String) utilObject(Build.RADIO);
        }

        /**
         * Device hardware string.
         *
         * This String method returns Build.HARDWARE;
         *
         * @return the string
         */
        public String HARDWARE(){
            return (String) utilObject(Build.HARDWARE);
        }

        /**
         * Device id string.
         *
         * This String method returns Build.ID;
         *
         * @return the string
         */
        public final String ID(){
            return (String) utilObject(Build.ID);
        }

        /**
         * Device time long.
         *
         * This Long method returns Build.TIME;
         *
         * @return the long
         */
        public long TIME(){
            return (Long) utilObject(Build.TIME);
        }
    }

    private static Object utilObject(Object object){
        try {
            if(object instanceof String){
                String data = (String) object;
                if(data != null && !data.isEmpty())
                    return object;
                else
                    return null;
            } else if(object instanceof Integer){
                Integer data = (Integer) object;
                if(data != null)
                    return object;
                else
                    return null;
            } else if(object instanceof String[]){
                String[] data = (String[]) object;
                if(data != null && data.length != 0)
                    return object;
                else
                    return null;
            } else if(object instanceof Long){
                Long data = (Long) object;
                if(data != null)
                    return object;
                else
                    return null;
            } else {
                return null;
            }
        }catch (Exception e){
            error(TAG, e.getMessage());
            return null;
        }
    }
}