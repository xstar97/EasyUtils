package com.xstar97.easyutils.mods;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * The Easy permission mod API.
 */
public class EasyPermissionMod extends BaseMod
{
    private String TAG = "EasyPermissionMod";

    /**
     * Instantiates a new Easy permission mod.
     *
     *This is the basic and easy "builder" method that lets you use do stuff.
     *
     */
    public EasyPermissionMod() {
    }

    public static class permissionBuilder
    {
        private String TAG = "EasyPermissionMod.permissionBuilder";
        private Context context = null;
        private String permissionName = null;

        public permissionBuilder(Context context) {
            this.context = context;
        }

        public permissionBuilder setPermissionName(String permissionName) {
            this.permissionName = permissionName;
            return this;
        }

        public boolean isPermissionGranted() {
            try {
                boolean granted = context.checkCallingOrSelfPermission(permissionName) == PackageManager.PERMISSION_GRANTED;
                debug(TAG, "permission '" + permissionName + "': " + granted);
                return granted;
            } catch (Exception e) {
                error(TAG, e.getMessage());
                return false;
            }
        }

    }
}