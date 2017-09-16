package com.xstar97.easyutils.mods;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

/**
 * The Easy activity mod API.
 */
public class EasyActivityMod extends BaseMod
{
    private String TAG = "EasyActivityMod";
    private Context context;

    /**
     * Instantiates a new Easy activity mod.
     *
     * <p>
     * This is the basic and easy "builder" method that lets you use do stuff.
     *
     * @param context the context
     */
    public EasyActivityMod(Context context) {
        this.context = context;
    }

    /**
     * Restart app method.
     *
     * <p>
     * This method will restart your app safely.
     */
    public final void restartApp(){
        try {
            debug(TAG, "initiating...");
            Intent i = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            debug(TAG, "restarting app...");
            context.startActivity(i);
        }catch (NullPointerException npe){
            error(TAG, npe.getMessage());
        }
    }

    /**
     * Recreate method.
     *
     * <p>
     * this method does not kill/restart your app, but recreates the current activity instead.
     */
    public final void recreate() {
        try {
            debug(TAG, "initiating...");
            if (context instanceof Activity) {
                Activity activity = ((Activity) context);
                debug(TAG, "getting activity: " + activity);
                while (activity != null) {
                    debug(TAG, "recreating...");
                    activity.recreate();
                    activity = activity.getParent();
                }
            }
        }catch (Exception e){
            error(TAG, e.getMessage());
        }
    }

    public final void setFullScreen(){
        try {
        if(context instanceof Activity){
            Activity activity = (Activity) context;
            activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        }catch (RuntimeException rte){
            error(TAG, rte.getMessage());
        }
    }
}