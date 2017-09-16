package com.xstar97.easyutils.mods;

import android.content.Context;
import android.os.AsyncTask;

import com.xstar97.easyutils.enums.AdMethods;
import com.xstar97.easyutils.interfaces.EasyAdsModAsyncTaskListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * The Easy ads mod API.
 */
public class EasyAdsMod extends BaseMod
{
    private static String TAG = "EasyAdsMod";
    private Context context;

    /**
     * Instantiates a new Easy ads mod.
     *
     * <p>
     * This is the basic and easy "builder" method that lets you use do stuff.
     */
    public EasyAdsMod(Context context) {
        this.context = context;
    }

    /**
     * Instantiates a new Easy ads mod.
     *
     * <p>
     * This is the basic and easy "builder" method that lets you use do stuff.
     */
    public EasyAdsMod() {
    }

    private static boolean hostMethod() {
        try {
            debug(TAG, "initiating host method...");
            boolean result = false;
            File filename = new File("/etc/hosts");
            InputStream is = new BufferedInputStream(new FileInputStream(filename));
            try {
                byte[] c = new byte[1024];
                int count = 0;
                int readChars = 0;
                boolean empty = true;
                while ((readChars = is.read(c)) != -1) {
                    empty = false;
                    for (int i = 0; i < readChars; ++i) {
                        if (c[i] == '\n') {
                            ++count;
                        }
                    }
                }
                int lines = (count == 0 && !empty) ? 1 : count;
                debug(TAG, "line count = " + lines);
                result = lines >+ 2;
                return result;
            } finally {
                is.close();
            }
        } catch (Exception e) {
            error(TAG, e.getMessage());
            return false;
        }
    }

    public static class AdsBlockedTask extends AsyncTask<Void, Void, Boolean> {

        private EasyAdsModAsyncTaskListener mListener;
        private AdMethods methods;

        public AdsBlockedTask() {
        }

        public AdsBlockedTask setListener(EasyAdsModAsyncTaskListener listener){
           this.mListener = listener;
            return this;
        }
        public AdsBlockedTask setMethod(AdMethods method){
            this.methods = method;
            return this;
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            boolean result = false;
            switch (methods) {
                case HostMethod:
                    result = hostMethod();
                    break;
                default:
                    break;
            }
            return result;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            if (mListener != null)
                mListener.areAdsBlocked(result);
        }
    }
}