package com.xstar97.easyutils.mods;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;

/**
 * The Easy file mod API.
 */
public class EasyFileMod extends BaseMod
{
    private String TAG = "EasyFileMod";

    /**
     * Instantiates a new Easy file mod.
     *
     *This is the basic and easy "builder" method that lets you use do stuff.
     *
     */
    public EasyFileMod() {
    }

    public static class utilsBuilder
    {
        private String TAG = "EasyFileMod.utilsBuilder";

        private String fileName = null;
        private String extension = null;
        private File file = null;
        private File outPut = null;


        public utilsBuilder setFile(File file){
            this.file = file;
            return this;
        }

        public utilsBuilder setTempFile(String fileName, String extension){
            this.fileName = fileName;
            this.extension = extension;
            return this;
        }

        public utilsBuilder setNewFilePath(File outputPath){
            this.outPut = outputPath;
            return this;
        }

        /**
         * Create temp file file.
         *
         * @return the file
         */
        public File createTempFile() {
            try {
                return File.createTempFile(fileName, extension, file);
            } catch (IOException e) {
                error(TAG, e.getMessage());
                return null;
            }
        }

        /**
         * Move file.
         */
        public final void moveFile() {

            File inputPath = file;
            File outputPath = outPut;

            InputStream in = null;
            OutputStream out = null;

            try {

                String fileName = new EasyFileMod.filesBuilder()
                        .setFile(inputPath)
                        .getFileName();

                new EasyFileMod.utilsBuilder()
                        .setFile(outputPath)
                        .mkDirs();

                in = new FileInputStream(inputPath);

                if(!outputPath.toString().endsWith("/"))
                    out = new FileOutputStream(outputPath + "/" + fileName);
                else
                    out = new FileOutputStream(outputPath + fileName);

                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
                in.close();
                in = null;

                out.flush();
                out.close();
                out = null;

                new EasyFileMod.utilsBuilder()
                        .setFile(inputPath)
                        .deleteOnExit();
            }
            catch (FileNotFoundException fnfe1) {
                error(TAG, fnfe1.getMessage());
            }
            catch (Exception e) {
                error(TAG, e.getMessage());
            }
        }

        /**
         * Delete file.
         *
         */
        public final void deleteOnExit(){
            try {
                boolean exist = file.exists();
                debug(TAG, "file exist: " + exist);
                if(exist)
                    debug(TAG, "is file: " + file.isFile() + "\n" + "is directory: " + file.isDirectory());
                file.deleteOnExit();
            }catch (SecurityException se){
                error(TAG, se.getMessage());
            }
        }

        /**
         * Delete file.
         *
         */
        public final boolean delete(){
            try {
                boolean exist = file.exists();
                debug(TAG, "file exist: " + exist);
                if(exist)
                    debug(TAG, "is file: " + file.isFile() + "\n" + "is directory: " + file.isDirectory());
                return file.delete();
            }catch (SecurityException se){
                error(TAG, se.getMessage());
                return false;
            }
        }

        /**
         * Delete file/dir by command.
         *
         */
        public final void deleteByCommand() {
            try {
                boolean exist = file.exists();
                debug(TAG, "file exist: " + exist);
                if(exist)
                    debug(TAG, "is file: " + file.isFile() + "\n" + "is directory: " + file.isDirectory());
                 new EasyShellMod.shellBuilder()
                         .setCommand(EasyShellMod.shellBuilder.deleteCommand + file.getAbsolutePath())
                         .executeRunTimeCommand();
            }catch (SecurityException se){
                error(TAG, se.getMessage());
            }
        }

        /**
         * Mkdir boolean.
         *
         * @return the boolean
         */
        public final boolean mkDir() {
            try {
                File dir = file;
                boolean exist = dir.exists();
                debug(TAG, "dir exist: " + exist);
                if(!exist)
                    debug(TAG, "is file: " + dir.isFile() + "\n" + "is directory: " + dir.isDirectory());
                return dir.mkdir();
            }catch (SecurityException se){
                error(TAG, se.getMessage());
                return false;
            }
        }

        /**
         * Mkdirs boolean.
         *
         * @return the boolean
         */
        public final boolean mkDirs() {
            try {
                File dirs = file;
                boolean exist = dirs.exists();
                debug(TAG, "dirs exist: " + exist);
                if(!exist)
                    debug(TAG, "is file: " + dirs.isFile() + "\n" + "is directory: " + dirs.isDirectory());
                return dirs.mkdirs();
            }catch (SecurityException se){
                error(TAG, se.getMessage());
                return false;
            }
        }
    }

    public static class filesBuilder
    {
        private String TAG = "EasyFileMod.filesBuilder";
        private File file = null;
        private Context context = null;

        public filesBuilder(Context context){
            this.context = context;
        }
        public filesBuilder(){
        }

        public filesBuilder setFile(File file){
            this.file = file;
            return this;
        }

        /**
         * Is external storage writable boolean.
         *
         * @return the boolean
         */
        public boolean isExternalStorageWritable() {
            try {
                String state = Environment.getExternalStorageState();
                boolean writable = Environment.MEDIA_MOUNTED.equals(state);
                debug(TAG, "isWritable: " + writable);
                return writable;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return false;
            }
        }

        /**
         * Is external storage readable boolean.
         *
         * @return the boolean
         */
        public boolean isExternalStorageReadable() {
            try {
                String state = Environment.getExternalStorageState();
                boolean readable = Environment.MEDIA_MOUNTED.equals(state) ||
                        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
                debug(TAG, "isReadable: " + readable);
                return readable;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return false;
            }
        }

        /**
         * Get cache dir file.
         *
         * @return the file
         */
        public File getCacheDir(){
            try {
                File dir = context.getCacheDir();
                debug(TAG, "cacheDir: " + dir);
                return dir;
            }catch (Exception e){
                error(TAG, e.getMessage());
                return null;
            }
        }

        /**
         * get File Content string.
         *
         * @return the string
         */
        public String getFileContent(){
            if(file.isFile()) {
                StringBuilder Builder = new StringBuilder();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    String line;

                    while ((line = br.readLine()) != null) {
                        Builder.append(line).append('\n');
                    }
                    br.close();
                } catch (IOException e) {
                    error(TAG, e.getMessage());
                }
                return Builder.toString();
            }
            return file.getAbsolutePath();
        }

        /**
         * Get file name string.
         *
         * @return the string
         */
        public String getFileName(){
            String path = file.getAbsolutePath();
            if(path.contains("/")) {
                return path.substring(path.lastIndexOf("/") + 1);
            }
            else {
                return file.getName();
            }
        }

        //utils
        private long getFileFolderSize() {
            long size = 0;
            File dir = file;
            if (dir.isDirectory()) {
                for (File file1 : dir.listFiles()) {
                    if (file1.isFile()) {
                        size += file1.length();
                    } else
                        size += getFileFolderSize();
                }
            } else if (dir.isFile()) {
                size += dir.length();
            }
            return size;
        }
        private String LongSize(long fileSize) {
            double sizeMB = (double) fileSize / 1024 / 1024;
            String s = " MB";
            if (sizeMB < 1) {
                sizeMB = (double) fileSize / 1024;
                s = " KB";
            }
            String datasize = NumberFormat.getInstance().format(sizeMB);
            String datasize2 = datasize.replaceAll("[^\\d.,]", "");
            if (datasize2.length() >= 4){
                datasize2 = datasize2.substring(0, 4) + "";
            }
            return datasize2 + s;
        }
    }
}