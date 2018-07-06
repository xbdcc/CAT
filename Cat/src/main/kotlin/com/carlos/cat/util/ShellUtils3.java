//package com.carlos.cat.util;
//
//import org.apache.log4j.Logger;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class ShellUtils3 {
//
//    private static Logger log = LogUtil.getLogger(ShellUtils.class);
//
//    private static final long COMMAND_TIME_OUT = 30 * 60 * 1000; // cmd命令执行的最大超时时间
//    private static List<String> installResult = new ArrayList<String>();
//
//    public static String execCommand(String command) throws Exception {
//        log.info("Exec shell command: " + command);
//        return execCommand(Runtime.getRuntime().exec(command), false);
//    }
//
//    public static List<String> executeCommand(String command) throws Exception {
//        log.info("execute command: " + command);
//        boolean isInstallCmd = false;
//        if (command.contains(" install-multiple ") || command.contains(" install -r ")) {
//            isInstallCmd = true;
//        }
//        return executeCommand(Runtime.getRuntime().exec(command), false, isInstallCmd);
//    }
//
//    public static List<String> executeCommand(String command, boolean showLog) throws Exception {
//        log.info("execute command: " + command);
//        return executeCommand(Runtime.getRuntime().exec(command), showLog, false);
//    }
//
//    public static String execScriptCommand(String command, Process appiumProcess) throws Exception {
//        appiumProcess = Runtime.getRuntime().exec(command);
//        return execCommand(appiumProcess, true);
//    }
//
//    public static String execCommand(final Process proc, boolean resultOnTime) throws Exception {
////        log.info("Exec shell command... ");
//        Timer timer = startCommandTimeoutTask(proc);
//        DataOutputStream os = null;
//        BufferedReader br = null;
//        InputStreamReader sReader = null;
//        String result = null;
//        try {
//
//            os = new DataOutputStream(proc.getOutputStream());
//            os.flush();
//
//            // new Thread for ErrorStream, avoid it block the standard output stream
//            ThreadPoolManager.newTask(new InputStreamRunnable(proc.getErrorStream(), "ErrorStream", false));
//
//            BufferedInputStream bis = new BufferedInputStream(proc.getInputStream());
//
//            sReader = new InputStreamReader(bis, "UTF-8");
//
//            br = new BufferedReader(sReader);
//
//            StringBuilder sb = new StringBuilder();
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//                handleResultOnTime(resultOnTime, line);
//                sb.append("\n");
//            }
//            br.close();
//            result = sb.toString();
//        } finally {
//            try {
//                if (timer != null) {
//                    timer.cancel();
//                }
//                proc.destroy();
//            } catch (Exception e) {
//                log.error(e);
//            }
//        }
//        return result;
//    }
//
//    private static void handleResultOnTime(boolean resultOnTime, String line) {
//        if (resultOnTime) { // resultOnTime = true, return the result
//            log.info(line);
//            // if it's exec appium script ...
////            if (line.contains("Init AndroidDriver end")) {
////                Platform.runLater(new Runnable() {
////                    @Override
////                    public void run() {
////                        StageController.getInstance().closeDialog();
////                        if (timerHelper != null) {
////                            timerHelper.startTimer();
////                        }
////                    }
////                });
////            }
//        }
//    }
//
//    public static List<String> executeCommand(final Process proc, boolean showLog, boolean isInstallApk)
//            throws Exception {
//        Timer timer = startCommandTimeoutTask(proc);
//        DataOutputStream os = null;
//        BufferedReader br = null;
//        InputStreamReader sReader = null;
//        List<String> results = new ArrayList<String>();
//        try {
//            os = new DataOutputStream(proc.getOutputStream());
//            os.flush();
//
//            // new Thread for ErrorStream, avoid it block the standard output
//            // stream
//            ThreadPoolManager
//                    .newTask(new InputStreamRunnable(proc.getErrorStream(), "ErrorStream", isInstallApk));
//
//            BufferedInputStream bis = new BufferedInputStream(proc.getInputStream());
//            sReader = new InputStreamReader(bis, "UTF-8");
//            br = new BufferedReader(sReader);
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                if (line.length() > 0)
//                    results.add(line);
//                if (showLog)
//                    log.info(line);
//            }
//            br.close();
//        } finally {
//            try {
//                if (timer != null) {
//                    timer.cancel();
//                }
//                if (isInstallApk && (results.size() == 0) || results.get(0).trim().equals("")) {
//                    CommonUtil.sleep(500);
//                    results = installResult;
//                }
//                proc.destroy();
//            } catch (Exception e) {
//                log.error(ConstantUtil.ERROR_MESSAGE, e);
//            }
//        }
//        return results;
//    }
//
//    private static Timer startCommandTimeoutTask(final Process proc) {
//        Timer timer = new Timer();
//        CommandTimeOutTimerTask commandTimerTask = new CommandTimeOutTimerTask(proc);
//        timer.schedule(commandTimerTask, COMMAND_TIME_OUT);
//        return timer;
//    }
//
//    // 命令执行超时的TimerTask
//    private static class CommandTimeOutTimerTask extends TimerTask {
//        private Process process;
//
//        public CommandTimeOutTimerTask(Process process) {
//            this.process = process;
//        }
//
//        @Override
//        public void run() {
//            process.destroy();
//        }
//
//    }
//
//    static class InputStreamRunnable implements Runnable {
//        BufferedReader bReader = null;
//        String type = null;
//        boolean isInstallApk;
//
//        public InputStreamRunnable(InputStream is, String _type, boolean isInstallApk) {
//            try {
//                bReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), "UTF-8"));
//                type = _type;
//                this.isInstallApk = isInstallApk;
//            } catch (Exception ex) {
//                log.error(ex);
//            }
//        }
//
//        @Override
//        public void run() {
//            String line;
//
//            try {
//                StringBuilder sb = new StringBuilder();
//                while ((line = bReader.readLine()) != null) {
//                    sb.append(line);
//                    sb.append("\n");
//                    log.info("error line: " + line);
//                    if (isInstallApk) {
//                        installResult.clear();
//                        installResult.add(line);
//                    }
//                    // Thread.sleep(200);
//                }
//                if (sb.toString().length() != 0) {
//                    log.info(sb.toString());
//                }
//                bReader.close();
//            } catch (Exception ex) {
//                log.error(ex);
//            }
//        }
//    }
//
//}
