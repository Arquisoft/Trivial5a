package es.uniovi.asw.trivial;

import java.util.List;
import es.uniovi.asw.bussines.*;
import es.uniovi.asw.model.User;

public class TrivalDesktop {

	public void run(List<User> usuarios){
		Game g = new Game(usuarios);
		g.initialize();
	}

}
