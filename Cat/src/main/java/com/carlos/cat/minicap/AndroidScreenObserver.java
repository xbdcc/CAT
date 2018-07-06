package com.carlos.cat.minicap;

import java.awt.*;

public interface AndroidScreenObserver {

    public void onImageChanged(Image image);

    public void onImageDataChange(byte[] imageByte);
}
