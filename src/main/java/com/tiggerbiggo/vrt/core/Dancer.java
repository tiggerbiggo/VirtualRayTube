package com.tiggerbiggo.vrt.core;

import old.RayScreen;

public class Dancer implements Runnable {
    RayScreen scr;

    public Dancer(RayScreen _scr) {
        scr = _scr;
    }

    @Override
    public void run() {
        double c = 0;
        while(!Thread.interrupted()){
            for(double i=0; i<Math.PI; i+=0.003){
                scr.plot(
                        Math.sin(i*Math.sin(c*0.5)) * Math.cos(i*c) * scr.getWidth() * 0.3
                        ,
                        Math.cos(i-c) * Math.cos(Math.max(Math.cos(c*0.142), Math.sin(c))) * scr.getHeight() * 0.3
                );
            }
            c+=0.003;
        }
    }
}
