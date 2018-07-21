package old;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.Timer;

public class Main {
  public static final int WIDTH = 200;
  public static final int HEIGHT = 200;

  public static void main(String[] args) {
    RayScreen screen = new RayScreen(200, 200);
    screen.setDarkenFactor(0.99);

    BufferedImage img = screen.getImg();

    JFrame f = new JFrame("Hi");

    f.setBounds(0,0, WIDTH, HEIGHT);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setBackground(Color.RED);

    JSlider brightness = new JSlider(1, 100);
    brightness.setBounds(0, 500, 500, 30);
    brightness.addChangeListener(e -> screen.setBrightness(brightness.getValue()));

    f.add(brightness);

    Timer displayRepaintTimer = new Timer(0, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Graphics g = f.getGraphics();
        if(g != null) g.drawImage(img, 20, 40, null);
        screen.refreshImg();
      }
    });

    Runnable run = new Runnable() {
      @Override
      public void run() {
        double c = 0;
        while(!Thread.interrupted()){
          for(double i=0; i<Math.PI*4; i+=0.01){
            screen.plot(
                Math.sin(i*Math.sin(c*0.5)) * Math.cos(c) * 50
                ,
                Math.cos(i-c) * Math.cos(c) * 50
            );
          }
          c+=0.003;
        }
      }
    };

    Renderer r = new Renderer();
    r.start(run);

    displayRepaintTimer.setDelay(1000/60);
    displayRepaintTimer.start();

    f.setVisible(true);

  }
}
