package com.xstar97.easyutils.mods;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.xstar97.easyutils.interfaces.EasySocialModListener;

public class EasySocialMod extends BaseMod
{
    private String TAG = "EasySocialAppMod";

    public static class socialBuilder
    {
        private String TAG = "EasySocialAppMod.SocialBuilder";
        private Context context = null;
        private EasySocialModListener easySocialModListener = null;

        private String pkg = null;
        private String uri = null;

        private String FB_MESSENGER_URL = "http://m.me/";
        private String LATEST_FB_PAGE_URL = "fb://facewebmodal/f?href=https://www.facebook.com/";
        private String LEGACY_FB_PAGE_URL = "fb://page/";
        private String LATEST_FB_PROFILE_URL = "fb://facewebmodal/f?href=https://www.facebook.com/profile.php?id=";
        private String LEGACY_FB_PROFILE_URL = "https://www.facebook.com/";
        private String FB_VERSION = "11.0.0.11.23";
        private String TWITTER_URL = "twitter://user?screen_name=";
        private String GOOGLE_PLUS_COMMUNITY_URL = "https://plus.google.com/communities/";
        private String GOOGLE_PLUS_PROFILE_URL = "https://plus.google.com/";
        private String YOUTUBE_VIDEO_URL = "vnd.youtube:";
        private String YOUTUBE_CHANNEL_URL = "https://www.youtube.com/channel/";
        private String YOUTUBE_USER_URL = "https://www.youtube.com/user/";

        public static String FACEBOOK_APP = "com.facebook.katana";
        public static String FACEBOOK_MESSENGER_APP = "com.facebook.orca";
        public static String TWITTER_APP = "com.twitter.android";
        public static String GOOGLE_PLUS_APP = "com.google.android.apps.plus";
        public static String YOUTUBE_APP = "com.google.android.youtube";

        /**
         * Instantiates a new Easy social mod.
         * <p>
         ** This is the basic and easy "builder" method that lets you use do stuff.
         *
         * @param context the context
         **/
        public socialBuilder(Context context) {
        this.context = context;
    }

        public socialBuilder setPKG(String pkg){
            this.pkg = pkg;
            return this;
        }
        public socialBuilder setUri(String uri){
            this.uri = uri;
            return this;
        }

        /**
          *Sets listener.
         *
         * @param easySocialModListener the listener
         * @return the listener
         **/
        public socialBuilder setListener(EasySocialModListener easySocialModListener) {
        this.easySocialModListener = easySocialModListener;
        return this;
    }

        /**
         * Open facebook page.
         *
         */
        //social app
        public void openFacebookPage(){
            pkg = FACEBOOK_APP;
            String base = fbURI(LEGACY_FB_PAGE_URL, LATEST_FB_PAGE_URL);
            String id = getUri();
            uri = base + id;
            openSocialApp();
        }

        /**
         * Open facebook profile.
         *
         */
        public void openFacebookProfile(){
            pkg = FACEBOOK_APP;
            String base = fbURI(LEGACY_FB_PROFILE_URL, LATEST_FB_PROFILE_URL);
            String id = getUri();
            uri = base + id;
            openSocialApp();
        }

        public void openFacebookMessengerUserChat(){
            pkg = FACEBOOK_MESSENGER_APP;
            String base = FB_MESSENGER_URL;
            String id = getUri();
            uri = base + id;
            openSocialApp();
        }

        /**
         * Open twitter profile.
         *
         */
        public void openTwitterProfile(){
            pkg = TWITTER_APP;
            String base = TWITTER_URL;
            String id = getUri();
            uri = base + id;
            openSocialApp();
        }

        /**
         * Open google plus profile.
         *
         */
        public void openGooglePlusProfile(){
            pkg = GOOGLE_PLUS_APP;
            String base = GOOGLE_PLUS_PROFILE_URL;
            String id = getUri();
            uri = base + id;
            openSocialApp();
        }

