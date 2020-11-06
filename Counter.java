import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
  
public class Counter {

    private int counter;
    private Lock sizeLock;
   
    public Counter() {
        counter = 0;  
        sizeLock = new ReentrantLock(); 
    }
   
   public void increment() throws InterruptedException {
       sizeLock.lock();
       try {
         counter++;
       } finally {
          sizeLock.unlock();
       }
   }
   
   public void decrement() throws InterruptedException {
       sizeLock.lock();
       try {
         counter--;
       } finally {
          sizeLock.unlock();
       }
   }
   
   public int getCount() {
      return counter;
   }
   
}
