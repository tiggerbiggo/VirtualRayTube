package com.tiggerbiggo.vrt.core;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import old.RayScreen;

public class RayScreenView extends ImageView {
  RayScreen screen;
  BufferedImage img;

  Timer animationTimer;

  public RayScreenView(RayScreen _screen){
    super();
    screen = _screen;
    screen.setBrightness(10);
    img = screen.getImg();

    animationTimer = new Timer();
    animationTimer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        refreshImage();
        screen.refreshImg();
        Image fxImage = SwingFXUtils.toFXImage(img, null);
//        System.out.println("\n\nColors below: ");
//        System.out.println(fxImage.getPixelReader().getArgb(0,0));
//        System.out.println(img.getRGB(0,0));
        Platform.runLater( () -> setImage(fxImage) );

      }
    },
    0,
        1000/60
    );
  }

  public void refreshImage(){
    img = screen.getImg();
  }
}
