package mine2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Worker2 implements Runnable{

	  private String name;
	  private  volatile Carriage carriage;
	  private Exchanger<Carriage> changer;
	  private Exchanger<Carriage> changer2;
	  
	  public Worker2(String name, Carriage carriage,Exchanger<Carriage> changer, Exchanger<Carriage> changer2) 
	  {
	    this.name = name;
	    this.carriage = carriage;
	    this.changer = changer;
	    this.changer2=changer2;
	    new Thread(this).start();
	  }
	  
	  
	  public String getName() {
	    return name;
	  }


	  public void setName(String name) {
	    this.name = name;
	  }


	  public Carriage getCart() {
	    return carriage;
	  }


	  public void setCart(Carriage carriage) {
	    this.carriage = carriage;
	  }


	  @Override
	  public void run() {
	    
	    while(true) 
	    {
	      try {
	    	  Carriage carriage = changer.exchange(null);
	      } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }
	      if(carriage!=null) 
	      {
	        System.out.println("Worker2 picking the cart and recieves int to Worker3");
	        try {
	          TimeUnit.SECONDS.sleep(5);
	          changer2.exchange(carriage);
	          carriage=null;
	        } catch (InterruptedException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	        
	      }
	        try {
	        	carriage = changer2.exchange(null);
	        } catch (InterruptedException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	System.out.println("Picking the cart from Worker3");
	          System.out.println("Reciving cart to Worker1...");
	          try {
	            TimeUnit.SECONDS.sleep(5);
	          } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	          }
	          try {
	            changer.exchange(carriage);
	          } catch (InterruptedException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	          }
	          
	    }
	    
	      }
	}