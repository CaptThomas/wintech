public class waterCounter{
  private int cups;
  public waterCounter(){
    cups = 0;
  }
  public void drink(){
    cups ++;
  }
  public void reset(){
    cups = 0;
  }
  public int getCups(){
    return cups;
  }
}
