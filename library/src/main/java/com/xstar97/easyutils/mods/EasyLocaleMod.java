package com.xstar97.easyutils.mods;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import com.xstar97.easyutils.interfaces.EasyLocaleModListener;

import java.util.Locale;

/**
 * The Easy locale mod API.
 */
public class EasyLocaleMod extends BaseMod
{
    private String TAG = "EasyLocaleMod";

    /**
     * Instantiates a new Easy locale mod.
     * <p>
     * This is the basic and easy "builder" method that lets you use do stuff.
     *
     */
    public EasyLocaleMod() {
    }

    public static class localeBuilder
    {
        private String TAG = "EasyLocaleMod.localeBuilder";

        private String SELECTED_LOCALE = "EasyLocaleMod.Selected.Locale";
        private Context context = null;
        private EasyLocaleModListener listener = null;

        private String localeCode = null;

        public localeBuilder(Context context){
            this.context = context;
        }

        public localeBuilder setListener(EasyLocaleModListener listener) {
            this.listener = listener;
            return this;
        }

        public localeBuilder setLocaleCode(String code){
            this.localeCode = code;
            return this;
        }

        /**
         * Sets default locale from prefs.
         */
        public void setDefaultLocale() {
            String lang = getPersistedData(Locale.getDefault().getLanguage());
            debug(TAG, "setDefaultLocale: " + lang);
            localeCode = lang;
            setLocale();
        }
        /**
         * Sets locale.
         *
         */
        public void setLocale() {
            debug(TAG, "setLocale: " + localeCode);
            persist(localeCode);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                updateResources(localeCode);
            else
                updateResourcesLegacy(localeCode);
        }

        /**
         * Gets language code.
         *
         * @return the language code
         */
        public final String getDeviceLanguageCode() {
            debug(TAG, "getDeviceLanguageCode");
            return Locale.getDefault().getLanguage();
        }

        /**
         * Gets language.
         *
         * @return the language
         */
        public final String getAppLanguageCode() {
            debug(TAG, "getAppLanguageCode");
            return getPersistedData(Locale.getDefault().getLanguage());
        }

        private String getPersistedData(String defaultLanguage) {
            debug(TAG, "getPersistedData: " + defaultLanguage);
           return (String) new EasyPrefsMod.prefsBuilder(context)
                    .setPreference()
                    .setKey(SELECTED_LOCALE)
                    .setValue(defaultLanguage)
                    .get();
        }

        private void persist(String language) {
            debug(TAG, "persist: " + language);
            new EasyPrefsMod.prefsBuilder(context)
                    .setPreference()
                    .setKey(SELECTED_LOCALE)
                    .setValue(language)
                    .put();
        }

        @TargetApi(Build.VERSION_CODES.N)
        private void updateResources(String language) {
            debug(TAG, "updateResources: " + language);
            try {
                Locale locale = new Locale(language);
                Locale.setDefault(locale);

                Configuration configuration = context.getResources().getConfiguration();
                configuration.setLocale(locale);

                addListener();
                reset();
            }catch (Exception e){
                error(TAG, e.getMessage());
            }
        }

        @SuppressWarnings("deprecation")
        private void updateResourcesLegacy(String language) {
            debug(TAG, "updateResourcesLegacy: " + language);
            try {
                Locale locale = new Locale(language);
                Locale.setDefault(locale);

                Resources resources = context.getResources();

                Configuration configuration = resources.getConfiguration();
                configuration.locale = locale;

                resources.updateConfiguration(configuration, resources.getDisplayMetrics());

                addListener();
                reset();
            }catch (Exception e){
                error(TAG, e.getMessage());
            }
        }

        private void addListener(){
            try {
                if (listener != null) {
                    listener.onLocaleChanged();
                }
            }catch (Exception e){
                error(TAG, e.getMessage());
            }
        }
        private void reset(){
            EasyActivityMod easyActivityMod = new EasyActivityMod(context);
            easyActivityMod.recreate();
        }
    }
}