        /**
         * Open google plus community.
         *
         */
        public void openGooglePlusCommunity(){
            pkg = GOOGLE_PLUS_APP;
            String base = GOOGLE_PLUS_COMMUNITY_URL;
            String id = getUri();
            uri = base + id;
            openSocialApp();
        }

        /**
         * Open you tube profile.
         *
         */
        public void openYouTubeProfile(){
            pkg = YOUTUBE_APP;
            String base = YOUTUBE_USER_URL;
            String id = getUri();
            uri = base + id;
            openSocialApp();
        }

        /**
         * Open youtube video.
         *
         */
        public void openYouTubeVideo(){
            pkg = YOUTUBE_APP;
            String base = YOUTUBE_VIDEO_URL;
            String id = getUri();
            uri = base + id;
            openSocialApp();
        }

        /**
         * Open you tube channel.
         *
         */
        public void openYouTubeChannel(){
            pkg = YOUTUBE_APP;
            String base = YOUTUBE_CHANNEL_URL;
            String id = getUri();
            uri = base + id;
            openSocialApp();
        }

        public void openSocialApp(){
        debug(TAG, "AppID: " + pkg + "\n" + "url: " + uri);
        boolean isLauncheable  = isNetWorkAvailable() && isAppInstalled();
                if(isLauncheable)
                 launchApp();
                else
                addFailListener();
        }

        /**
     * Rate app.
     *
     */
        public void rateApp() {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + pkg)));
        } catch (android.content.ActivityNotFoundException anfe) {
            error(TAG, "ActivityNotFoundException: " + anfe);
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + pkg)));
        }
    }

        private void launchApp(){
        try {
            debug(TAG, "initiating intent...");
            final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setPackage(pkg);
            context.startActivity(intent);
            debug(TAG, "Launched Activity...");
            addSuccessListener();
        }catch (Exception e){
            error(TAG, e.getMessage());
            addFailListener();
        }
    }

        private String getPkg(){
            return pkg;
        }
        private String getUri(){
            return uri;
        }

        //fb uri util
        private String fbURI(String legacy, String latest){
        String version = appVersionName();
        String version2 = version.replaceAll("[.]", "");
        String fb = FB_VERSION.replaceAll("[.]", "");//11.0.0.11.23 to 11001123
        try {
            if (stringToInt(version2) >= stringToInt(fb))
            {
                return latest;
            } else {
                return legacy;
            }
        } catch (NumberFormatException nfe) {
            error(TAG, nfe.getMessage());
            return null;
        }
    }

        //app utils
        private boolean isAppInstalled(){
        return new EasyAppMod.appBuilder(context)
                .setPkgId(pkg)
                .isAppInstalled();
    }
        private String pkgName(){
        return new EasyAppMod.appBuilder(context)
                .appPackageName();
    }
        private String appVersionName(){
        return new EasyAppMod.appBuilder(context)
                .setPkgId(pkg)
                .appVersionName();
    }

        private boolean isNetWorkAvailable(){
        return new EasyNetworkMod.wifiBuilder(context)
                .isConnected();
    }

        //string util
        private int stringToInt(String number){
        return (Integer) new EasyParseMod.parseBuilder()
                .setValue(number)
                .to(1)
                .parse();
    }

        //listener methods
       private void addSuccessListener(){
        try {
            debug(TAG, "onSuccess!");
            if (easySocialModListener != null) {
                easySocialModListener.onSuccess(pkg);
            }
        }catch (Exception e){
            error(TAG, e.getMessage());
        }
    }
       private void addFailListener(){
        try {
            debug(TAG, "onfail: " + "\n" +
                    "isConnected:" + isNetWorkAvailable() + "\n" +
                    "isAppInstalled: " + isAppInstalled());

            if (easySocialModListener != null)
                easySocialModListener.onFail(pkg, isNetWorkAvailable(), isAppInstalled());
        }catch (Exception e){
            error(TAG, e.getMessage());
        }
    }
    }
}