public class DecrementRunnable implements Runnable {
   
   private static final int DELAY = 1;
   private Counter count;

   public DecrementRunnable(Counter d) {
      count = d;
   }

  public void run() {
      try {
         count.decrement();
         Thread.sleep(DELAY);
      }
      catch (InterruptedException exception) {}
   }
}