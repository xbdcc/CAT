package com.carlos.cat.minicap;

import java.awt.*;

public interface ScreenSubject {

    public void registerObserver(AndroidScreenObserver o);

    public void removeObserver(AndroidScreenObserver o);

    public void notifyObservers(Image image);

    public void notifyObserversImageChange(byte[] imageByte);

}
