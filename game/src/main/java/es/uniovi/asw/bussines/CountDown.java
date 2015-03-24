package es.uniovi.asw.bussines;

import java.util.Timer;
import java.util.TimerTask;
public class CountDown {

	/**
	 * Clase que sirve para hacer la cuenta regresiva de las preguntas
	 */
	private Timer temporizador;
	DisplayCowntdown c = new DisplayCowntdown();
	boolean acabado;

	public static void main(String args[]) {
		CountDown c1 = new CountDown();
		c1.schedule();

	}

	public CountDown() {
		temporizador = new Timer();
	}

	public void schedule() {
		temporizador.schedule(c, 0, 1000);
		tiempoacabado();

	}

	/**
	 * Metodo que devuelve si se acabado el tiempo o no
	 * @return
	 */
	public boolean tiempoacabado() {
	//	Scanner sc = new Scanner(System.in);

		while (c.tiempoAcabado != true) {
			//System.out.println("tiempo NO acabado");
			/*String comand = sc.nextLine();
			if (comand.equals("q"))*/
				pararTiempo();
			/*if (comand.equals("n"))
				reiniciarTiempo();*/
		}
		//System.out.println("tiempo acabado");

		return true;
	}

	/**
	 * Metodo que se llama cuando se inicia una nueva pregunta
	 */
	private void reiniciarTiempo() {
		c = new DisplayCowntdown();
		schedule();
	}

	/**
	 * Metodo que se llamara cuando se selecciona una respuesta para parar el
	 * crono
	 */
	public void pararTiempo() {
		c.cancel();
	}
	
	
	
	/**
	 * Clase interna que funciona para decrementar el crono
	 * @author Rinok
	 *
	 */
	public class DisplayCowntdown  extends TimerTask{

		
		 public int segundos = 30 ; 
		 public boolean tiempoAcabado =false;
		 
		 
		@Override
		public void run() {
			if ( segundos> 0 ) { 
					System.out.println ( segundos + " segundos restantes ") ; 
					segundos--; 
					} 
			else {
					tiempoAcabado=true;
					System.exit(0);	
			}
			
		}
	
	
	}
	

}
