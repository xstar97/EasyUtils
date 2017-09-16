package com.xstar97.easyutils.mods;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import com.xstar97.easyutils.interfaces.EasyAudioModListener;

import java.io.File;
import java.io.IOException;

/**
 * The Easy audio mod API.
 */
public class EasyAudioMod extends BaseMod
{
    private String TAG = "EasyAudioMod";

    /**
     * Instantiates a new Easy audio mod.
     *
     *This is the basic and easy "builder" method that lets you use do stuff.
     *
     */
    public EasyAudioMod() {
    }

    public static class audioBuilder
    {
        private String TAG = "EasyAudioMod.audioBuilder";
        private Context context = null;
        private EasyAudioModListener listener = null;
        private static MediaPlayer m = null;

        private Object name = null;

        public audioBuilder(Context context){
            this.context = context;
        }

        /**
         * Sets listener.
         *
         * This method sets the listener for you to receive callbacks
         *
         * @param listener the listener
         * @return the listener
         */
        public audioBuilder setListener(EasyAudioModListener listener) {
            this.listener = listener;
            return this;
        }

        public audioBuilder setSound(Object name){
            this.name = name;
            return this;
        }

        public void play(){
            try{
                debug(TAG, "initiating...");
                stop();
                debug(TAG, "crafting new mediaPLayer...");
                m = new MediaPlayer();
                debug(TAG, "setting AudioStreamType...");
                m.setAudioStreamType(AudioManager.STREAM_ALARM);
                if(name instanceof String) {
                    String assetName = (String) name;
                    debug(TAG, "creating asset media...");
                    AssetFileDescriptor afd = context.getAssets().openFd(assetName);
                    debug(TAG, "setting data source...");
                    m.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    debug(TAG, "closing AssetFileDescriptor...");
                    afd.close();
                } else if(name instanceof Integer){
                    int rawName = (int) name;
                    debug(TAG, "creating raw media...");
                    m = MediaPlayer.create(context, rawName);
                } else if(name instanceof File){
                    File fileName = (File) name;
                    debug(TAG, "parsing file uri...");
                    Uri myUri = Uri.parse("file://" + fileName);
                    debug(TAG, "setting DataSource...");
                    m.setDataSource(context, myUri);
                }
                else{
                    throw new ClassCastException(TAG.getClass().getName() + " object not accepted for playing audio!");
                }
                debug(TAG, "MediaPlayer prepping...");
                m.prepare();
                debug(TAG, "MediaPlayer starting...");
                m.start();
                addListenerPlaying();
            }catch (ClassCastException cce){
                error(TAG, cce.getMessage());
            }catch (IOException io){
                error(TAG, io.getMessage());
            }catch (NullPointerException npe){
                error(TAG, npe.getMessage());
            }
        }
        public void stop(){
            try {
                debug(TAG, "stopping mediaPlayer: " + getMediaPlayer());
                if (getMediaPlayer() != null) {
                    if (getMediaPlayer().isPlaying()) {
                        getMediaPlayer().stop();
                        getMediaPlayer().release();
                        addListenerStop();
                    }
                }
            }catch (Exception e){
                error(TAG, e.getMessage());
            }
        }


        private MediaPlayer getMediaPlayer(){
            return m;
        }

        private void addListenerStop(){
            try {
                debug(TAG, "listener: is stopped");
                if (listener != null)
                    listener.isStopped();
            }catch (Exception e){
                error(TAG, e.getMessage());
            }
        }
        private void addListenerPlaying() {
            try {
                debug(TAG, "listener: is playing");
                if (listener != null)
                    listener.isPlaying();
            }catch (Exception e){
                error(TAG, e.getMessage());
            }
        }
    }
}