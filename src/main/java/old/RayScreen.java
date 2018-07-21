package old;

import com.tiggerbiggo.prima.primaplay.graphics.ColorTools;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class RayScreen {
  private BufferedImage img;
  private int[][] numberMap;
  private int currentMax;
  private double brightness, darkenFactor;

  private Color A, B;

  private int width, height, halfWidth, halfHeight;

  private boolean isResizing = false;

  public RayScreen(int _width, int _height, Color _A, Color _B) {
    if(_width <=0 || _height <= 0) throw new IllegalArgumentException("Width or Height out of range");
    width = _width;
    height = _height;

    halfWidth = width / 2;
    halfHeight = height / 2;

    A = _A;
    B = _B;

    numberMap = new int[width][height];

    img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    for(int i=0; i<width; i++){
      for(int j=0; j<height; j++){
        img.setRGB(i, j, A.getRGB());
      }
    }

    darkenFactor = 1;
    brightness = 1;
  }

  public RayScreen(int _width, int _height){
    this(_width, _height, Color.BLACK, Color.WHITE);
  }

  public RayScreen(double _width, double _height){
    this((int)_width, (int)_height);
  }

  public void setBrightness(double b){
    brightness = b;
  }

  public void setDarkenFactor(double darkenFactor) {
    this.darkenFactor = darkenFactor;
  }


  public synchronized void resize(double _width, double _height){
    resize((int)_width, (int)_height);
  }

  public void plot(int x, int y){
    if(isResizing) return;
    x += halfWidth;
    y += halfHeight;
    if(!isInRange(x, y)) return;
    numberMap[x][y] += 10;
    int mapVal = numberMap[x][y];
    if(mapVal > currentMax) currentMax = mapVal;
  }

  public void plot(double x, double y){
    plot((int)Math.ceil(x), (int)Math.ceil(y));
  }

  public synchronized void resize(int _width, int _height){
    System.out.println("W: " + _width + ", H: " + _height);
    isResizing = true;
    if(_width >= 1 && _height >= 1){
      width = _width;
      height = _height;
      halfWidth = width / 2;
      halfHeight = height / 2;
      img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      numberMap = new int[width][height];
    }
    isResizing = false;
  }
  public void refreshImg(double darkenFactor){
    if(isResizing) return;
    for(int i=0; i<width; i++){
      for(int j=0; j<height; j++){
        try {
          double percent = (double) numberMap[i][j] / currentMax;
          percent = percent * brightness;
          img.setRGB(i, j, ColorTools.colorLerp(A, B, percent).getRGB());
          //img.setRGB(i, j, Color.HSBtoRGB((float)percent,1, (float)percent));
          numberMap[i][j] = Math.max(0, (int) (numberMap[i][j] * darkenFactor));
        }
        catch(ArrayIndexOutOfBoundsException e){}
      }
    }
  }

  public void refreshImg(){
    refreshImg(darkenFactor);
  }

  public boolean isInRange(int x, int y){
    return x >= 0 && x < width && y >= 0 && y < height;
  }

  public BufferedImage getImg() {
    return img;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
