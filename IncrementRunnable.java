public class IncrementRunnable implements Runnable {

   private static final int DELAY = 1; 
   private Counter count;

   public IncrementRunnable(Counter i) {
      count = i;
   }
   
   public void run() {
      try {
         count.increment();
         Thread.sleep(DELAY);
      }
      catch (InterruptedException exception) {}
   }
}