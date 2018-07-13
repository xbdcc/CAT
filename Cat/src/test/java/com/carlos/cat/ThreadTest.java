package com.carlos.cat;

import org.junit.Test;

/**
 * Created by Carlos on 2018/7/5.
 */
public class ThreadTest {

    @Test
    public void test() throws InterruptedException {

        Thread a = new Thread(runnable);
        Thread b = new Thread(runnable);
        Thread c = new Thread(runnable);
        Thread d = new Thread(runnable);

        a.start();
        b.start();
        c.start();
        d.start();


        Thread.sleep(5000);
    }
    int num = 10;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while(true) {
                doit();
            }
        }
    };

    synchronized void doit(){
        if (num > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("num"+num--);
        }
    }
}
