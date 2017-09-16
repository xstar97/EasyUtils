package com.xstar97.easyutils.mods;

import android.util.Log;

/**
 * The Easy log mod API.
 *
 */
public class EasyLogMod
{
    private static String TAG = "EasyLogMod";

    public EasyLogMod(){
    }

    public static class logBuilder
    {
        private String tag = null;
        private String log = null;

        public logBuilder setTag(String tag){
            this.tag = tag;
            return this;
        }

        public logBuilder setLog(String log){
            this.log = log;
            return this;
        }

        /**
         * Verbose.
         *
         */
        public void verbose() {
            try {
                Log.v(tag, getFormattedLogLine() + log);
            }catch(OutOfMemoryError error) {
                Log.e(TAG, "verbose OutOfMemoryError: " + error);
            }
        }

        /**
         * Debug.
         *
         */
        public void debug() {
            try {
                Log.d(tag, getFormattedLogLine() + log);
            }catch(OutOfMemoryError error) {
                Log.e(TAG, "debug OutOfMemoryError: " + error);
            }
        }

        /**
         * Info.
         *
         */
        public void info() {
            try {
                Log.i(tag, getFormattedLogLine() + log);
            }catch(OutOfMemoryError error) {
                Log.e(TAG, "info OutOfMemoryError: " + error);
            }
        }

        /**
         * Warn.
         *
         */
        public void warn() {
            try {
                Log.w(tag, getFormattedLogLine() + log);
            }catch(OutOfMemoryError error) {
                Log.e(TAG, "warn OutOfMemoryError: " + error);
            }
        }

        /**
         * Error.
         *
         */
        public void error() {
            try {
                Log.e(tag, getFormattedLogLine() + log);
            }catch(OutOfMemoryError error) {
                Log.e(TAG, "error OutOfMemoryError: " + error);
            }
        }

        private String getFormattedLogLine() {
            String className = getCallerClassName (5);
            String methodName = getCallerMethodName (5);
            int lineNumber = getCallerLineNumber (5);
            return className + "." + methodName + "()" + lineNumber + ":";
        }

        private String getCallerClassName(int stackTracePosition) {
            String fullClassName = Thread.currentThread().getStackTrace()[stackTracePosition].getClassName();
            return fullClassName.substring(fullClassName.lastIndexOf(".") +  1);
        }

        private String getCallerMethodName(int stackTracePosition) {
            return Thread.currentThread().getStackTrace()[stackTracePosition].getMethodName();
        }

        private int getCallerLineNumber(int stackTracePosition) {
            return Thread.currentThread().getStackTrace()[stackTracePosition].getLineNumber();
        }
    }

}