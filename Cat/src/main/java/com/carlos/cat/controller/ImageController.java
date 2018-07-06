package com.carlos.cat.controller;

import com.android.ddmlib.IDevice;
import com.carlos.cat.ConstantUtil;
import com.carlos.cat.ThreadPoolManager;
import com.carlos.cat.uiautomator.DebugBridge;
import com.carlos.cat.minicap.MiniCapHelper;
import com.carlos.cat.uiautomator.UiAutomatorHelper;
import com.carlos.cat.uiautomator.UiAutomatorModel;
import com.google.common.io.Files;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Affine;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Carlos on 2018/6/29.
 */
public class ImageController  {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView image;

    private static final int IMG_BORDER = 2;
    private static final int PHONE_SURFACE_WIDTH = 364;
    private static final int PHONE_SURFACE_HEIGHT = 425;

    private Image screenshot;

    @FXML
    public void click() throws UiAutomatorHelper.UiAutomatorException {

        System.out.println("click");


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // to prevent blocking the ui thread, we do the saving in the other thread.

        IDevice device = pickDevice();
        System.out.println(device.getSerialNumber());


//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        if (device!=null) {
            UiAutomatorHelper.UiAutomatorResult result = UiAutomatorHelper.takeSnapshot(device,true);
//
            setModel(result.model,result.uiHierarchy,result.screenshot);
        }
//        UiAutomatorHelper.UiAutomatorResult result = UiAutomatorHelper.takeSnapshot(device,true);
////
//        setModel(result.model,result.uiHierarchy,result.screenshot);

//        try {
//            showPhoneSurfaceWithoutOperate();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    public void setModel(UiAutomatorModel model, File modelBackingFile, Image screenshot) {
        System.out.println(screenshot);
       image.setImage(screenshot);

    }

    public void showPhoneSurfaceWithoutOperate() throws IOException {
        Canvas canvas = new Canvas(PHONE_SURFACE_WIDTH, PHONE_SURFACE_HEIGHT);
        pane.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        File tmpScreenshotFile = new File(ConstantUtil.ROOT, ConstantUtil.SCREEN_SHOT_NAME);
        IDevice device = pickDevice();
        ThreadPoolManager.newTask(new Runnable() {
            @Override
            public void run() {
                if (device == null) {
                    return;
                }
                Image image = null;
                int i = 0;
                while (true) {
                    try {
                        MiniCapHelper.getInstance(device).takeScreenShot(tmpScreenshotFile.getAbsolutePath());
                        image = new Image(tmpScreenshotFile.toURL().toString());
                        float scaleX = ((float) canvas.getWidth() - 2 * IMG_BORDER - 1)
                                / (float) (image.getWidth());
                        float scaleY = ((float) canvas.getHeight() - 2 * IMG_BORDER - 1)
                                / (float) (image.getHeight());

                        // use the smaller scale here so that we can fit the entire screenshot
                        float mScale = Math.min(scaleX, scaleY);
                        // calculate translation values to center the image on the canvas
                        int mDx = (int) (canvas.getWidth() - getScaledSize(mScale, (int) image.getWidth())
                                - IMG_BORDER * 2) / 2 + IMG_BORDER;
                        int mDy = (int) (canvas.getHeight() - getScaledSize(mScale, (int) image.getHeight())
                                - IMG_BORDER * 2) / 2 + IMG_BORDER;
                        Affine t = new Affine();
                        gc.setTransform(t);
                        gc.translate(mDx, mDy);
                        gc.scale(mScale, mScale);

                        gc.drawImage(image, 0, 0);
                        image = null;
                        i++;
                        if (i == 50) {
                            System.gc();
                            i = 0;
                        }
                    } catch (Exception e) {
//                        log.error("", e);
//                        isCaptureScreen = false;
                    }

                }
            }
        });
    }


    private IDevice pickDevice() {
        IDevice[] devices =  DebugBridge.getDevices();
        if (devices.length == 0) {
            return null;
        } else if (devices.length == 1) {
            return devices[0];
        }
        return null;
    }


    private int getScaledSize(float mScale, int size) {
        if (mScale == 1.0f) {
            return size;
        } else {
            return new Double(Math.floor((size * mScale))).intValue();
        }
    }



}
