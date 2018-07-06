package com.carlos.cat.minicap;

import com.android.ddmlib.IDevice;

import java.awt.*;

public class MiniCapHelper implements AndroidScreenObserver {

    private IDevice iDevice;
    private MiniCapUtil minicap;

    private static MiniCapHelper instance;

    private MiniCapHelper(IDevice device) {
        this.iDevice = device;
        init();
    }

    public static MiniCapHelper getInstance(IDevice device) {
        synchronized (MiniCapHelper.class) {
            if (instance == null) {
                if (instance == null) {
                    instance = new MiniCapHelper(device);
                }
            } else if (!instance.getDevice().getSerialNumber().equals(device.getSerialNumber())) {
                instance = new MiniCapHelper(device);
            }
        }
        return instance;

    }

    public void init() {
        minicap = new MiniCapUtil(iDevice);
        minicap.registerObserver(this);
    }

    public IDevice getDevice() {
        return iDevice;
    }

    public void takeScreenShot(String localSavePath) {
        if (minicap != null) {
            minicap.takeScreenShotOnce(localSavePath);
        }
    }

    @Override
    public void onImageChanged(Image image) {

    }

    @Override
    public void onImageDataChange(byte[] imageByte) {

    }
}
