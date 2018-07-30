package com.tiggerbiggo.vrt.core;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import com.tiggerbiggo.utils.calculation.Vector2;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import old.RayScreen;
import old.Renderer;

public class MainController implements Initializable {

  @FXML
  AnchorPane imageHoldAnchorPane;

  @FXML
  Slider brightnessSlider, darkenSlider;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //AudioUtil player = new AudioUtil();
    //player.start();

    RayScreen scr = new RayScreen(100, 100, Color.BLACK, Color.WHITE);

    RayScreenView scrView = new RayScreenView(scr);
    AnchorPane.setBottomAnchor(scrView, 0d);
    AnchorPane.setTopAnchor(scrView, 0d);
    AnchorPane.setLeftAnchor(scrView, 0d);
    AnchorPane.setRightAnchor(scrView, 0d);

    imageHoldAnchorPane.getChildren().add(scrView);


    imageHoldAnchorPane.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
      @Override
      public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
        scr.resize(newValue.getWidth(), newValue.getHeight());
        scrView.refreshImage();
      }
    });

    Renderer rend = new Renderer();
    rend.start(() ->
    {
      double c = 0;
      while(!Thread.interrupted()){
        for(double i=0; i<1; i+=0.001){
          scr.plot(
                  new Vector2(
                          scr.getWidth() * Math.sin(i + (i*c*Math.sin(c*0.0001*i)))
                    ,
                    Math.sin(c*i)*Math.PI//Math.cos(Math.max(Math.cos(i*c),Math.sin(c*i))) * 4 * Math.PI
                  )
                  .fromPolar()
          );
        }
        c+=0.003;
      }
    });

    brightnessSlider.valueProperty().addListener((ob, o, n) -> scr.setBrightness(n.doubleValue()));
    darkenSlider.valueProperty().addListener((ob, o, n) -> scr.setDarkenFactor(n.doubleValue()));


  }
}