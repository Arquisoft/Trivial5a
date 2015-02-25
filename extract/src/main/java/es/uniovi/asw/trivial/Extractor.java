package es.uniovi.asw.trivial;

import java.io.InputStreamReader;
import java.util.Scanner;

import es.uniovi.asw.Consola.Consola;

public class Extractor {
	
	/**
	 * PUNTO DE ENTRADA DEL PROGRAMA
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public int run(String[] args)  {
		try {
			Consola.main(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage()+"\n");
		}
		
		return 0;
	}
	

	public static void main(final String[] args) {
		new Extractor().automatizar();
		//new Extractor().run(args);
    }
	
	public void automatizar()
	{
		
		
		Runnable run = new Runnable() {
			@Override
			public void run() {
				Scanner scan;
						System.out.println("Ejecutando automaticamente...(Introduzca comando)");
					scan =new Scanner(new InputStreamReader(System.in));
					 String comando = scan.nextLine();
					String[] args= comando.split(" ");
					if(args.length!=0)
						try {
							new Extractor().run(args);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
			
			
		};
		
		try {
			while(1==1)
			{
			Thread threadA1 = new Thread(run, "A");
			//System.out.println(threadA1.getId());
			threadA1.run();
			Thread.sleep(1000);
			
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
