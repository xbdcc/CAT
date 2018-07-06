package com.carlos.cat;

import java.io.File;

public class ConstantUtil {

    public static final String ANDROID_HOME = "ANDROID_HOME";
    public static final String ERROR_MESSAGE = "errormessage:";
    public static final String ANDROID_WEBVIEW_CLASS = "android.webkit.WebView";
    public static final String ANDROID_V7_RECYCLERVIEW_CLASS = "android.support.v7.widget.RecyclerView";
    public static final String ANDROID_LISTVIEW_CLASS = "android.widget.ListView";
    public static final String ANDROID_GRIDVIEW_CLASS = "android.widget.GridView";
    public static final String ANDROID_BUTTON_CLASS = "android.widget.Button";
    public static final String ANDROID_IMAGEVIEW_CLASS = "android.widget.ImageView";
    public static final String ANDROID_RELATIVELAYOUT_CLASS = "android.widget.RelativeLayout";
    public static final String ADB_KEYBOARD_APKNAME = "ADBKeyBoard.apk";
    public static final String ADB_KEYBOARD_PACKAGENAME = "com.android.adbkeyboard";
    public static final String SUCCESS = "Success";

    public static final String SCRIPT_EXEC_STEP_MESSAGE = "Exec step :";
    public static final String SCRIPT_EXEC_START_UP = "Start up";
    public static final String SCRIPT_CMD_SEPARATOR = "^&*";
    public static final String SCRIPT_CMD_CLICK = "click";
    public static final String SCRIPT_CMD_DOUBLE_CLICK = "doubleclick";
    public static final String SCRIPT_CMD_TAP = "tap";
    public static final String SCRIPT_CMD_LONG_CLICK = "longPress";
    public static final String SCRIPT_CMD_UP_GLIDE = "upGlide";
    public static final String SCRIPT_CMD_DOWN_GLIDE = "downGlide";
    public static final String SCRIPT_CMD_LEFT_GLIDE = "leftGlide";
    public static final String SCRIPT_CMD_RIGHT_GLIDE = "rightGlide";
    public static final String SCRIPT_CMD_INPUT_TEXT = "sendText";
    public static final String SCRIPT_CMD_SLEEP = "sleep";
    public static final String SCRIPT_CMD_SCREEN_SHOT = "screenShot";
    public static final String SCRIPT_CMD_IF_JUDGE = "ifJudge";
    public static final String SCRIPT_CMD_FOR_LOOP = "forLoop";
    public static final String SCRIPT_CMD_BACK_CLICK = "back";
    public static final String SCRIPT_CMD_AFFAIR = "Affair";
    public static final String SCRIPT_CMD_START = "start";
    public static final String SCRIPT_CMD_END = "end";

    public static final int ACTION_DEAULT = 0;
    public static final int ACTION_TO_HOME = 1;
    public static final int ACTION_INPUT_TEXT = -1;
    public static final int ACTION_ACCOUNT_INPUT_TEXT = 0;
    public static final int ACTION_PASSWORD_INPUT_TEXT = 1;
    public static final int ACTION_INPUT_CLICK_TEXT_OR_POINT = 2;

    public static final String ROOT = System.getProperty("user.dir") ;
//    public static final String ROOT = System.getProperty("user.dir") + File.separator + "Cat";
    public static final String OSNAME = System.getProperty("os.name");
    public static final boolean ISLINUXOS = OSNAME.contains("linux") || OSNAME.contains("Linux");
    public static final boolean ISWINDOWSOS = OSNAME.startsWith("win") || OSNAME.startsWith("Win");

    public static final String PATH_SEPARATOR = "_";
    public static final String PATH_SCRIPT = ROOT + File.separator + "script";
    public static final String ORINGIN_APK = "oringin.apk";
    public static final String RECORD_APK = "record.apk";
    public static final String SCREEN_SHOT_NAME = "tmpScreenshot.png";

    public static final String SCRIPT_CODE_PATH = ROOT + File.separator + "appiumScriptCode";
    public static final String EXEC_OPERATE_CODE_PATH = SCRIPT_CODE_PATH + File.separator + "ExecOperateFile";
    public static final String SCRIPT_SRC_CODE_PATH = SCRIPT_CODE_PATH + File.separator + "src";
    public static final String SCRIPT_LIB_CODE_PATH = SCRIPT_CODE_PATH + File.separator + "libs";

    public static final String EXEC_OPERATE_HEAD_FILE_PATH = EXEC_OPERATE_CODE_PATH + File.separator
            + "ExecOperateThreadHead.txt";
    public static final String EXEC_OPERATE_END_FILE_PATH = EXEC_OPERATE_CODE_PATH + File.separator
            + "ExecOperateThreadEnd.txt";
    public static final String EXEC_THREAD_FILE_PATH = SCRIPT_SRC_CODE_PATH + File.separator
            + "ExecOperateThread.java";
    public static final String SCRIPT_OUT_PATH = SCRIPT_CODE_PATH + File.separator + "out";
    public static final String SCRIPT_NAME = "script.jar";
    public static final String SCRIPT_TAIL = ".jar";

}
