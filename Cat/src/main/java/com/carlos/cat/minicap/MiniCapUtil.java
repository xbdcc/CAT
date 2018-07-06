package com.carlos.cat.minicap;

import com.android.ddmlib.*;
import com.android.ddmlib.IDevice.DeviceUnixSocketNamespace;
import com.carlos.cat.ConstantUtil;
import com.carlos.cat.ThreadPoolManager;
import com.carlos.cat.uiautomator.DebugBridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MiniCapUtil implements ScreenSubject {

    private Logger log = LoggerFactory.getLogger(MiniCapUtil.class);

    public static final String ABIS_ARM64_V8A = "arm64-v8a";
    public static final String ABIS_ARMEABI_V7A = "armeabi-v7a";
    public static final String ABIS_X86 = "x86";
    public static final String ABIS_X86_64 = "x86_64";
    private static final int DEFAULT_COMPRESS = 2;

    private Queue<byte[]> dataQueue = new LinkedBlockingQueue<byte[]>();
    private List<AndroidScreenObserver> observers = new ArrayList<AndroidScreenObserver>();

    private Banner banner = new Banner();
    private static final int PORT = 1717;
    private Socket socket;
    private IDevice device;
    private String REMOTE_PATH = "/data/local/tmp";
    private String ABI_COMMAND = "ro.product.cpu.abi";
    private String SDK_COMMAND = "ro.build.version.sdk";
    private String MINICAP_BIN = "minicap";
    private String MINICAP_SO = "minicap.so";
    private String MINICAP_CHMOD_COMMAND = "chmod 777 %s/%s";
    private String MINICAP_WM_SIZE_COMMAND = "wm size";
    private String MINICAP_START_COMMAND = "LD_LIBRARY_PATH=/data/local/tmp /data/local/tmp/minicap -P %s@%s/0";
    private String MINICAP_TAKESCREENSHOT_COMMAND = "LD_LIBRARY_PATH=/data/local/tmp /data/local/tmp/minicap -P %s@%s/0 -s >%s";
    private String ADB_PULL_COMMAND = DebugBridge.getAdbLocation() + " -s %s pull %s %s";
    private boolean isRunning = false;
    private String size;
    private String captureSize;
    private int screenZoomScale;
    private int width;
    private int height;

    public MiniCapUtil(IDevice device) {
        this(device, DEFAULT_COMPRESS);
    }

    public MiniCapUtil(IDevice device, int screenZoomScale) {
        this.device = device;
        this.screenZoomScale = screenZoomScale;
        init();
    }

    // check is support minicap
    public boolean isSupoort() {
        String supportCommand = String
                .format("LD_LIBRARY_PATH=/data/local/tmp /data/local/tmp/minicap -P %s@%s/0 -t", size, size);
        String output = executeShellCommand(supportCommand);
        if (output.trim().endsWith("OK")) {
            return true;
        }
        return false;
    }

    /**
     * push the .so of minicap file to /data/local/tmp, start minicap server
     */
    private void init() {
        String abi = device.getProperty(ABI_COMMAND);
        if (abi == null) {
            abi = device.getProperty(ABI_COMMAND);
        }
        log.info("abi is = " + abi);
        String sdk = device.getProperty(SDK_COMMAND);
        log.info("sdk is = " + sdk);
        File minicapBinFile = new File(new File(ConstantUtil.ROOT, "minicap" + File.separator + "bin"),
                abi + File.separator + MINICAP_BIN);
        File minicapSoFile = new File(new File(ConstantUtil.ROOT, "minicap" + File.separator + "shared"),
                "android-" + sdk + File.separator + abi + File.separator + MINICAP_SO);
        try {
            // push the .so and bin files to device
            device.pushFile(minicapBinFile.getAbsolutePath(), REMOTE_PATH + "/" + MINICAP_BIN);
            device.pushFile(minicapSoFile.getAbsolutePath(), REMOTE_PATH + "/" + MINICAP_SO);
            executeShellCommand(String.format(MINICAP_CHMOD_COMMAND, REMOTE_PATH, MINICAP_BIN));
            // port Forward
            device.createForward(PORT, "minicap", DeviceUnixSocketNamespace.ABSTRACT);

            // get the Size of device's screen
            String output = executeShellCommand(MINICAP_WM_SIZE_COMMAND);
            String[] splits = output.split("\n");
            for (String s : splits) {
                if (s.contains("Physical size")) {
                    size = s.split(":")[1].trim();
                    width = Integer.parseInt(size.split("x")[0].trim());
                    height = Integer.parseInt(size.split("x")[1].trim());
                    break;
                }
            }

            // if the screenZoomScale is not 1, then compress by the screenZoomScale
            if (screenZoomScale > 1) {
                captureSize = (width / screenZoomScale) + "x" + (height / screenZoomScale);
            } else {
                captureSize = size;
            }
        } catch (SyncException e) {
            log.error("errormessage: ", e);
        } catch (IOException e) {
            log.error("errormessage: ", e);
        } catch (AdbCommandRejectedException e) {
            log.error("errormessage: ", e);
        } catch (TimeoutException e) {
            log.error("errormessage: ", e);
        }
    }

    public void takeScreenShotOnce() {
        String localPath = System.getProperty("user.dir") + "/screenshot.jpg";
        takeScreenShotOnce(localPath);
    }

    public void takeScreenShotOnce(String localSavePath) {
        String savePath = "/data/local/tmp/screenshot.jpg";
        takeScreenShotOnce(savePath, localSavePath);
    }

    public void takeScreenShotOnce(String savePath, String localSavePath) {

        // save the screen size, if it is bigger than 720P, set the saveSize to 540x960
        String saveSize = size;
//        if (height >= 1080) {
//            saveSize = (width / 2) + "x" + (height / 2);
//        }
//        log.debug("saveSize:" + saveSize);

        String takeScreenShotCommand = String.format(MINICAP_TAKESCREENSHOT_COMMAND, size, saveSize,
                savePath);
        String pullCommand = String.format(ADB_PULL_COMMAND, device.getSerialNumber(), savePath,
                localSavePath);
        try {
            executeShellCommand(takeScreenShotCommand);
            if (localSavePath != null && !localSavePath.equals("")) {
                device.pullFile(savePath, localSavePath);
            }
//            Runtime.getRuntime().exec(pullCommand);
        } catch (IOException e) {
            log.error("errormessage: ", e);
        } catch (SyncException e) {
            log.error("errormessage: ", e);
        } catch (AdbCommandRejectedException e) {
            log.error("errormessage: ", e);
        } catch (TimeoutException e) {
            log.error("errormessage: ", e);
        }

    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv + " ");
        }
        return stringBuilder.toString();
    }

    private String executeShellCommand(String command) {
        CollectingOutputReceiver output = new CollectingOutputReceiver();
        try {
            device.executeShellCommand(command, output, 0);
        } catch (TimeoutException e) {
            log.error("errormessage: ", e);
        } catch (AdbCommandRejectedException e) {
            log.error("errormessage: ", e);
        } catch (ShellCommandUnresponsiveException e) {
            log.error("errormessage: ", e);
        } catch (IOException e) {
            log.error("errormessage: ", e);
        }
        return output.getOutput();
    }

    private BufferedImage createImageFromByte(byte[] binaryData) {
        BufferedImage bufferedImage = null;
        InputStream in = new ByteArrayInputStream(binaryData);
        try {
            bufferedImage = ImageIO.read(in);
            if (bufferedImage == null) {
                log.info("bufferimage is null");
            }
        } catch (IOException e) {
            log.error("errormessage: ", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    log.error("errormessage: ", e);
                }
            }
        }
        return bufferedImage;
    }

    private Image createImage(byte[] data) {
        Image image = Toolkit.getDefaultToolkit().createImage(data);
        log.info("createImage OK");
        return image;
    }

    // java merge two byte[]
    private static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[byte_1.length + byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }

    private byte[] subByteArray(byte[] byte1, int start, int end) {
        byte[] byte2 = new byte[0];
        try {
            byte2 = new byte[end - start];
        } catch (NegativeArraySizeException e) {
            log.error("errormessage: ", e);
        }
        System.arraycopy(byte1, start, byte2, 0, end - start);
        return byte2;
    }

    class ImageConverter implements Runnable {
        private int readBannerBytes = 0;
        private int bannerLength = 2;
        private int readFrameBytes = 0;
        private int frameBodyLength = 0;
        private byte[] frameBody = new byte[0];

        @Override
        public void run() {
            // TODO Auto-generated method stub
            long start = System.currentTimeMillis();
            while (isRunning) {
                if (dataQueue.isEmpty()) {
                    // LOG.info("dataQueue is Empty");
                    continue;
                }
                byte[] buffer = dataQueue.poll();
                int len = buffer.length;
                for (int cursor = 0; cursor < len;) {
                    int byte10 = buffer[cursor] & 0xff;
                    if (readBannerBytes < bannerLength) {
                        cursor = parserBanner(cursor, byte10);
                    } else if (readFrameBytes < 4) {
                        frameBodyLength += (byte10 << (readFrameBytes * 8)) >>> 0;
                        cursor += 1;
                        readFrameBytes += 1;
//                        log.info("size of picture = " + readFrameBytes);
                    } else {
                        if (len - cursor >= frameBodyLength) {
//                            log.info("frameBodyLength = " + frameBodyLength);
                            byte[] subByte = subByteArray(buffer, cursor, cursor + frameBodyLength);
                            frameBody = byteMerger(frameBody, subByte);
                            if ((frameBody[0] != -1) || frameBody[1] != -40) {
                                log.info(String.format("Frame body does not start with JPG header"));
                                return;
                            }
                            final byte[] finalBytes = subByteArray(frameBody, 0, frameBody.length);
                            // change to bufferImage
                            ThreadPoolManager.newTask(new Runnable() {

                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    notifyObserversImageChange(finalBytes);
                                    Image image = createImageFromByte(finalBytes);
                                    notifyObservers(image);
                                }
                            });

                            long current = System.currentTimeMillis();
//                            log.info("picture build,time used : " + TimeUtil.formatElapsedTime(current - start));
                            start = current;
                            cursor += frameBodyLength;
                            restore();
                        } else {
//							System.out.println("size of data needs : " + frameBodyLength);
                            byte[] subByte = subByteArray(buffer, cursor, len);
                            frameBody = byteMerger(frameBody, subByte);
                            frameBodyLength -= (len - cursor);
                            readFrameBytes += (len - cursor);
                            cursor = len;
                        }
                    }
                }
            }

        }

        private void restore() {
            frameBodyLength = 0;
            readFrameBytes = 0;
            frameBody = new byte[0];
        }

        private int parserBanner(int cursor, int byte10) {
            switch (readBannerBytes) {
                case 0:
                    // version
                    banner.setVersion(byte10);
                    break;
                case 1:
                    // length
                    bannerLength = byte10;
                    banner.setLength(byte10);
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                    // pid
                    int pid = banner.getPid();
                    pid += (byte10 << ((readBannerBytes - 2) * 8)) >>> 0;
                    banner.setPid(pid);
                    break;
                case 6:
                case 7:
                case 8:
                case 9:
                    // real width
                    int realWidth = banner.getReadWidth();
                    realWidth += (byte10 << ((readBannerBytes - 6) * 8)) >>> 0;
                    banner.setReadWidth(realWidth);
                    break;
                case 10:
                case 11:
                case 12:
                case 13:
                    // real height
                    int realHeight = banner.getReadHeight();
                    realHeight += (byte10 << ((readBannerBytes - 10) * 8)) >>> 0;
                    banner.setReadHeight(realHeight);
                    break;
                case 14:
                case 15:
                case 16:
                case 17:
                    // virtual width
                    int virtualWidth = banner.getVirtualWidth();
                    virtualWidth += (byte10 << ((readBannerBytes - 14) * 8)) >>> 0;
                    banner.setVirtualWidth(virtualWidth);

                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                    // virtual height
                    int virtualHeight = banner.getVirtualHeight();
                    virtualHeight += (byte10 << ((readBannerBytes - 18) * 8)) >>> 0;
                    banner.setVirtualHeight(virtualHeight);
                    break;
                case 22:
                    // orientation
                    banner.setOrientation(byte10 * 90);
                    break;
                case 23:
                    // quirks
                    banner.setQuirks(byte10);
                    break;
            }

            cursor += 1;
            readBannerBytes += 1;

            if (readBannerBytes == bannerLength) {
                log.info(banner.toString());
            }
            return cursor;
        }

    }

    @Override
    public void registerObserver(AndroidScreenObserver o) {
        observers.add(o);

    }

    @Override
    public void removeObserver(AndroidScreenObserver o) {
        int index = observers.indexOf(o);
        if (index != -1) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers(Image image) {
        for (AndroidScreenObserver observer : observers) {
            observer.onImageChanged(image);
        }

    }

    @Override
    public void notifyObserversImageChange(byte[] imageByte) {
        for (AndroidScreenObserver observer : observers) {
            observer.onImageDataChange(imageByte);
        }
    }
}
