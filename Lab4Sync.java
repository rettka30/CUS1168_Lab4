import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// import java.util.concurrent.locks.Lock;
// import java.util.concurrent.locks.ReentrantLock;
  
class SynchronizedCounter {

//    private int counter;
//    private Lock sizeLock;
   
//    public Counter() {
//        counter = 0;  
//        sizeLock = new ReentrantLock(); 
//    }

   int counter = 0;
   
   public void increment() { // throws InterruptedException {
//       sizeLock.lock();
//       try {
         synchronized(this){
            counter++;
         }
//       } finally {
//          sizeLock.unlock();
//       }
   }
   
   public void decrement() { // throws InterruptedException {
      // sizeLock.lock();
//       try {
      synchronized(this){
         counter--;
      }
//       } finally {
//          sizeLock.unlock();
//       }
   }
   
   public int getCount() {
      return counter;
   }
   
}

public class Lab4Sync {

    	public static void main(String[] args) throws InterruptedException {
    	      ExecutorService execute = Executors.newFixedThreadPool(10);
    	      
    	      SynchronizedCounter count = new SynchronizedCounter();
            long startTime = System.nanoTime();
    	      for(int i = 0; i<100000; i++){
    	    	  		execute.submit(()->count.increment());
                  execute.submit(()->count.decrement());
    	      }
    	      
    	      execute.shutdown();
    	      execute.awaitTermination(60, TimeUnit.SECONDS);
    	      long endTime = System.nanoTime();
            
    	      System.out.println("Final Count Value is " + count.getCount() );
            System.out.println(((endTime - startTime)/(1000000)) + " milliseconds");
    	      
      }
    
}

//1: 8 milliseconds
//2: 9 milliseconds
//4: 11 milliseconds
//8: 12 milliseconds
//16: 13 milliseconds
//32: 15 milliseconds
//Lab4Sync: 100000: 144 milliseconds
//Lab4NoSync: 100000: 18338 milliseconds
//Speedup: 18338/144=127.347 => 127X
//Processor Specs:
//Inspiron 7573
//Processor: Intel(R) Core(TM) i7-8550U CPU @ 1.80GHz 1.99GHz
//System Type: 64-bit operating system, x64-based processor

//A and B combined:
// Similar to the dining philosophers problem, problems can emerge when simulating this setup. 
// If multiple savages attempt to eat a portion at the same time, or at least more than this resource 
// can handle, livelock may occur. With multiple savages attempting to access this resource at the 
// same time, they will all fail and try again later at the same time. This problem can be solved 
// using locks on the savages so that the resource. One problem that may emerge is starvation, as 
// there may be a savage who will be unable to acquire a portion continually. Of course, this problem 
// may only occur precautions are not taken to remedy this particular problem. One solution is to add 
// Conditions to the locks or by adding priority to the threads, so that all processes can eventually 
// acquire the resource. This could apply to how many servings each savage needs.
