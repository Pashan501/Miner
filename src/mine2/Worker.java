package mine2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Worker  implements Runnable{

	  private String name;
	  private  volatile Carriage carriage;
	  private Exchanger<Carriage> changer;
	  private Mine mine;
	  
	  public Worker(String name, Carriage carriage,Exchanger<Carriage> changer, Mine mine) 
	  {
	    this.name = name;
	    this.carriage = carriage;
	    this.changer = changer;
	    this.mine = mine;
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
	      System.out.println("Worker1 is mining the gold...");
	      if(mine.getValueOfGold() < 6) 
			{
			System.out.println("END");
			System.exit(0);
				
				
			}
	      for(int i = 0; i <3; i++) 
	      {
	        try {
	          TimeUnit.SECONDS.sleep(1);
	        } catch (InterruptedException e) {
	          e.printStackTrace();
	        }
	        mine.setValueOfGold(mine.getValueOfGold()-2);
	        carriage.setValue(carriage.getValue()+2);
	        System.out.println("The value of cart: " + carriage.getValue());
	        System.out.println("The value of mine: " + mine.getValueOfGold());
	      }
	      System.out.println("The cart is full");
	      System.out.println("Giving the cart to Worker2");
	      try {
	        changer.exchange(carriage);
	        carriage = null;
	      } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }
	      
	      try {
	    	  carriage=changer.exchange(null);
	        System.out.println("Taking the cart from Worker2");
	      } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }}
	    }
	    
	  }

	  
	