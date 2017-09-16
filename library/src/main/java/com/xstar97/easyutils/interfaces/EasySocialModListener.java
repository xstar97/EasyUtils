package com.xstar97.easyutils.interfaces;

public interface EasySocialModListener
{
    /**
     * On success.
     *
     * @param appID the app id
     */
    void onSuccess(String appID);

    /**
     * On fail.
     *
     * @param appID          the app id
     * @param isConnected    the is connected
     * @param isAppInstalled the is app installed
     */
    void onFail(String appID, boolean isConnected, boolean isAppInstalled);
}