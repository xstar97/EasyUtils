package com.xstar97.easyutils.mods;

import java.io.IOException;

/**
 * The Easy shell mod API.
 */
public class EasyShellMod extends BaseMod
{
    private String TAG = "EasyShellMod";

    /**
     * Instantiates a new Easy shell mod.
     *
     *This is the basic and easy "builder" method that lets you use do stuff.
     */
    public EasyShellMod(){
    }

    public static class shellBuilder
    {
        private String TAG = "EasyShellMod.shellBuilder";
        private String command = null;

        /**
         * The Reboot command.
         */
        public static String rebootCommand =  "su -c reboot now";

        /**
         * The Delete command.
         */
        public static String deleteCommand = "rm -r ";

        public shellBuilder setCommand(String command){
            this.command = command;
            return this;
        }

        /**
         * Execute process command.
         *
         */
        public void executeProcessCommand() {
            debug(TAG, "command: " + command);
            debug(TAG, "initiating process...");
            Process process = null;
            try
            {
                debug(TAG, "starting executing command: " + command);
                process = Runtime.getRuntime().exec(command);
                debug(TAG, "command executed!");
            }
            catch (IOException e)
            {// Handle I/O exception.
                error(TAG, e.getMessage());
            }// We waitFor only if we've got the process.
            if (process != null) {
                try {
                    debug(TAG, "waiting for process...");
                    debug(TAG, "process: " + process.waitFor());
                    process.waitFor();
                    debug(TAG, "processed: " + process.waitFor());
                } catch (InterruptedException e) {
                    // Now handle this exception.
                    error(TAG, e.getMessage());
                }
            }
        }

        /**
         * Execute run time command.
         */
        public void executeRunTimeCommand(){
            debug(TAG, "command: " + command);
            Runtime runtime = Runtime.getRuntime();
            debug(TAG, "availableProcessors: " + runtime.availableProcessors());
            try {
                debug(TAG, "runtime: executing...");
                runtime.exec(command);
            }catch (IOException io){
                error(TAG, io.getMessage());
            }catch (Exception e){
                error(TAG, e.getMessage());
            }
        }
    }
}