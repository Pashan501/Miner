package mine2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Worker3 implements Runnable {

	  private String name;
	  private  volatile Carriage carriage;
	  private Exchanger<Carriage> changer;
	  
	  public Worker3(String name, Carriage carriage,Exchanger<Carriage> changer) 
	  {
	    this.name = name;
	    this.carriage = carriage;
	    this.changer = changer;
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
	    	  carriage = changer.exchange(null);
	      } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }
	      if(carriage!=null) 
	      {
	        System.out.println("Worker3 picking cart from Worker2");
	        
	      }
	      for(int i = 0; i <=1;i++) 
	      {
	        System.out.println("Unloading the cart...");
	        carriage.setValue(carriage.getValue()-3);
	        System.out.println("Value of cart: " +  carriage.getValue());
	        try {
	          TimeUnit.SECONDS.sleep(1);
	        } catch (InterruptedException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	      }
	      try {
	        System.out.println("Giving cart back to Worker2");
	        changer.exchange(carriage);
	        carriage=null;
	      } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }
	    }
	    
	  }
	}