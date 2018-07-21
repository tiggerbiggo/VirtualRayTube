package old;

public class RunningAverage {
  private long total = 0;
  private int num = 0;

  public void add(int toAdd){
    num += 1;
    total += toAdd;
  }

  public long getAverage(){
    if(num == 0) return 0;
    return total / num;
  }
}
