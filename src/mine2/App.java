package mine2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;





public class App {

	
		
	 public static void main(String[] args) {
		    Carriage carriage = new Carriage();
		    Mine mine = new Mine();
		    Exchanger<Carriage> changer = new Exchanger<>();
		    Exchanger<Carriage> changer2 = new Exchanger<>();
		    
		    new Worker("Vasia",carriage,changer,mine);
		    new Worker2("Vitia",carriage,changer,changer2);
		    new Worker3("Sven",carriage,changer2);

		  }

		}