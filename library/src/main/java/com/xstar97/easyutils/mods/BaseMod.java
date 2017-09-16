package com.xstar97.easyutils.mods;

import com.xstar97.easyutils.BuildConfig;

class BaseMod
{
    private static String TAG = "BaseMod.";

    public static void debug(String tag, String d){
        //if(BuildConfig.DEBUG)
            new EasyLogMod.logBuilder()
                    .setTag(TAG + tag)
                    .setLog(d)
                    .debug();
    }
    public static void error(String tag, String e){
        new EasyLogMod.logBuilder()
                .setTag(TAG + tag)
                .setLog(e)
                .error();
    }

}