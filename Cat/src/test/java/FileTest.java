import com.android.SdkConstants;
import org.junit.Test;

import java.awt.*;
import java.io.File;

import static com.android.SdkConstants.FD_PLATFORM_TOOLS;

/**
 * Created by Carlos on 2018/6/29.
 */
public class FileTest {

    @Test
    public void getFile(){
        System.out.println(System.getProperty("user.dir"));
        System.out.println(FileTest.class.getResource("/").getPath());
//        String toolsDir = System.getProperty("com.android.uiautomator.bindir");
//        System.out.println(toolsDir);
        System.out.println(System.getProperties());

        //		String toolsDir = System.getProperty("com.android.uiautomator.bindir");
        String toolsDir = System.getenv("ANDROID_HOME");//$NON-NLS-1$

        File sdk = new File(toolsDir);

        // check if adb is present in platform-tools
        File platformTools = new File(sdk, "platform-tools");
        File adb = new File(platformTools, SdkConstants.FN_ADB);
        if (adb.exists()) {
            System.out.println("exe");
//            return adb.getAbsolutePath();
        }

        System.out.println(FD_PLATFORM_TOOLS);
        System.out.println(System.getenv("ANDROID_HOME"));
        System.out.println(System.getenv());

    }
}
