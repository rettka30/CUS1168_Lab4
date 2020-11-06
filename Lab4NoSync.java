public class Lab4NoSync {
   
   public static void main(String[] args) {
      
      final int threads = 100000;
      Counter counter = new Counter();
      
      long startTime = System.nanoTime();
      for (int j = 1; j <= threads; j++) {
         IncrementRunnable i = new IncrementRunnable(counter);
         Thread inc = new Thread(i);
         inc.start();
      
          DecrementRunnable d = new DecrementRunnable(counter);
          Thread dec = new Thread(d);
          dec.start();
      }
      long endTime = System.nanoTime();
      System.out.println(counter.getCount());
      System.out.println(((endTime - startTime)/(1000000)) + " milliseconds");
      
   }
}