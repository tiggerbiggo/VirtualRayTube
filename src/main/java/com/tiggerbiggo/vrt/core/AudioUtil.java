package com.tiggerbiggo.vrt.core;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.spi.MinimServiceProvider;
import org.jtransforms.fft.FloatFFT_1D;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class AudioUtil {
    AudioPlayer player;

    public AudioUtil(){
        Minim min = new Minim(this);

        player = min.loadFile("audio/Music.wav");

    }

    public void start() throws NullPointerException{
        player.play();
    }

    public float getLoudness(){
        return player.getVolume();
    }

    String sketchPath(String fileName){
        return fileName;
    }

    InputStream createInput(String fileName){
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
