package com.xstar97.easyutils.mods;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;
import java.util.Set;

/**
 * The Easy prefs mod API.
 */
public class EasyPrefsMod extends BaseMod
{
    private String TAG = "EasyPrefsMod";

    /**
     * Instantiates a new Easy prefs mod.
     *
     * <p>
     * This is the basic and easy "builder" method that lets you use do stuff.
     *
     */
    public EasyPrefsMod(){
    }

    public static class prefsBuilder
    {
        private String TAG = "EasyPrefsMod.prefsBuilder";

        private Context context = null;
        private SharedPreferences getPrefs = null;
        private SharedPreferences.Editor getEditor = null;

        /**
         * The Mode private method.
         *
         * <p>
         * File creation mode: the default mode, where the created file can only be accessed by the calling application.
         */
        public static int MODE_PRIVATE = 0;

        /**
         * The Mode world readable method.
         *
         * <p>
         * Creating world-readable files is very dangerous, and likely to cause security holes in applications.
         *
         * @deprecated
         * This constant was deprecated in API level 17.
         */
        public static int MODE_WORLD_READABLE = 1;

        /**
         * The Mode world writeable method.
         *
         * <p>
         * Creating world-writable files is very dangerous, and likely to cause security holes in applications.
         *
         * @deprecated
         * This constant was deprecated in API level 17.
         */
        public static int MODE_WORLD_WRITEABLE = 2;

        /**
         * The Mode multi process method.
         *
         * <p>
         * This method will check for modification of preferences even if the sharedpreference instance has already been loaded
         */
        public static int MODE_MULTI_PROCESS = 4;

        /**
         * The Mode append method.
         *
         * <p>
         * This will append the new preferences with the already exisiting preferences
         */
        public static int MODE_APPEND = 32768;

        /**
         * The Mode enable write ahead logging method.
         *
         * <p>
         * Database open flag. When it is set , it would enable write ahead logging by default
         */
        public static int MODE_ENABLE_WRITE_AHEAD_LOGGING = 8;

        private String prefName = null;
        private int mode = -1;
        private String key = null;
        private Object value = null;

        private String ERROR_PREFERENCE = "Set SharedPreferences method correctly!";
        private String ERROR_PREFERENCE_NAME = "Set SharedPreferences method 'NAME' correctly!";
        private String ERROR_PREFERENCE_MODE = "Set SharedPreferences method 'MODE' correctly!";
        private String ERROR_PREFERENCE_KEY = "Set SharedPreferences method 'KEY' correctly!";
        private String ERROR_PREFERENCE_OBJECT = "Object not accepted for sharedPreferences";

        public prefsBuilder(Context context){
            this.context = context;
        }

        public prefsBuilder setPreferenceName(String name){
            debug(TAG, "set pref name: " + name);
            this.prefName = name;
            return this;
        }
        public prefsBuilder setMode(int mode){
            debug(TAG, "set pref mode: " + mode);
            this.mode = mode;
            return this;
        }

        public prefsBuilder setPreference(){
            boolean mPref = prefName == null || prefName.isEmpty();
            boolean mMode = mode == -1;
            if(mPref && mMode) {
                debug(TAG, "DefaultSharedPreferences");
                this.getPrefs = PreferenceManager.getDefaultSharedPreferences(context);
            }else {
                debug(TAG, "Custom SharedPreferences: " + "\n" + "name: " + getPrefName() + "\n" + "mode: " + getMode());
                this.getPrefs = context.getSharedPreferences(getPrefName(), getMode());
            }
            return this;
        }

        public prefsBuilder setKey(String key){
            this.key = key;
            return this;
        }
        public prefsBuilder setValue(Object value){
            this.value = value;
            return this;
        }

        /**
         * Get object method.
         *
         * @return the object
         *
         * <p><ul>
         * <li> String
         * <li> Boolean
         * <li> Set<String>
         * <li> Integer
         * <li> Float
         * <li> Long
         * <li> Object
         * </ul><p>
         *
         */
        public Object get() {
            try {
                SharedPreferences prefs = getPrefs();
                String getKey = getKey();
                Object defaultValue = getValue();

                if (defaultValue instanceof String) {
                    String value = prefs.getString(getKey, (String) defaultValue);
                    prefLog("getting", value, "from", getKey);
                    return value;
                } else if (defaultValue instanceof Boolean) {
                    boolean value = prefs.getBoolean(getKey, (Boolean) defaultValue);
                    prefLog("getting", value, "from", getKey);
                    return value;
                } else if (defaultValue instanceof Set<?>) {
                    Set<String> value = prefs.getStringSet(getKey, (Set<String>) defaultValue);
                    prefLog("getting", value, "from", getKey);
                    return value;
                } else if (defaultValue instanceof Integer) {
                    int value = prefs.getInt(getKey, (Integer) defaultValue);
                    prefLog("getting", value, "from", getKey);
                    return value;
                } else if (defaultValue instanceof Float) {
                    Float value = prefs.getFloat(getKey, (Float) defaultValue);
                    prefLog("getting", value, "from", getKey);
                    return value;
                } else if (defaultValue instanceof Long) {
                    Long value = prefs.getLong(getKey, (Long) defaultValue);
                    prefLog("getting", value, "from", getKey);
                    return value;
                } else {
                    throw new ClassCastException(TAG.getClass().getName() + ": " + ERROR_PREFERENCE_OBJECT);
                }

            }catch (ClassCastException cce){
                error(TAG, cce.getMessage());
                return value;
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
                return value;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return value;
            }
        }

