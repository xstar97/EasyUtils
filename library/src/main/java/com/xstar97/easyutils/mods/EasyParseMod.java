package com.xstar97.easyutils.mods;

import java.util.Arrays;

import static java.lang.Long.parseLong;

/**
 * The Easy parse mod API.
 */
public class EasyParseMod extends BaseMod
{
    private final String TAG = "EasyParseMod";

    /**
     * Instantiates a new Easy parse mod.
     *
     *This is the basic and easy "builder" method that lets you use do stuff.
     */
    public EasyParseMod(){
    }

    public static class parseBuilder
    {
        private String TAG = "EasyParseMod.parseBuilder";

        private  Object value = null;
        private  Object returnObject = null;

        public parseBuilder setValue(Object value){
            this.value = value;
            return this;
        }

        public parseBuilder to(Object toObjs){
            this.returnObject = toObjs;
            return this;
        }

        public Object parse(){
            return this.parse(value, returnObject);
        }

        private Object parse(Object from, Object to){
            try {
            if(from instanceof String && to instanceof Integer){
                String string = (String) value;
                return Integer.parseInt(string);
            } else if(from instanceof String && to instanceof Long){
                String string = (String) value;
                return parseLong(string);
            } else if(from instanceof String && to instanceof Float){
                String string = (String) value;
                return Float.parseFloat(string);
            } else if(from instanceof String && to instanceof String[]){
                String string = (String) value;
                return new String[] {string};
            }  else if(from instanceof String[] && to instanceof String){
                String[] array = (String[]) value;
                return Arrays.toString(array);
            } else if(from instanceof Integer && to instanceof String){
                Integer integer = (Integer) value;
                return Integer.toString(integer);
            } else if(from instanceof Integer && to instanceof Long){
                Integer integer = (Integer) value;
                return parseLong(Integer.toString(integer));
            } else if(from instanceof Long && to instanceof String){
                Long lng = (Long) value;
                return Long.toString(lng);
            } else if(from instanceof Long && to instanceof Integer){
                Long  l = (Long) value;
                return l.intValue();
            } else if(from instanceof Float && to instanceof String){
                Float fl = (Float) value;
                return Float.toString(fl);
            }
            }catch (NumberFormatException e){
                error(TAG, e.getMessage());
                   return returnObject;
            }catch (Exception e){
                error(TAG, e.getMessage());
                    return returnObject;
            }
                return returnObject;
        }
    }

    public static class stringUtils
    {
        private String TAG = "EasyParseMod.stringBuilder";
        private String EMPTY = "";
        private String NULL = "null";
        private String string = null;
        private String indexOfCHar = null;
        private String indexOfString = null;
        private int indexOf = -1;

        public stringUtils setString(String string){
            this.string = string;
            return this;
        }
        public stringUtils setIndexOfChar(String indexOfCHar){
            this.indexOfCHar = indexOfCHar;
            return this;
        }
        public stringUtils setIndexOfString(String indexOfString){
            this.indexOfString = indexOfString;
            return this;
        }
        public stringUtils setIndexOf(int indexOf){
            this.indexOf = indexOf;
            return this;
        }

        /**
         * Is empty boolean.
         *
         * @return the boolean
         */
        public boolean isEmpty() {
            return null == string || EMPTY.equals(string.trim());
        }

        /**
         * Contains digit boolean.
         *
         * @return the boolean
         */
        public boolean containsDigit() {
            boolean containsDigit = false;
            try {
                if (string != null && !string.isEmpty()) {
                    for (char c : string.toCharArray()) {
                        if (containsDigit = Character.isDigit(c)) {
                            break;
                        }
                    }
                }
            }catch (Exception e){
                error(TAG, e.getMessage());
            }
            return containsDigit;
        }

        /**
         * Last index of char string.
         *
         * @return the string
         */
        public String lastIndexOfChar(){
            if (string != null && string.length() > 0 )
            {
                return string.substring(string.lastIndexOf(indexOfCHar) + 1);
            }
            return NULL;
        }

        /**
         * Last index of string string.
         *
         * @return the string
         */
        public String lastIndexOfString(){
            if (string != null && string.length() > 0 )
            {
                return string.substring(string.indexOf(indexOfString) + indexOfString.length());
            }
            return NULL;
        }

        /**
         * Last index of string.
         *
         * @return the string
         */
        public String lastIndexOf(){
            if (string != null && string.length() > 0 )
            {
                return string.substring(string.lastIndexOf(indexOf) + 1);
            }
            return NULL;
        }
    }

}