package mine2;



public class Carriage {

	private  volatile int value = 0;
	  
	  public Carriage() {}
	  
	  public void setValue(int value) 
	  {
	    if(value > 6)
	      return;
	    else
	    this.value=value;
	  }
	  
	  public int getValue() 
	  {
	    return value;
	  }
	}