        /**
         * Gets all.
         *
         * <p>
         * This Map<String, ?> method will return ALL values from sharedPreferences
         *
         * @return all
         */
        public Map<String,?> getAll() {
            try {
                SharedPreferences prefs = getPrefs();
                prefLog("getting", "all", "from", "getAll");
                return prefs.getAll();
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
                return null;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return null;
            }
        }

        /**
         * Put method.
         *
         * <p>
         * This method saves the generic value in your SharePreferences.
         *
         * <p><ul>
         * <li> String
         * <li> Boolean
         * <li> Set<String>
         * <li> Integer
         * <li> Float
         * <li> Long
         * <li> Object
         * </ul><p>
         *
         */
        public void put() {
            try {
                SharedPreferences.Editor editor = getEditor();
                Object putValue = getValue();
                String putKey = getKey();

                if (putValue instanceof String) {
                    editor.putString(putKey, (String) putValue);
                    editor.apply();
                } else if (putValue instanceof Boolean) {
                    editor.putBoolean(putKey, (boolean) putValue);
                    editor.apply();
                } else if (putValue instanceof Set<?>) {
                    editor.putStringSet(putKey, (Set<String>) putValue);
                    editor.apply();
                } else if (putValue instanceof Integer) {
                    editor.putInt(putKey, (Integer) putValue);
                    editor.apply();
                } else if (putValue instanceof Float) {
                    editor.putFloat(putKey, (Float) putValue);
                    editor.apply();
                } else if (putValue instanceof Long) {
                    editor.putLong(putKey, (Long) putValue);
                    editor.apply();
                } else {
                    throw new ClassCastException(TAG.getClass().getName() + ": " + ERROR_PREFERENCE_OBJECT);
                }
                prefLog("saving", putValue, "to", putKey);
            }catch (ClassCastException cce){
                error(TAG, cce.getMessage());
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
            }catch (Exception e){
                error(TAG, e.getMessage());
            }
        }

        /**
         * Clear value method.
         *
         * <p>
         * This method clears the specific value by key from SharedPreferences.
         *
         */
        public void clearValue(){
            try {
                String getKey = getKey();
                debug(TAG, "removing value: " + getKey);
                SharedPreferences.Editor editor = getEditor();
                    editor.remove(getKey);
                    editor.apply();
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
            }catch (Exception e){
                error(TAG, e.getMessage());
            }
        }

        /**
         * Clear all method.
         *
         * <p>
         * This method clears ALL values from SharedPreferences.
         */
        public void clearAll(){
            try {
                if(getPrefs == null){
                    debug(TAG, ERROR_PREFERENCE);
                    throwException(ERROR_PREFERENCE);
                }
                debug(TAG, "resetting SharePreferences");
                getEditor = getPrefs.edit();
                SharedPreferences.Editor editor = getEditor;
                editor.clear();
                editor.apply();
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
            }catch (Exception e){
                error(TAG, e.getMessage());
            }
        }

        private String getPrefName() throws NullPointerException{
            try {
                if(prefName == null || prefName.isEmpty())
                    throwException(ERROR_PREFERENCE_NAME);
                else
                    return prefName;
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
                return prefName;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return prefName;
            }
            return prefName;
        }
        private int getMode() throws NullPointerException{
            try {
                if(mode == -1)
                    throwException(ERROR_PREFERENCE_MODE);
                else
                    return mode;
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
                return mode;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return mode;
            }
            return mode;
        }

        private SharedPreferences getPrefs() throws NullPointerException{
            try {
                if(getPrefs == null)
                    throwException(ERROR_PREFERENCE);
                else
                    return getPrefs;
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
                return getPrefs;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return getPrefs;
            }
            return getPrefs;
        }
        private SharedPreferences.Editor getEditor() throws NullPointerException{
            try {
                if(getPrefs == null)
                    throwException(ERROR_PREFERENCE);
                else
                    return getEditor = getPrefs.edit();
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
                return getEditor;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return getEditor;
            }
            return getEditor;
        }

        private String getKey() throws NullPointerException{
            try {
                if(key == null || key.isEmpty())
                    throwException(ERROR_PREFERENCE_KEY);
                else
                    return key;
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
                return key;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return key;
            }
            return key;
        }
        private Object getValue() throws NullPointerException{
            try {
                if(value == null)
                    throwException(ERROR_PREFERENCE_OBJECT);
                else
                    return value;
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
                return value;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return value;
            }
            return value;
        }

        /**
         * pref Log method.
         *
         * <p>
         * This method logs values saved and get!
         */
        private void prefLog(String getSave, Object value, String toFrom, String key){
            debug(TAG, getSave + " value " + "'" + value + "' " + toFrom  + " key " + "'" + key + "'");
        }

        private void throwException(String msg){
            throw new NullPointerException(TAG.getClass().getName() + ": " + msg);
        }
    }
}