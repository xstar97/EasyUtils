package com.xstar97.easyutils.mods;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

/**
 * The Easy app mod API.
 */
public class EasyAppMod extends BaseMod
{
    private String TAG = "EasyAppMod";

    /**
     * Instantiates a new Easy app mod.
     *
     * <p>
     * This is the basic and easy "builder" method that lets you use do stuff.
     *
     */
    public EasyAppMod(){
    }

    public static class appBuilder
    {
        private String TAG = "EasyAppMod.appBuilder";
        private Context context = null;
        private String STRING_NAME_NOT_FOUND_EXCEPTION = "Name Not Found Exception";
        private int INT_NAME_NOT_FOUND_EXCEPTION = 0;
        private String pkg = null;
        private Drawable fallbackDrawable = null;

        public appBuilder(Context context){
            this.context = context;
        }

        public appBuilder setPkgId(String pkg){
            this.pkg = pkg;
            return this;
        }
        public appBuilder setDrawable(Drawable fallback){
            this.fallbackDrawable = fallback;
            return this;
        }

        /**
         * Get app resources resources.
         *
         * <p>
         * This drawable method gets an apps icon
         *if the app does NOT exist returns the fallback drawable.
         *
         * @return the drawable
         */
        public Drawable getAppDrawable(){
            Drawable fallback = fallbackDrawable;
            try {
                Drawable res = myPackageManager().getApplicationIcon(pkg);
                debug(TAG, "pkg: " + pkg + "\n" + "res: " + res);
                return res;
            }catch (PackageManager.NameNotFoundException nnfe){
                debug(TAG, "pkg: " + pkg + "\n" + " fallback res: " + fallback);
                error(TAG, nnfe.getMessage());
                return fallback;
            }catch (NullPointerException npe){
                debug(TAG, "pkg: " + pkg + "\n" + " fallback res: " + fallback);
                error(TAG, npe.getMessage());
                return fallback;
            }
        }

        /**
         * Is app installed boolean.
         *
         * <p>
         * This boolean method checks if an app is installed or not...
         *
         * @return the boolean
         */
        public boolean isAppInstalled() {
            try {
                boolean isInstalled = myPackageInfo() != null;
                debug(TAG, "pkg: " + pkg + " is installed: " + isInstalled);
                return isInstalled;
            } catch (Exception e) {
                error(TAG, e.getMessage());
                return false;
            }
        }

        /**
         * Is app enabled boolean.
         *
         * <p>
         * This boolean method checks if an app is enabled or not...
         *
         * @return the boolean
         */
        public boolean isAppEnabled() {
            try {
                boolean isEnabled = myApplicationInfo().enabled;
                debug(TAG, "pkg: " + pkg + " is installed: " + isEnabled);
                return isEnabled;
            } catch (Exception e) {
                error(TAG, e.getMessage());
                return false;
            }
        }

        /**
         * App version name string.
         *
         * <p>
         * This String method returns an apps VersionName
         * if app does NOT exist returns "Name Not Found Exception";
         *
         * @return the string
         */
        public String appVersionName() {
            try {
                String versionName = myPackageInfo().versionName;
                debug(TAG, "pkg: " + pkg + " versionName: " + versionName);
                return versionName;
            } catch (Exception e) {
                error(TAG, e.getMessage());
                return STRING_NAME_NOT_FOUND_EXCEPTION;
            }
        }

        /**
         * App version code int.
         *
         * <p>
         * This int method returns an apps VersionCode
         * if app does NOT exist returns 0;
         *
         * @return the int
         */
        public int appVersionCode() {
            try {
                int versionCode = myPackageInfo().versionCode;
                debug(TAG, "pkg: " + pkg + " versionCode: " + versionCode);
                return versionCode;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return INT_NAME_NOT_FOUND_EXCEPTION;
            }
        }

        /**
         * App package name string.
         *
         * <p>
         * This String method returns an apps PackageName
         * if app does NOT exist returns ???????
         *
         * @return the string
         */
        public String appPackageName(){
            String pkg = context.getPackageName();
            debug(TAG, "pkg: " + pkg);
            return pkg;
        }

        //utils
        private PackageManager myPackageManager(){
            try {
                return context.getPackageManager();
            }catch (NullPointerException npe) {
                error(TAG, npe.getMessage());
                return null;
            } catch (Exception e) {
                error(TAG, e.getMessage());
                return null;
            }
        }
        private PackageInfo myPackageInfo(){
            try {
                return myPackageManager().getPackageInfo(pkg, 0);
            } catch (PackageManager.NameNotFoundException e) {
                error(TAG, e.getMessage());
                return null;
            } catch (NullPointerException npe) {
                error(TAG, npe.getMessage());
                return null;
            }
        }
        private ApplicationInfo myApplicationInfo(){
            try {
                return myPackageManager().getApplicationInfo(pkg, 0);
            } catch (PackageManager.NameNotFoundException e) {
                error(TAG, e.getMessage());
                return null;
            } catch (NullPointerException npe) {
                error(TAG, npe.getMessage());
                return null;
            }
        }
    }
}