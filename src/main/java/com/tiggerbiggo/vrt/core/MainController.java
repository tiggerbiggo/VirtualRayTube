package com.tiggerbiggo.vrt.core;

import java.net.URL;
import java.util.ResourceBundle;
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
  Slider brightnessSlider;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    RayScreen scr = new RayScreen(100, 100);
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
        for(double i=0; i<Math.PI*4; i+=0.01){
          scr.plot(
              Math.sin(i*Math.sin(c*0.5)) * Math.cos(c) * scr.getWidth()
              ,
              Math.cos(i-c) * Math.cos(c) * scr.getHeight()
          );
        }
        c+=0.003;
      }
    });

    brightnessSlider.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        scr.setBrightness(newValue.doubleValue());
      }
    });
  }

  @FXML
  void onBrightnessSliderChanged(){

  }
}

//xamarin