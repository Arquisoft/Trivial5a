package es.uniovi.asw.trivial;

import es.uniovi.asw.bussines.Game;

public class TrivalDesktop {
	
	public static void main(String [] args)
	{
		run();
	}

	public static void run(){
		
		Game g = new Game();
		g.initialize();
	}

}
